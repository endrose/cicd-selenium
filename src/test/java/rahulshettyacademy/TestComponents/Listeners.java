package rahulshettyacademy.TestComponents;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import rahulshettyacademy.resources.ExtentReportNG;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener  {
    ExtentTest test;
    ExtentReports extent =  ExtentReportNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal(); //Thread safe
    @Override
    public void onTestStart(ITestResult result) {
//        ITestListener.super.onTestStart(result);
       test =  extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);//unique thread id(ErrorValidationTest)-> test
    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        ITestListener.super.onTestSuccess(result);
    test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result){
//        ITestListener.super.onTestFailure(result);
//        test.fail(result.getThrowable());
//        test.log(Status.FAIL, "Test Failed");

        extentTest.get().fail(result.getThrowable());//
//        SCREENSHOT , ATTACH TO REPORT

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        }

        String filePath = null;
        try {
            filePath = getScreenhot(result.getMethod().getMethodName() , driver);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onStart(ITestContext context) {
//        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
//        ITestListener.super.onFinish(context);

        extent.flush();
    }
}
