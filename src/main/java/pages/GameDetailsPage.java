package pages;

import core.BrowserService;
import models.GenreCatalogGameItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class GameDetailsPage extends CommonHeader {
    private static final By ageDropdownBy = By.id("ageYear");
    private static final By viewPageBtnBy = By.xpath("//a[@onclick = 'ViewProductPage(); return false;']");
    private static final By pageContentBy = By.className("page_content_ctn");
    private static final By gameAreaPurchaseGameItemBy = By.xpath("//div[@class = 'game_area_purchase_game' or @class = 'game_area_purchase_game ']");
    private static final By gameNameFromPurchaseGameItemBy = By.tagName("h1");
    private static final By gameFinalPriceBy = By.className("discount_final_price");
    private static final By gameOriginalPriceBy = By.className("discount_original_price");
    private static final By gamePurchasePriceBy = By.cssSelector(".game_purchase_price.price");
    private static final By gameDiscountBy = By.className("discount_pct");
    private static final By winPlatformIconBy = By.className("win");
    private static final By macPlatformIconBy = By.className("mac");
    private static final By linuxPlatformIconBy = By.className("linux");

    public GameDetailsPage(BrowserService browserService) {
        super(browserService, null);
    }

    @Override
    protected By getIndicatorThatPageOpenedElementLocator() {
        return pageContentBy;
    }

    public Select getAgeDropdown() {
        return new Select(browserService.getDriver().findElement(ageDropdownBy));
    }

    public WebElement getViewPageBnt() {
        return browserService.getDriver().findElement(viewPageBtnBy);
    }

    public By getViewPageBtnBy() {
        return viewPageBtnBy;
    }

    public By getGameAreaPurchaseGameItemBy() {
        return gameAreaPurchaseGameItemBy;
    }

    public WebElement getGameAreaPurchaseGameItem() {
        return browserService.getDriver().findElements(gameAreaPurchaseGameItemBy).get(0);
    }

    public GenreCatalogGameItem getGameItemFromDetailPage() {

        return new GenreCatalogGameItem(
                /**
                 * Create get() for extracting name from getGameAreaPurchaseGameItem()
                 * */
                Utils.replaceStringWithoutTradeMark(getGameAreaPurchaseGameItem().findElement(gameNameFromPurchaseGameItemBy).getText().replaceFirst("^\\w+\\s", "")),
                getPriceWithDiscountIfExists(getGameAreaPurchaseGameItem()),
                getFinalPrice(getGameAreaPurchaseGameItem()),
                getDiscountIfExists(getGameAreaPurchaseGameItem()),
                getPlatforms(getGameAreaPurchaseGameItem())
        );
    }

    private Double getPriceWithDiscountIfExists(WebElement gameItem) {
        if (gameItem.findElements(gameOriginalPriceBy).size() > 0) {
            return Utils.getNumberFormString(gameItem.findElement(gameOriginalPriceBy).getText());
        } else {
            return null;
        }
    }

    private Double getFinalPrice(WebElement gameItem) {
        if (gameItem.findElements(gamePurchasePriceBy).size() > 0) {
            if (gameItem.findElement(gamePurchasePriceBy).getText().contains("Free")) {
                return 0.0;
            } else {
                return Utils.getNumberFormString(gameItem.findElement(gamePurchasePriceBy).getText());
            }
        } else if (gameItem.findElements(gameFinalPriceBy).size() > 0) {
            return Utils.getNumberFormString(gameItem.findElement(gameFinalPriceBy).getText());
        } else {
            return 0.0;
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
}
