package baseEntities;

import core.BrowserService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    protected BrowserService browserService;
    protected SoftAssert softAssert;

    @BeforeMethod
    @Parameters( {"BrowserType"} )
    public void setupClass(String browserType) {
        softAssert = new SoftAssert();
        browserService = new BrowserService(browserType);
    }

    @AfterMethod
    public void tearDownClass() {
        browserService.getDriver().quit();
        browserService = null;
    }
}
