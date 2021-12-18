import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;


import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class CartTest extends TestBase {

    @Test
    @DisplayName("Можем добавить товар в корзину без куки в запросе")
    void canAddItemToCartWOCookie() {
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .body("addtocart_14.EnteredQuantity=1")
                        .cookie(HARD_COOKIE)
                        .when()
                        .post(baseUrl +"/addproducttocart/details/14/1")
                        .then()
                        .statusCode(200)
                        .body("success", is(true))
                        .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                        .body("updatetopcartsectionhtml", is("(1)"))
                        .extract().response();
    }
    @Test
    @DisplayName("Можем добавить товар в корзину с сохранением сессии 1 --> 2")
    void canAddItemToCartWithCookie() {
        String cookie =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .formParam("Email", "dfff@dd.dd")
                        .formParam("Password", "9u334fj")
                        .when()
                        .post(baseUrl + "/login")
                        .then()
                        .statusCode(200)
                        .extract()
                        .cookie("NOPCOMMERCE.AUTH");
         open(baseUrl + "/Themes/DefaultClean/Content/images/logo.png");

         getWebDriver().manage().addCookie(new Cookie("Nop.customer", cookie));



        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("addtocart_14.EnteredQuantity=1")
                .cookie(cookie)
                .when()
                .post(baseUrl +"/addproducttocart/details/14/1")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", is("(1)"))
                .extract().response();

    }

}
