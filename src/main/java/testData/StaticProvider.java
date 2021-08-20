package testData;

import org.testng.annotations.DataProvider;

public class StaticProvider {

    @DataProvider(name = "InvalidUserNameAndPassword")
    public static Object[][] loginWithInvalidUserNameAndPassword() {
        return new Object[][]{
                {"!#$%^&*()_+-=`~[]{}|;':,./<>?", "_+-=`~[]{}|;':,./<>?!@#$%^&*()"},
                {"user", "password"},
                {"<b>text</b>", "<b>text</b>"},
                {"SELECT * FROM USERS", "SELECT * FROM USERS"},
                {"javascript:alert(‘I have a bad news for you’);", "javascript:alert(‘I have a bad news for you’);"}
        };
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
