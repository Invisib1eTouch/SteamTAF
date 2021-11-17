package testData;

import core.PropertyReader;
import org.testng.annotations.DataProvider;

public class StaticProvider {

    @DataProvider(name = "InvalidUserNameAndPassword")
    public static Object[][] loginWithInvalidUserNameAndPassword() {
        if (PropertyReader.getLocalization().equalsIgnoreCase("ru")){
             return new Object[][]{
                    {"!#$%^&*()_+-=`~[]{}|;':,./<>?", "_+-=`~[]{}|;':,./<>?!@#$%^&*()", "Неверное имя аккаунта или пароль."},
                    {"user", "password", "Неверное имя аккаунта или пароль."},
                    {"<b>text</b>", "<b>text</b>", "Неверное имя аккаунта или пароль."},
                    {"SELECT * FROM USERS", "SELECT * FROM USERS", "Неверное имя аккаунта или пароль."},
                    {"javascript:alert(‘I have a bad news for you’);", "javascript:alert(‘I have a bad news for you’);", "Неверное имя аккаунта или пароль."}
            };
        } else {
            return new Object[][]{
                    {"!#$%^&*()_+-=`~[]{}|;':,./<>?", "_+-=`~[]{}|;':,./<>?!@#$%^&*()", "The account name or password that you have entered is incorrect."},
                    {"user", "password", "The account name or password that you have entered is incorrect."},
                    {"<b>text</b>", "<b>text</b>", "The account name or password that you have entered is incorrect."},
                    {"SELECT * FROM USERS", "SELECT * FROM USERS", "The account name or password that you have entered is incorrect."},
                    {"javascript:alert(‘I have a bad news for you’);", "javascript:alert(‘I have a bad news for you’);", "The account name or password that you have entered is incorrect."}
            };
        }
    }

    @DataProvider(name = "InstallAppNames")
    public static Object[][] installationAppNames() {
        return new Object[][]{
                {"SteamSetup.exe", "SteamSetup.exe"},
                {"steam.dmg", "steam.dmg"},
                {"steam.deb", "steam_latest.deb"}
        };
    }

}
