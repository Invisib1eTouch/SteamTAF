package tests;

import baseEntities.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import steps.MainPageSteps;
import testData.StaticProvider;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "InvalidUserNameAndPassword", dataProviderClass = StaticProvider.class)
    public void negativeLoginTest(String login, String password) {
        MainPageSteps mainPageSteps = new MainPageSteps(browserService, true);
        LoginPage loginPage = mainPageSteps
                .proceedToLoginPageByButton()
                .loginWithIncorrectCredentials(login, password)
                .getPageInstance();

//        try {
//            Thread.sleep(5000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Assert.assertEquals(loginPage.getErrorMessage().getText(), "The account name or password that you have entered is incorrect.");

    }
}
