package baseEntities;

import core.BrowserService;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import utils.Listener;

@Log4j2
@Listeners({Listener.class})
public class BaseTest {
    public BrowserService browserService;
    protected SoftAssert softAssert;

    @BeforeMethod
    @Parameters({"BrowserType"})
    public void setupClass(String browserType) {
        softAssert = new SoftAssert();
        browserService = new BrowserService(browserType);
        log.info("The BrowserService with " + browserType + " is initialized.");
    }

    @AfterMethod
    public void tearDownClass() {
        browserService.getDriver().quit();
        browserService = null;
        log.info("Driver is torn down.");
    }
}
