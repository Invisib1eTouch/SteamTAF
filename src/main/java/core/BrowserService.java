package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.NoSuchElementException;

public class BrowserService {
    private WebDriver driver;

    public BrowserService() {
        PropertyReader propertyReader = new PropertyReader();

        DriverManagerType driverManagerType;
        switch (propertyReader.getBrowserName().toLowerCase()) {
            case "chrome" -> {
                driverManagerType = DriverManagerType.CHROME;
                WebDriverManager.getInstance(driverManagerType).setup();
                driver = new ChromeDriver();
            }
            case "firefox" -> {
                driverManagerType = DriverManagerType.FIREFOX;
                WebDriverManager.getInstance(driverManagerType).setup();
                driver = new FirefoxDriver();
            }

            default -> throw new NoSuchElementException();

        }
    }

    public WebDriver getDriver(){
        return this.driver;
    }

}
