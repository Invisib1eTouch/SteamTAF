package tests;

import baseEntities.BaseTest;
import models.GenreCatalogGameItem;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.GameGenrePage;
import steps.MainPageSteps;

import java.util.List;

public class GameDescriptionTest extends BaseTest {

    @Test
    public void gameTest() {
        MainPageSteps mainPageSteps = new MainPageSteps(browserService, true);

        GameGenrePage gameGenrePage = mainPageSteps
                .proceedToRandomGameGenreByClick()
                .getPageInstance();

//        int cnt = 0;
//        for (WebElement el: gameGenrePage.getGameItems()){
//            cnt++;
//            if (cnt == 16) {
//                el.click();
//
//            }
//
//        }


        List<GenreCatalogGameItem> genreCatalogGameItems = gameGenrePage.collectGameDataFromTable(-1111);

        for (GenreCatalogGameItem item : genreCatalogGameItems) {
            System.out.println("Name: " + item.getName());
            System.out.println("Price original: " + item.getOriginalPrice());
            System.out.println("Price final: " + item.getFinalPrice());
            System.out.println("Discount: " + item.getDiscount());
            System.out.println("Platforms: " + item.getPlatforms());
            System.out.println("");
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
