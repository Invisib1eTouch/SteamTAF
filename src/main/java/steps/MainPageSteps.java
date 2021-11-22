package steps;

import core.BrowserService;
import io.qameta.allure.Step;
import pages.MainPage;
import utils.Utils;

public class MainPageSteps extends CommonHeaderSteps<MainPage> {
    public MainPageSteps(BrowserService browserService, boolean openPageByUrl) {
        super(browserService, openPageByUrl);
    }

    @Step("Open Main page.")
    @Override
    public MainPageSteps openPage() {
        this.page.open();
        return this;
    }

    @Step("Open random Genre page.")
    public GameGenrePageSteps proceedToRandomGameGenreByClick() {
        this.page.getCategoryBtn().click();
        this.page.getGenresLinks().get(Utils.getRandomNumber(0, this.page.getGenresLinks().size() - 1)).click();
        return new GameGenrePageSteps(browserService);
    }
}
