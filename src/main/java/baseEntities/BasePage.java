package baseEntities;

import core.BrowserService;
import core.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected BrowserService browserService;
    protected WebDriver driver;
    protected final static String BASE_URL = PropertyReader.getUrl();
    protected final String path;

    public BasePage(BrowserService browserService, String path) {
        this.browserService = browserService;
        this.driver = browserService.getDriver();
        this.path = path;
    }

    public void open() {
        if (this.path != null) {
            this.driver.get(BASE_URL + path);
        }
        verifyPageOpened();
    }

    public void verifyPageOpened() {
        try {
            this.browserService.getWaiter().waitForVisibility(getIndicatorThatPageOpenedElementLocator());
        } catch (TimeoutException e) {
            throw new AssertionError("Page was not opened");
        }
    }

    protected abstract By getIndicatorThatPageOpenedElementLocator();
}
