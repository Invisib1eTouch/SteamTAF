package pages;

import core.BrowserService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AboutPage extends CommonHeader{
    private static final By installSteamForWindowsBtnBy = By.xpath("//div[@id = 'about_greeting']//child::a[@class = 'about_install_steam_link']");
    public AboutPage(BrowserService browserService) {
        super(browserService, "/about");
    }

    @Override
    protected By getIndicatorThatPageOpenedElementLocator() {
        return installSteamForWindowsBtnBy;
    }

    public WebElement getInstallSteamForWindowsBtn(){
        return this.browserService.getDriver().findElement(installSteamForWindowsBtnBy);
    }
}
