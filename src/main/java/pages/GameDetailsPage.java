package pages;

import core.BrowserService;
import org.openqa.selenium.By;

public class GameDetailsPage extends CommonHeader{
    private static final By pageContainerBy = By.className("page_content_ctn");

    public GameDetailsPage(BrowserService browserService) {
        super(browserService, null);
    }

    @Override
    protected By getIndicatorThatPageOpenedElementLocator() {
        return pageContainerBy;
    }
}
