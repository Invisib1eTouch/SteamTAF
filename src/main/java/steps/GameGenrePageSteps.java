package steps;

import core.BrowserService;
import io.qameta.allure.Step;
import models.GenreCatalogGameItem;
import org.openqa.selenium.JavascriptExecutor;
import pages.GameGenrePage;

public class GameGenrePageSteps extends CommonHeaderSteps<GameGenrePage> {
    JavascriptExecutor js;

    public GameGenrePageSteps(BrowserService browserService) {
        super(browserService, false);
        this.js = (JavascriptExecutor) browserService.getDriver();
    }

    @Override
    public GameGenrePageSteps openPage() {
        return null;
    }

    @Step("Find game item with '{genreCatalogGameItem.name}' name in search input.")
    public GameGenrePageSteps findGameItemBySearchInput(GenreCatalogGameItem genreCatalogGameItem) {
        this.page.getSearchInput().clear();
        this.page.getSearchInput().sendKeys(genreCatalogGameItem.getName());
        return this;
    }

    @Step("Open Game details page through Search Results.")
    public GameDetailsPageSteps proceedToGameDetailsPageBySearchResults(GenreCatalogGameItem genreCatalogGameItem) {
        if (this.page.getFirstFoundGameItemFromSearchInput().getNameAsString().equals(genreCatalogGameItem.getName())) {
            this.page.getFirstFoundGameItemFromSearchInput().getName().click();
            return new GameDetailsPageSteps(browserService);
        } else {
            return null;
        }

    }
}
