package baseEntities;

import core.BrowserService;
import core.PropertyReader;
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
    public void setupClass() {
        softAssert = new SoftAssert();
        browserService = new BrowserService();
        log.info("The BrowserService with " + PropertyReader.getBrowserName() + " is initialized.");
    }

    @AfterMethod
    public void tearDownClass() {
        browserService.getDriver().quit();
        browserService = null;
        log.info("Driver is torn down.");
    }
}
