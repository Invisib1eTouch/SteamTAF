package utils;

import baseEntities.BaseTest;
import core.BrowserService;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener, WebDriverListener {

    @Override
    public void beforeClick(WebElement element) {
//        WebDriverListener.super.beforeClick(element);

        byte[] scrFile = ((TakesScreenshot) new BaseTest().browserService.getDriver()).getScreenshotAs(OutputType.BYTES);
        saveScreenshot(scrFile);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseTest) currentClass).browserService.getDriver();
        byte[] scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        saveScreenshot(scrFile);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseTest) currentClass).browserService.getDriver();
        byte[] scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        saveScreenshot(scrFile);
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveScreenshot(byte[] screenshot) {
        return screenshot;
    }
}
