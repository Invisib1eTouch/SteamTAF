package pages;

import core.BrowserService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage extends CommonHeader {
    private static final By categoryBtnBy = By.id("genre_tab");
    private static final By genresLinksBy = By.xpath("//div[contains(@class, 'popup_menu_subheader popup_genre_expand_header')]/following-sibling::div[contains(@class, 'popup_genre_expand_content')]//a");

    public MainPage(BrowserService browserService) {
        super(browserService, "");
    }

    @Override
    protected By getIndicatorThatPageOpenedElementLocator() {
        return categoryBtnBy;
    }

    public WebElement getCategoryBtn() {
        return browserService.getDriver().findElement(categoryBtnBy);
    }

    public List<WebElement> getGenresLinks(){
        return browserService.getDriver().findElements(genresLinksBy);
    }
}
