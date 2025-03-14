package com.devstack.automation.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.Objects;

public class ExtentReportManager {
    private static ExtentReports extentReports;

    private static ExtentTest test;

    public static void initReport() {
        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("ExtentReports/ExtentReports.html");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Test Automation Results");
        extentReports.attachReporter(sparkReporter);
    }

    public static void createTest(String testName) {
        test = extentReports.createTest(testName);
    }

    public static void logPass(String message) {
        if(Objects.nonNull(test)) { //test != null
            test.log(Status.PASS, message);
        }
    }

    public static void logFail(String message) {
        if(Objects.nonNull(test)) { //test != null
            test.log(Status.FAIL, message);
        }
    }

    public static void logSkip(String message) {
        if(Objects.nonNull(test)) { //test != null
            test.log(Status.SKIP, message);
        }
    }

    public static void writeToReport(String message) {
        if(Objects.nonNull(test)) {
            test.log(Status.INFO, message);
        }
    }

    public static void logFailWithScreenShot(String message,String base64Screenshot) {
        if(Objects.nonNull(test)) {
            test.fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        }
    }

    public static void flushReport() {
        if(Objects.nonNull(test)) {
            extentReports.flush();
        }
    }
}
