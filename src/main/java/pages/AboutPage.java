package pages;

import core.BrowserService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AboutPage extends CommonHeader {
    private static final String installSteamBtn = "//div[@id = 'about_greeting']/descendant::a[contains(@href, 'https://cdn.akamai.steamstatic.com/client/installer/%s')]";

    private static final By mainInstallSteamBtnBy = By.xpath("//div[@id = 'about_greeting']//child::a[@class = 'about_install_steam_link']");

    public AboutPage(BrowserService browserService) {
        super(browserService, "/about");
    }

    @Override
    protected By getIndicatorThatPageOpenedElementLocator() {
        return mainInstallSteamBtnBy;
    }

    public WebElement getInstallSteamBtn(String fileName) {
        return this.browserService.getDriver().findElement(By.xpath(String.format(installSteamBtn, fileName)));
    }

}
