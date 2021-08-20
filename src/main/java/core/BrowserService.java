package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.Waiter;

import java.io.File;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class BrowserService {
    private WebDriver driver;

    public BrowserService() {

        DriverManagerType driverManagerType;
        switch (PropertyReader.getBrowserName().toLowerCase()) {
            case "chrome" -> {
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
                optionsChrome.addArguments("--lang=en");
                optionsChrome.addArguments("--start-maximized");
                optionsChrome.addArguments("--safebrowsing-disable-download-protection");
                optionsChrome.addArguments("safebrowsing-disable-extension-blacklist");
                driver = new ChromeDriver(optionsChrome);
            }
            case "firefox" -> {
                driverManagerType = DriverManagerType.FIREFOX;
                WebDriverManager.getInstance(driverManagerType).setup();
//                HashMap<String, Object> firefoxPrefs = new HashMap<String, Object>();
//                firefoxPrefs.put("browser.download.folderList", 2);
//                firefoxPrefs.put("browser.download.dir", System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "DownloadedFiles");
//
//                firefoxPrefs.put("browser.download.useDownloadDir", true);
//                firefoxPrefs.put("browser.download.viewableInternally.enabledTypes", "");
//                FirefoxOptions optionsFirefox = new FirefoxOptions();
//
//                optionsFirefox.


                FirefoxOptions optionsFirefox = new FirefoxOptions();
                optionsFirefox.addPreference("browser.download.folderList", 2);
                optionsFirefox.addPreference("browser.download.dir", System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "DownloadedFiles");
                optionsFirefox.addPreference("browser.download.useDownloadDir", true);
                optionsFirefox.addPreference("browser.download.viewableInternally.enabledTypes", "");
//                optionsFirefox.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf;text/plain;application/text;text/xml;application/xml");

                optionsFirefox.addPreference("browser.helperApps.neverAsk.openFile", "application/octet-stream");
                optionsFirefox.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
                driver = new FirefoxDriver(optionsFirefox);
            }

            default -> throw new NoSuchElementException();

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
