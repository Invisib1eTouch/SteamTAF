package steps;

import core.BrowserService;
import pages.MainPage;

public class MainPageSteps extends CommonHeaderSteps<MainPage> {
    public MainPageSteps(BrowserService browserService, boolean openPageByUrl) {
        super(browserService, openPageByUrl);
    }

    @Override
    public MainPageSteps openPage() {
        this.page.open();
        return this;
    }
}
