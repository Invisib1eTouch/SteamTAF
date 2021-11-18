package tests;

import baseEntities.BaseTest;
import core.PropertyReader;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AboutPage;
import steps.MainPageSteps;
import testData.StaticProvider;

import java.io.File;
import java.util.Objects;

public class DownloadAppTest extends BaseTest {

    @Test(dataProvider = "InstallAppNames", dataProviderClass = StaticProvider.class)
    @Description("Download steam installation app for any available platform.")
    public void downloadWinAppTest(String linkFileName, String downloadedFileName) throws InterruptedException {
        MainPageSteps mainPageSteps = new MainPageSteps(browserService, true);

        AboutPage aboutPage = mainPageSteps
                .proceedToAboutPageByButton()
                .getPageInstance();

        aboutPage.getInstallSteamBtn(linkFileName).click();

        File folder = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "DownloadedFiles");

        if (!folder.exists()) {
            folder.mkdir();
        }

        boolean flag = false;

        int timer = 0;

        while (!flag) {
            if (Objects.requireNonNull(folder.listFiles()).length < 1) {
                timer++;
                Thread.sleep(1000);
            } else {
                File[] listOfFiles = folder.listFiles();

                for (File fileFromList : Objects.requireNonNull(listOfFiles)) {
                    if (fileFromList.isFile()) {
                        if (fileFromList.getName().matches(downloadedFileName)) {
                            File f = new File(String.valueOf(fileFromList.getAbsoluteFile()));
                            flag = true;
                            f.delete();
                            Assert.assertEquals(fileFromList.getName(), downloadedFileName);
                        } else {
                            timer++;
                            Thread.sleep(1000);
                        }
                    }
                }
            }
            if (timer == PropertyReader.getTimeOut()) {
                throw new AssertionError("Timeout for downloading the file.");
            }
        }
    }
}
