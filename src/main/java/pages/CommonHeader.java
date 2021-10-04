package pages;

import baseEntities.BasePage;
import core.BrowserService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class CommonHeader extends BasePage {
    private static final By loginBtnBy = By.xpath("//a[@class = 'global_action_link']");
    private static final By installSteamBtnBy = By.className("header_installsteam_btn_content");

    public CommonHeader(BrowserService browserService, String path) {
        super(browserService, path);
    }

    public WebElement getLoginBtn() {
        return browserService.getDriver().findElement(loginBtnBy);
    }

    public WebElement getInstallSteamBtn() {
        return browserService.getDriver().findElement(installSteamBtnBy);
    }
}
