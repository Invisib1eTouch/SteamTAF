package steps;

import core.BrowserService;
import io.qameta.allure.Step;
import pages.GameDetailsPage;

import java.time.Year;

public class GameDetailsPageSteps extends CommonHeaderSteps<GameDetailsPage> {
    public GameDetailsPageSteps(BrowserService browserService) {
        super(browserService, false);
    }

    @Override
    public GameDetailsPageSteps openPage() {
        return null;
    }

    @Step("Apply adult date of birthday.")
    public GameDetailsPageSteps applyDateOfBirthIfPresent() {
        if (browserService.getDriver().findElements(this.page.getViewPageBtnBy()).size() > 0) {
            this.page.getAgeDropdown().selectByValue(Integer.toString(Year.now().getValue() - 18));
            this.page.getViewPageBnt().click();
            browserService.getWaiter().waitForVisibility(this.page.getGameAreaPurchaseGameItemBy());
        }
        return this;
    }
}
