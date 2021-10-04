package steps;

import core.BrowserService;
import pages.LoginPage;

public class LoginPageSteps extends CommonHeaderSteps<LoginPage> {

    public LoginPageSteps(BrowserService browserService, boolean openPageByUrl) {
        super(browserService, openPageByUrl);
    }

    @Override
    public LoginPageSteps openPage() {
        this.page.open();
        return this;
    }

    public LoginPageSteps loginWithIncorrectCredentials(String login, String password) {
        this.page.getSteamAccountNameInput().sendKeys(login);
        this.page.getPasswordInput().sendKeys(password);
        this.page.getSignInBtn().click();
        return this;
    }
}
