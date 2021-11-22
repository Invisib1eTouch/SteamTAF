package steps;

import core.BrowserService;
import io.qameta.allure.Step;
import pages.LoginPage;

public class LoginPageSteps extends CommonHeaderSteps<LoginPage> {

    public LoginPageSteps(BrowserService browserService, boolean openPageByUrl) {
        super(browserService, openPageByUrl);
    }

    @Step("Open Login Page.")
    @Override
    public LoginPageSteps openPage() {
        this.page.open();
        return this;
    }

    @Step("Login with incorrect credentials: ({login} / {password}).")
    public LoginPageSteps loginWithIncorrectCredentials(String login, String password) {
        this.page.getSteamAccountNameInput().sendKeys(login);
        this.page.getPasswordInput().sendKeys(password);
        this.page.getSignInBtn().click();
        return this;
    }
}
