package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import utils.Listener;
import utils.Waiter;

import java.io.File;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class BrowserService {
    private WebDriver driver;

    public BrowserService() {
        DriverManagerType driverManagerType;
        if (PropertyReader.getBrowserName().equalsIgnoreCase("Chrome")) {
            driverManagerType = DriverManagerType.CHROME;
            WebDriverManager.getInstance(driverManagerType).setup();
            HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "DownloadedFiles");
            chromePrefs.put("download.prompt_for_download", false);
            chromePrefs.put("download.extensions_to_open", "application/xml");
            chromePrefs.put("safebrowsing.enabled", true);
            ChromeOptions optionsChrome = new ChromeOptions();
            optionsChrome.setExperimentalOption("prefs", chromePrefs);
            optionsChrome.addArguments("--start-maximized");
            optionsChrome.addArguments("--safebrowsing-disable-download-protection");
            optionsChrome.addArguments("safebrowsing-disable-extension-blacklist");
            if (PropertyReader.getLocalization().equalsIgnoreCase("ru")) {
                optionsChrome.addArguments("--lang=ru");
            } else {
                optionsChrome.addArguments("--lang=en");
            }
            WebDriverListener listener = new Listener();
            driver = new EventFiringDecorator(listener).decorate(new ChromeDriver(optionsChrome));
        } else if (PropertyReader.getBrowserName().equalsIgnoreCase("firefox")) {
            driverManagerType = DriverManagerType.FIREFOX;
            WebDriverManager.getInstance(driverManagerType).setup();
            FirefoxOptions optionsFirefox = new FirefoxOptions();
            optionsFirefox.addPreference("browser.download.folderList", 2);
            optionsFirefox.addPreference("browser.download.dir", System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "DownloadedFiles");
            optionsFirefox.addPreference("browser.download.useDownloadDir", true);
            optionsFirefox.addPreference("browser.download.viewableInternally.enabledTypes", "");
            optionsFirefox.addPreference("browser.helperApps.neverAsk.openFile", "application/octet-stream");
            optionsFirefox.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
            optionsFirefox.addPreference("permissions.fullscreen.allowed", false);

            if (PropertyReader.getLocalization().equalsIgnoreCase("ru")) {
                optionsFirefox.addPreference("intl.accept_languages", "ru");
            } else {
                optionsFirefox.addPreference("intl.accept_languages", "en");
            }
            WebDriverListener listener = new Listener();
            driver = new EventFiringDecorator(listener).decorate(new FirefoxDriver(optionsFirefox));
            driver.manage().window().maximize();
        } else {
            throw new NoSuchElementException();
        }
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public Waiter getWaiter() {
        return new Waiter(this);
    }

    public Waiter getWaiter(int timeout) {
        return new Waiter(this, timeout);
    }

}
