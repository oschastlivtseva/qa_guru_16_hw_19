package guru.qa.tests;

import org.junit.jupiter.api.Test;

import static guru.qa.specs.Specs.request;
import static guru.qa.specs.Specs.response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class AddItemToWishlistTest {

    @Test
    public void addItemToWishListByAnonymUser() {
        Integer productId = 61;
        Integer quantity = 7;

        given()
                    .spec(request)
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .formParam("addtocart_" + productId + ".EnteredQuantity", quantity)
                .when()
                    .post("addproducttocart/details/" + productId + "/2")
                .then()
                    .spec(response)
                    .body("success", is(true))
                    .body("message", is("The product has been added to your <a href=\"/wishlist\">wishlist</a>"))
                    .body("updatetopwishlistsectionhtml", is("(" + quantity + ")"));
    }
}
