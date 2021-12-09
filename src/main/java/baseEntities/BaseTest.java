package baseEntities;

import core.BrowserService;
import core.PropertyReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import utils.Listener;
import utils.Logger;

@Listeners({Listener.class})
public class BaseTest {
    public BrowserService browserService;
    protected SoftAssert softAssert;

    @BeforeMethod
    public void setupClass() {
        softAssert = new SoftAssert();
        browserService = new BrowserService();
        Logger.log.info("The BrowserService with " + PropertyReader.getBrowserName() + " is initialized.");
    }

    @AfterMethod
    public void tearDownClass() {
        browserService.getDriver().quit();
        browserService = null;
        Logger.log.info("Driver is torn down.");
    }
}
