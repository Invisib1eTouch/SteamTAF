package steps;

import baseEntities.BaseStep;
import core.BrowserService;
import io.qameta.allure.Step;
import pages.AboutPage;

public class AboutPageSteps extends CommonHeaderSteps<AboutPage> {
    public AboutPageSteps(BrowserService browserService, boolean openPageByUrl) {
        super(browserService, openPageByUrl);
    }

    @Step("Open About Steam page.")
    @Override
    public AboutPageSteps openPage() {
        this.page.open();
        return this;
    }
}
