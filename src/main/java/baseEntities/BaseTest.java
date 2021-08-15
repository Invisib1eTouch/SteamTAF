package baseEntities;

import core.BrowserService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    public BrowserService browserService;

    @BeforeMethod
    public void setupClass() {
        browserService = new BrowserService();
    }

    @AfterMethod
    public void tearDownClass() {
        browserService.getDriver().quit();
        browserService = null;
    }
}
