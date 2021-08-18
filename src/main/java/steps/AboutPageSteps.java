package steps;

import baseEntities.BaseStep;
import core.BrowserService;
import pages.AboutPage;

public class AboutPageSteps extends CommonHeaderSteps<AboutPage>{
    public AboutPageSteps(BrowserService browserService, boolean openPageByUrl) {
        super(browserService, openPageByUrl);
    }

    @Override
    public AboutPageSteps openPage() {
        this.page.open();
        return this;
    }
}
