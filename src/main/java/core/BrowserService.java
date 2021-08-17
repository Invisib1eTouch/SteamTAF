package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.Waiter;

import java.util.NoSuchElementException;

public class BrowserService {
    private WebDriver driver;

    public BrowserService() {

        DriverManagerType driverManagerType;
        switch (PropertyReader.getBrowserName().toLowerCase()) {
            case "chrome" -> {
                driverManagerType = DriverManagerType.CHROME;
                WebDriverManager.getInstance(driverManagerType).setup();
                ChromeOptions optionsChrome = new ChromeOptions();
                optionsChrome.addArguments("--lang=en");
                optionsChrome.addArguments("--start-maximized");
                driver = new ChromeDriver(optionsChrome);
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

    public Waiter getWaiter() {
        return new Waiter(this);
    }

    public Waiter getWaiter(int timeout) {
        return new Waiter(this, timeout);
    }

}
