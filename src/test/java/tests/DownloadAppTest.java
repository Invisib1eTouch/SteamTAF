package tests;

import baseEntities.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AboutPage;
import steps.MainPageSteps;
import testData.StaticProvider;

import java.io.File;

public class DownloadAppTest extends BaseTest {

    @Test(dataProvider = "InstallAppNames", dataProviderClass = StaticProvider.class)
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
        while (!flag) {
            if (folder.listFiles().length < 1) {
                Thread.sleep(1000);
            } else {
                File[] listOfFiles = folder.listFiles();

                boolean found = false;

                for (File listOfFile : listOfFiles) {
                    if (listOfFile.isFile()) {
                        if (listOfFile.getName().matches(downloadedFileName)) {
                            File f = new File(String.valueOf(listOfFile.getAbsoluteFile()));
                            found = true;
                            f.delete();
                        }
                    }
                }
                Assert.assertTrue(found, "Downloaded document is not found");
                flag = true;
            }
        }
    }
}
