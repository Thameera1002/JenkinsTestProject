package com.devstack.automation.testbase;

import com.devstack.automation.reporter.ExtentReportManager;
import com.devstack.automation.utils.ThreadLocalWebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    public void onTestStart(ITestResult result) {
        ExtentReportManager.createTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.logPass(result.getMethod().getMethodName()+" Passed.");
    }

    public void onTestFailure(ITestResult result) {
        WebDriver driver = ThreadLocalWebDriverManager.getDriver();
        String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        ExtentReportManager.logFailWithScreenShot("Test Failed."+result.getThrowable().getLocalizedMessage(), base64Screenshot);
        ExtentReportManager.logFail(result.getMethod().getMethodName()+" Failed.");
    }

    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.logSkip(result.getMethod().getMethodName()+" Skipped."+result.getThrowable().getMessage());
    }

    public void onStart(ITestContext context) {
        ExtentReportManager.initReport();
    }

    public void onFinish(ITestContext context) {
        ExtentReportManager.flushReport();
    }
}
