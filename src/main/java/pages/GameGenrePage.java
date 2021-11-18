package pages;

import core.BrowserService;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import models.GameItemFromSearchResults;
import models.GenreCatalogGameItem;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

@Log4j2
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

    @SneakyThrows
    private List<WebElement> getGameItems() {
        try {
            return this.browserService.getDriver().findElement(gamesTableBy).findElements(By.tagName("a"));
        } catch (NoSuchElementException e) {
            var errMes = "Couldn't find game item. \nDetailed message: \n" + e.getMessage();
            log.error(errMes);
            throw new Exception(errMes);
        }
    }

    @SneakyThrows
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
                    Utils.replaceStringWithoutTradeMark(getGameItems().get(i).findElement(gamesNameBy).getText()),
                    getPriceWithDiscountIfExists(getGameItems().get(i)),
                    getFinalPrice(getGameItems().get(i), gameFinalPriceBy),
                    getDiscountIfExists(getGameItems().get(i)),
                    getPlatforms(getGameItems().get(i))
            );
            genreCatalogGameItems.add(genreCatalogGameItem);
        }
        return genreCatalogGameItems;
    }

    @SneakyThrows
    private Double getFinalPrice(WebElement gameItem, By gameFinalPriceLocator) {
        try {
            if (gameItem.findElements(gameFinalPriceLocator).size() == 0) {
                return 0.0;
            }
            String gamePrice = gameItem.findElement(gameFinalPriceLocator).getText();
            if (gamePrice.contains("Free")) {
                return 0.0;
            } else {
                return Utils.getNumberFormString(gamePrice);
            }
        } catch (Error e) {
            var errMes = "Couldn't get final price. \nDetailed message: \n" + e.getMessage();
            log.error(errMes);
            throw new Exception(errMes);
        }
    }

    @SneakyThrows
    public static Double getFinalPrice(WebElement gameFinalPriceLocator) {
        try {

            String gamePrice = gameFinalPriceLocator.getText();
            if (gamePrice.contains("Free") || gamePrice.equals("")) {
                return 0.0;
            } else {
                return Utils.getNumberFormString(gamePrice);
            }
        } catch (Error e) {
            var errMes = "Couldn't get final price. \nDetailed message: \n" + e.getMessage();
            log.error(errMes);
            throw new Exception(errMes);
        }
    }

    @SneakyThrows
    private Double getPriceWithDiscountIfExists(WebElement gameItem) {
        try {
            if (gameItem.findElements(gameOriginalPriceBy).size() > 0) {
                return Utils.getNumberFormString(gameItem.findElement(gameOriginalPriceBy).getText());
            } else {
                return null;
            }
        } catch (Error e) {
            var errMes = "Couldn't get price with discount. \nDetailed message: \n" + e.getMessage();
            log.error(errMes);
            throw new Exception(errMes);
        }
    }

    @SneakyThrows
    private Double getDiscountIfExists(WebElement gameItem) {
        try {
            if (gameItem.findElements(gameDiscountBy).size() > 0) {
                return Utils.getNumberFormString(gameItem.findElement(gameDiscountBy).getText());
            } else {
                return 0.0;
            }
        } catch (Error e) {
            var errMes = "Couldn't find discount. \nDetailed message: \n" + e.getMessage();
            log.error(errMes);
            throw new Exception(errMes);
        }
    }

    @SneakyThrows
    private List<GenreCatalogGameItem.Platform> getPlatforms(WebElement gameItem) {
        try {
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
        } catch (Error e) {
            var errMes = "Couldn't find platform. \nDetailed message: \n" + e.getMessage();
            log.error(errMes);
            throw new Exception(errMes);
        }
    }

    public WebElement getSearchInput() {
        return this.browserService.getDriver().findElement(searchInputBy);
    }

    public WebElement getSearchResults() {
        return this.browserService.getDriver().findElement(searchResultsBy);
    }

    public List<WebElement> getSearchResultItems() {
        return browserService.getWaiter().waitForVisibilityOfAllElements(searchResultItemsBy);
    }

    public GameItemFromSearchResults getFirstFoundGameItemFromSearchInput() {
        return new GameItemFromSearchResults(
                getSearchResultItems().get(0).findElement(itemNameFromSearchResultsInputBy),
                getSearchResultItems().get(0).findElement(itemFinalPriceFromSearchResultsInputBy)
        );
    }
}
