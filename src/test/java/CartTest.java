import com.codeborne.selenide.Condition;
import io.restassured.response.Response;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class CartTest extends TestBase {

    @Test
    @DisplayName("Можем добавить товар в корзину без куки в запросе")
    void canAddItemToCartWOCookie() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_72_5_18=53&product_attribute_72_6_19=54&product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=1")
                .when()
                .post(baseUrl + "/addproducttocart/details/72/1")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", is("(1)"))
                .extract().response();
    }


    @Test
    @DisplayName("Добавляем товар в корзину с куки в запросе, проверяем что кол-во товара != 1")
    void canAddItemToCartWithCookie() {

        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_72_5_18=53&product_attribute_72_6_19=54&product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=1")
                .cookie("Nop.customer" + HARD_COOKIE)
                .when()
                .post(baseUrl + "/addproducttocart/details/72/1")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", not("(1)"));


    }


    @Test
    @DisplayName("UI-проверка на кол-во товара в корзине ")
    void checkCartItemsValue() {
        //Открываем сессию браузера
        open(baseUrl + "/Themes/DefaultClean/Content/images/logo.png");
        //Подкладываем токен
        getWebDriver().manage().addCookie(new Cookie("Nop.customer", HARD_COOKIE));
        //Открываем корзину
        open(baseUrl + "/cart");
        //Проверяем что счетчик айтемов !=1
        $("span.cart-qty").shouldNotHave(Condition.value("1"));


    }

}
