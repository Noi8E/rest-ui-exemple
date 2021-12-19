import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

public class TestBase {

    public final String USER_EMAIL = "mail@test.com";
    public final String USER_PASSWORD = "password";
    public final String HARD_COOKIE = "a578b8a0-72b1-4a15-91e8-65255bc54583";
    public static final String baseUrl = "http://demowebshop.tricentis.com";


    //todo разобраться с получением куки на старте тестов!
//    public static final String COOKIE() {
//        String myCookie =
//                given()
//                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
//                        .when()
//                        .get(baseUrl)
//                        .then()
//                        .statusCode(200)
//                        .extract().cookie("Nop.customer");
//        System.out.println("Куки для теста = " + myCookie);
//        return myCookie;
//    }

    @BeforeAll
    static void setUp() {

        Configuration.browserSize = "600x600";


    }


}