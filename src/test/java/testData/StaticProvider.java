package testData;

import org.testng.annotations.DataProvider;

public class StaticProvider {

    @DataProvider(name = "InvalidUserNameAndPassword")
    public static Object[][] loginWithInvalidUserNameAndPassword() {
        return new Object[][]{
                {"!@#$%^&*()_+-=`~[]{}|;':,./<>?", "!@#$%^&*()_+-=`~[]{}|;':,./<>?"},
                {"人人都爱喝可乐", "人人都爱喝可乐"},
                {"<b>text</b>", "<b>text</b>"},
                {"SELECT * FROM USERS", "SELECT * FROM USERS"},
                {"javascript:alert(‘I have a bad news for you’);", "javascript:alert(‘I have a bad news for you’);"}
        };
    }

}
