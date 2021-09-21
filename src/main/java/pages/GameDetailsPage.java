package pages;

import core.BrowserService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class GameDetailsPage extends CommonHeader{
    private static final By pageContainerBy = By.className("page_content_ctn");
    private static final By ageDropdownBy = By.id("ageYear");
    private static final By viewPageBtnBy = By.xpath("//a[@onclick = 'ViewProductPage(); return false;']");

    public GameDetailsPage(BrowserService browserService) {
        super(browserService, null);
    }

    @Override
    protected By getIndicatorThatPageOpenedElementLocator() {
        return pageContainerBy;
    }

    public Select getAgeDropdown(){
        return new Select(browserService.getDriver().findElement(ageDropdownBy));
    }

    public WebElement getViewPageBnt(){
        return browserService.getDriver().findElement(viewPageBtnBy);
    }

    public By getAgeDropdownBy() {
        return ageDropdownBy;
    }

    public By getViewPageBtnBy() {
        return viewPageBtnBy;
    }
}
