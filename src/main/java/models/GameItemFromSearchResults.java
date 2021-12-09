package models;

import org.openqa.selenium.WebElement;
import pages.GameGenrePage;
import utils.Utils;

public class GameItemFromSearchResults {
    public WebElement name;
    public WebElement price;

    public GameItemFromSearchResults(WebElement name, WebElement price) {
        this.name = name;
        this.price = price;
    }

    public String getNameAsString() {
        return Utils.replaceStringWithoutTradeMark(name.getText());
    }

    public Double getFinalPriceAsDouble() {
        return GameGenrePage.getFinalPrice(price);
    }

    public WebElement getName() {
        return name;
    }

    public WebElement getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "GameItemFromSearchResults{" +
                "name=" + name +
                ", price=" + price +
                '}';
    }
}
