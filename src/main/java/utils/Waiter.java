package utils;

import core.BrowserService;
import core.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Waiter {
    private final WebDriverWait wait;

    public Waiter(BrowserService browserService) {
        this.wait = new WebDriverWait(browserService.getDriver(), PropertyReader.getTimeOut());
    }

    public Waiter(BrowserService browserService, int timeout) {
        this.wait = new WebDriverWait(browserService.getDriver(), timeout);
    }

    public WebElement waitForVisibility(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public List<WebElement> waitForVisibilityOfAllElements(By by) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public List<WebElement> waitForVisibilityOfAllElements(List<WebElement> webElement) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(webElement));
    }

    public WebElement waitForElementVisibility(WebElement webElement) {
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }
}
