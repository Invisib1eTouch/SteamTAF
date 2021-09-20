package steps;

import core.BrowserService;
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

    public GameGenrePageSteps findGameItemBySearchInput(GenreCatalogGameItem genreCatalogGameItem){
//        showSearchResultsByJsScript();
        this.page.getSearchInput().clear();
        this.page.getSearchInput().sendKeys(genreCatalogGameItem.getName());
        return this;
    }

    private void showSearchResultsByJsScript() {
        js.executeScript("arguments[0].setAttribute('style', 'display: block;');", this.page.getSearchResults());
    }

    public GameDetailsPageSteps proceedToGameDetailsPageBySearchResults(GenreCatalogGameItem genreCatalogGameItem){
        if (this.page.getFirstFoundGameItemFromSearchInput().getNameAsString().equals(genreCatalogGameItem.getName())){
            this.page.getFirstFoundGameItemFromSearchInput().getName().click();
            return new GameDetailsPageSteps(browserService);
        } else {
            return null;
        }

    }
}
