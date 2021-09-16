package pages;

import core.BrowserService;
import org.openqa.selenium.By;

public class GameDetailsPage extends CommonHeader{
    public GameDetailsPage(BrowserService browserService) {
        super(browserService, null);
    }

    @Override
    protected By getIndicatorThatPageOpenedElementLocator() {
        return null;
    }
}
