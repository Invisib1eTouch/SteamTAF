package steps;

import baseEntities.BasePage;
import baseEntities.BaseStep;
import core.BrowserService;
import io.qameta.allure.Step;
import pages.CommonHeader;

public abstract class CommonHeaderSteps<Page extends CommonHeader> extends BaseStep<Page> {
    public CommonHeaderSteps(BrowserService browserService, boolean openPageByUrl) {
        super(browserService, openPageByUrl);
    }

    @Step("Open Login page.")
    public LoginPageSteps proceedToLoginPageByButton() {
        this.page.getLoginBtn().click();
        return new LoginPageSteps(browserService, false);
    }

    @Step("Open About Steam page.")
    public AboutPageSteps proceedToAboutPageByButton() {
        this.page.getInstallSteamBtn().click();
        return new AboutPageSteps(browserService, false);
    }
}
