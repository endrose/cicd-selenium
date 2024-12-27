package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

    public static ExtentReports getReportObject(){
        //        ExtentReports , ExtentSparkReporter
//        SPARK REPORT SETUP
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);

        reporter.config().setReportName("Web Automations");
        reporter.config().setDocumentTitle("Test Results");

//        EXTENT REPORT SETUP
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","ENDROS");
//        extent.createTest(path);

        return extent;
    }
}
