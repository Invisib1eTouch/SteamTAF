package pages;

import core.BrowserService;
import org.openqa.selenium.By;

public class MainPage extends CommonHeader {
    private static final By categoryBtnBy = By.id("genre_tab");

    public MainPage(BrowserService browserService) {
        super(browserService, "");
    }

    @Override
    protected By getIndicatorThatPageOpenedElementLocator() {
        return categoryBtnBy;
    }
}
