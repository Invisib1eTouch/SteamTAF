package tests;

import baseEntities.BaseTest;
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
    public void gameTest() {
        MainPageSteps mainPageSteps = new MainPageSteps(browserService, true);

        GameGenrePage gameGenrePage = mainPageSteps
                .proceedToRandomGameGenreByClick()
                .getPageInstance();


        List<GenreCatalogGameItem> genreCatalogGameItems = gameGenrePage.getGameItemsFromTable(3);

//        for (GenreCatalogGameItem item : genreCatalogGameItems) {
//            System.out.println("Name: " + item.getName());
//            System.out.println("Price original: " + item.getOriginalPrice());
//            System.out.println("Price final: " + item.getFinalPrice());
//            System.out.println("Discount: " + item.getDiscount());
//            System.out.println("Platforms: " + item.getPlatforms());
//            System.out.println("");
//        }

        GameGenrePageSteps gameGenrePageSteps = new GameGenrePageSteps(browserService);

        for (GenreCatalogGameItem gameItem : genreCatalogGameItems) {
            gameGenrePageSteps.findGameItemBySearchInput(gameItem);
            GameItemFromSearchResults gameItemFromSearchResults = gameGenrePage
                    .getFirstFoundGameItemFromSearchInput();
            Assert.assertEquals(gameItem.getName(), gameItemFromSearchResults.getName());
            Assert.assertEquals(gameItem.getFinalPrice(), gameItemFromSearchResults.getPrice());
            System.out.println(gameItem.getName() + " " + gameItemFromSearchResults.getName() + "\n" + gameItem.getFinalPrice() + " " + gameItemFromSearchResults.getPrice());

            gameGenrePageSteps.proceedToGameDetailsPageBySearchResults();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
