import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

public class TestBase {

    public final String USER_EMAIL = "mail@test.com";
    public final String USER_PASSWORD = "password";
    public final String HARD_COOKIE = "6bb46985-623e-4125-8f2c-ebc34feea1e4";
    public static final String baseUrl = "http://demowebshop.tricentis.com";

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "600x600";
    }

}