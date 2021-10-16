package baseEntities;

import core.BrowserService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    protected BrowserService browserService;
    protected SoftAssert softAssert;

    @BeforeMethod
    public void setupClass() {
        softAssert = new SoftAssert();
        browserService = new BrowserService();
    }

    @AfterMethod
    public void tearDownClass() {
        browserService.getDriver().quit();
        browserService = null;
    }
}
