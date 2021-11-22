package steps;

import core.BrowserService;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import models.GenreCatalogGameItem;
import org.openqa.selenium.JavascriptExecutor;
import pages.GameGenrePage;

@Log4j2
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

    @SneakyThrows
    @Step("Open Game details page through Search Results.")
    public GameDetailsPageSteps proceedToGameDetailsPageBySearchResults(GenreCatalogGameItem genreCatalogGameItem) {
        if (this.page.getFirstFoundGameItemFromSearchInput().getNameAsString().equals(genreCatalogGameItem.getName())) {
            this.page.getFirstFoundGameItemFromSearchInput().getName().click();
            return new GameDetailsPageSteps(browserService);
        } else {
            var errMes = "The name of the first game item from Search input doesn't equal searching item.";
            log.fatal(errMes);
            throw new Exception(errMes);
        }
    }
}
