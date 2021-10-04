package steps;

import baseEntities.BasePage;
import baseEntities.BaseStep;
import core.BrowserService;
import pages.CommonHeader;

public abstract class CommonHeaderSteps<Page extends CommonHeader> extends BaseStep<Page> {
    public CommonHeaderSteps(BrowserService browserService, boolean openPageByUrl) {
        super(browserService, openPageByUrl);
    }

    public LoginPageSteps proceedToLoginPageByButton() {
        this.page.getLoginBtn().click();
        return new LoginPageSteps(browserService, false);
    }

    public AboutPageSteps proceedToAboutPageByButton() {
        this.page.getInstallSteamBtn().click();
        return new AboutPageSteps(browserService, false);
    }
}
