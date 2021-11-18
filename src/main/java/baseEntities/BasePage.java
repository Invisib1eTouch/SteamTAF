package baseEntities;

import core.BrowserService;
import core.PropertyReader;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
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
            try{
                this.driver.get(BASE_URL + path);
            } catch (Error e){
                String errMes = String.format("%s was not opened \nDetailed Message:\n%s",
                        this.getClass().getSimpleName(), e.getMessage());
                log.error(errMes);
                throw new AssertionError(errMes);
            }
        }
        verifyPageOpened();
    }

    public void verifyPageOpened() {
        try {
            this.browserService.getWaiter().waitForVisibility(getIndicatorThatPageOpenedElementLocator());
        } catch (Error e) {
            String errMes = String.format("%s was not opened \nDetailed Message:\n%s",
                    this.getClass().getSimpleName(), e.getMessage());
            log.error(errMes);
            throw new AssertionError(errMes);
        }
    }

    protected abstract By getIndicatorThatPageOpenedElementLocator();
}
