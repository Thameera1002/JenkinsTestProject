package com.devstack.automation.testbase;

import com.devstack.automation.constants.SeleniumErrorMessages;
import com.devstack.automation.reporter.ExtentReportManager;
import com.devstack.automation.utils.ThreadLocalWebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumTestBase {

    protected static WebDriver driver;

    protected JavascriptExecutor executor;

    public SeleniumTestBase(WebDriver driver) {
        this.driver = ThreadLocalWebDriverManager.getDriver();
        this.executor = (JavascriptExecutor) this.driver;
    }


    public void click(By locator) {
        try {
            WebElement element = waitForVisibilityOfElement(driver.findElement(locator));
            moveToElement(element);
            element.click();
            ExtentReportManager.logPass("Clicked in locator :" + locator);
        }catch (Exception e) {
            ExtentReportManager.logFail("No such element in locator :" + locator+" /n"+e.getMessage());
        }

    }

    public void jsClick(By locator) {
        try{
            WebElement element = waitForVisibilityOfElement(driver.findElement(locator));
            moveToJsElement(element);
            executor.executeScript("arguments[0].click();", element);
            ExtentReportManager.logPass("JS Clicked in locator :" + locator);
        }catch (Exception e) {
            ExtentReportManager.logFail("No such element in locator :" + locator+" /n"+e.getMessage());
        }
    }

    public void type(By locator, String inputText) {
        try{
            WebElement element = waitForVisibilityOfElement(driver.findElement(locator));
            moveToElement(element);
            element.click();
            element.clear();
            element.sendKeys(inputText);
            ExtentReportManager.logPass("Typed ["+inputText+"] in locator :" + locator);
        }catch (Exception e) {
            ExtentReportManager.logFail("No such element in locator :" + locator+" /n"+e.getMessage());
        }

    }

    public void jsType(By locator, String inputText) {
        try{
            WebElement element = waitForVisibilityOfElement(driver.findElement(locator));
            moveToJsElement(element);
            executor.executeScript("arguments[0].value='';", element);
            executor.executeScript("arguments[0].value='"+inputText+"';", element);
            ExtentReportManager.logPass("JS Typed ["+inputText+"] in locator :" + locator);
        }catch (Exception e) {
            ExtentReportManager.logFail("No such element in locator :" + locator+" /n"+e.getMessage());
        }
    }

    public static WebElement waitForVisibilityOfElement(WebElement locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(locator));
    }

    public static void selectDropdownByVisibleText(By locator, String optionText) {
        WebElement element = waitForVisibilityOfElement(driver.findElement(locator));
        new Select(element).selectByVisibleText(optionText);
    }

    public static void selectDropdownByIndex(By locator, int optionIndex) {
        WebElement element = waitForVisibilityOfElement(driver.findElement(locator));
        new Select(element).selectByIndex(optionIndex);
    }

    public static void moveToElement(WebElement locator) {
        new Actions(driver).moveToElement(locator).perform();
    }

    public void moveToJsElement(WebElement locator) {
        executor.executeScript("arguments[0].scrollIntoView();", locator);
    }

    public boolean isElementPresent(By locator) {
        WebElement element = waitForVisibilityOfElement(driver.findElement(locator));
        return element.isDisplayed();
    }
}
