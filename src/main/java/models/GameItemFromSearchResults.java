package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import pages.GameGenrePage;
import utils.Utils;

@AllArgsConstructor
@Getter
public class GameItemFromSearchResults {
    public WebElement name;
    public WebElement price;

    public String getNameAsString(){
        return Utils.replaceStringWithoutTradeMark(name.getText());
    }

    public Double getFinalPriceAsDouble(){
        return GameGenrePage.getFinalPrice(price);
    }
}
