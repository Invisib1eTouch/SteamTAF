package pages;

import core.BrowserService;
import models.GameItemFromSearchResults;
import models.GenreCatalogGameItem;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class GameGenrePage extends CommonHeader {
    private static final By gamesTableBy = By.id("NewReleasesRows");
    private static final By gamesNameBy = By.className("tab_item_name");
    private static final By gameFinalPriceBy = By.className("discount_final_price");
    private static final By gameOriginalPriceBy = By.className("discount_original_price");
    private static final By gameDiscountBy = By.className("discount_pct");
    private static final By winPlatformIconBy = By.className("win");
    private static final By macPlatformIconBy = By.className("mac");
    private static final By linuxPlatformIconBy = By.className("linux");
    private static final By searchInputBy = By.id("store_nav_search_term");
    private static final By searchResultsBy = By.id("searchterm_options");
    private static final By searchResultItemsBy = By.cssSelector("#searchterm_options a");
    private static final By itemNameFromSearchResultsInputBy = By.className("match_name");
    private static final By itemFinalPriceFromSearchResultsInputBy = By.className("match_price");


    public GameGenrePage(BrowserService browserService) {
        super(browserService, null);
    }

    @Override
    protected By getIndicatorThatPageOpenedElementLocator() {
        return gamesTableBy;
    }

    private List<WebElement> getGameItems() {
        return this.browserService.getDriver().findElement(gamesTableBy).findElements(By.tagName("a"));
    }

    public List<GenreCatalogGameItem> getGameItemsFromTable(int numberOfGameItems) {
        List<GenreCatalogGameItem> genreCatalogGameItems = new ArrayList<>();
        if (numberOfGameItems > getGameItems().size()) {
            numberOfGameItems = getGameItems().size();
        }
        if (numberOfGameItems <= 0) {
            numberOfGameItems = 1;
        }

        for (int i = 0; i < numberOfGameItems; i++) {
            GenreCatalogGameItem genreCatalogGameItem = new GenreCatalogGameItem(
                    getGameItems().get(i).findElement(gamesNameBy).getText(),
                    getPriceWithDiscountIfExists(getGameItems().get(i)),
                    getFinalPrice(getGameItems().get(i), gameFinalPriceBy),
                    getDiscountIfExists(getGameItems().get(i)),
                    getPlatforms(getGameItems().get(i))
            );
            genreCatalogGameItems.add(genreCatalogGameItem);
        }
        return genreCatalogGameItems;
    }

    private Double getFinalPrice(WebElement gameItem, By gameFinalPriceLocator) {
        String gamePrice = gameItem.findElement(gameFinalPriceLocator).getText();
        if (gamePrice.contains("Free")) {
            return 0.0;
        } else {
            return Utils.getNumberFormString(gamePrice);
        }
    }

    private Double getPriceWithDiscountIfExists(WebElement gameItem) {
        if (gameItem.findElements(gameOriginalPriceBy).size() > 0) {
            return Utils.getNumberFormString(gameItem.findElement(gameOriginalPriceBy).getText());
        } else {
            return null;
        }
    }

    private Double getDiscountIfExists(WebElement gameItem) {
        if (gameItem.findElements(gameDiscountBy).size() > 0) {
            return Utils.getNumberFormString(gameItem.findElement(gameDiscountBy).getText());
        } else {
            return 0.0;
        }
    }

    private List<GenreCatalogGameItem.Platform> getPlatforms(WebElement gameItem) {
        List<GenreCatalogGameItem.Platform> platformList = new ArrayList<>();
        if (gameItem.findElements(winPlatformIconBy).size() > 0) {
            platformList.add(GenreCatalogGameItem.Platform.WINDOWS);
        }
        if (gameItem.findElements(macPlatformIconBy).size() > 0) {
            platformList.add(GenreCatalogGameItem.Platform.MAC);
        }
        if (gameItem.findElements(linuxPlatformIconBy).size() > 0) {
            platformList.add(GenreCatalogGameItem.Platform.LINUX);
        }
        return platformList;
    }

    public WebElement getSearchInput(){
        return this.browserService.getDriver().findElement(searchInputBy);
    }

    public WebElement getSearchResults(){
        return this.browserService.getDriver().findElement(searchResultsBy);
    }

    private List<WebElement> getSearchResultItems() {
        return browserService.getWaiter().waitForVisibilityOfAllElements(searchResultItemsBy);
    }

    public GameItemFromSearchResults getFirstFoundGameItemFormSearchInput(){
        GameItemFromSearchResults item = new GameItemFromSearchResults(
                getSearchResultItems().get(0).findElement(itemNameFromSearchResultsInputBy).getText(),
                getFinalPrice(getSearchResultItems().get(0), itemFinalPriceFromSearchResultsInputBy)
        );


//        JavascriptExecutor js = (JavascriptExecutor) browserService.getDriver();
//        js.executeScript("arguments[0].setAttribute('style', 'display: none;');", getSearchResults());
        return item;
    }
}