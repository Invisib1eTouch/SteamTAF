package tests;

import baseEntities.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AboutPage;
import steps.MainPageSteps;

import java.io.File;

public class DownloadAppTest extends BaseTest {

    @Test
    public void downloadApplicationTest() throws InterruptedException {
        MainPageSteps mainPageSteps = new MainPageSteps(browserService, true);

        AboutPage aboutPage = mainPageSteps
                .proceedToAboutPageByButton()
                .getPageInstance();

        aboutPage.getInstallSteamForWindowsBtn().click();

//        Thread.sleep(15000);

//        File folder = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "DownloadedFiles");
        File folder = new File(System.getProperty("user.dir"));

        boolean flag = false;
        while (!flag) {
            if (folder.listFiles().length < 1) {
                Thread.sleep(1000);
            } else {

                File[] listOfFiles = folder.listFiles();

                boolean found = false;
                File f = null;

                for (File listOfFile : listOfFiles) {
                    if (listOfFile.isFile()) {
                        String fileName = listOfFile.getName();
                        if (listOfFile.getName().contains(".exe")) {
//                            String fileName = listOfFile.getName();
//                        if (listOfFile.getName().contains(".exe")) {
                            f = new File(fileName);
                            found = true;
                        }
                    }
                }
                Assert.assertTrue(found, "Downloaded document is not found");
                f.deleteOnExit();
                flag = true;
            }
        }


    }
}
