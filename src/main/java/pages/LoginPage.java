package pages;

import core.BrowserService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends CommonHeader {
    private static final By signInBtnBy = By.className("btn_blue_steamui");
    private static final By steamAccountNameInputBy = By.id("input_username");
    private static final By passwordInputBy = By.id("input_password");
    private static final By errorMessageBy = By.className("checkout_error");

    public LoginPage(BrowserService browserService) {
        super(browserService, "/login/?redir=%3Fsnr%3D1_60_4__global-header&redir_ssl=1&snr=1_4_4__global-header");
    }

    @Override
    protected By getIndicatorThatPageOpenedElementLocator() {
        return signInBtnBy;
    }

    public WebElement getSignInBtn() {
        return browserService.getDriver().findElement(signInBtnBy);
    }

    public WebElement getSteamAccountNameInput() {
        return browserService.getDriver().findElement(steamAccountNameInputBy);
    }

    public WebElement getPasswordInput() {
        return browserService.getDriver().findElement(passwordInputBy);
    }

    public WebElement getErrorMessage(){
        return browserService.getDriver().findElement(errorMessageBy);
    }
}