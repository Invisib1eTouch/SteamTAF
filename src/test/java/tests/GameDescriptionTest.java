package tests;

import baseEntities.BaseTest;
import io.qameta.allure.Description;
import models.GameItemFromSearchResults;
import models.GenreCatalogGameItem;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GameGenrePage;
import steps.GameGenrePageSteps;
import steps.MainPageSteps;

import java.util.List;

public class GameDescriptionTest extends BaseTest {

    @Test
    @Description("Compare game item data in Category game list, Search input and game details page.")
    public void gameTest() {
        MainPageSteps mainPageSteps = new MainPageSteps(browserService, true);

        GameGenrePage gameGenrePage = mainPageSteps
                .proceedToRandomGameGenreByClick()
                .getPageInstance();

        List<GenreCatalogGameItem> genreCatalogGameItems = gameGenrePage.getGameItemsFromTable(3);

        GameGenrePageSteps gameGenrePageSteps = new GameGenrePageSteps(browserService);

        for (GenreCatalogGameItem gameItem : genreCatalogGameItems) {
            gameGenrePageSteps.findGameItemBySearchInput(gameItem);
            GameItemFromSearchResults gameItemFromSearchResults = gameGenrePage
                    .getFirstFoundGameItemFromSearchInput();

            Assert.assertEquals(gameItem.getName(), gameItemFromSearchResults.getNameAsString());
            Assert.assertEquals(gameItem.getFinalPrice(), gameItemFromSearchResults.getFinalPriceAsDouble());

            GenreCatalogGameItem gameDetailsItem = gameGenrePageSteps
                    .proceedToGameDetailsPageBySearchResults(gameItem)
                    .applyDateOfBirthIfPresent()
                    .getPageInstance()
                    .getGameItemFromDetailPage();

            Assert.assertEquals(gameItem.getName(), gameDetailsItem.getName());
            Assert.assertEquals(gameItem.getOriginalPrice(), gameDetailsItem.getOriginalPrice());
            Assert.assertEquals(gameItem.getFinalPrice(), gameDetailsItem.getFinalPrice());
            Assert.assertEquals(gameItem.getDiscount(), gameDetailsItem.getDiscount());
            Assert.assertEquals(gameItem.getPlatforms(), gameDetailsItem.getPlatforms());
        }
    }
}
