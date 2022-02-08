import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllBeers {
    @BeforeTest
    public static void setup(){
        RestAssured.baseURI = "https://api.punkapi.com/v2/beers";
    }

    @Test
    public void shouldGetAllBeers(){
        //1. Act
        //2. Arrange
        //3. Assert

        assertApiResponse(given()
                .when()
                .get()
                .then()
                .statusCode(200));

    }

    @Test
    public void shouldGetAllBeersBeforeDate(){
        //1. Act

        assertApiResponse(given()
                .when()

                //2. Arrange
                .get("?brewed_before=10-2011")
                .then()

                //3. Assert
                .statusCode(200)
                .log().body());

    }

    @Test
    public void shouldGetAllBeersAbvGreater(){
        //1. Act
        //2. Arrange
        //3. Assert

        assertApiResponse(given()
                .when()
                .get("?abv_gt=6")

                .then()
                .statusCode(200)
                .log().body());

    }

    @Test
    public void shouldVerifyPagination(){
        //1. Act
        //2. Arrange
        //3. Assert

        assertApiResponse(given()
                .when()
                .get("?page=2&per_page=5")

                .then()
                .statusCode(200)
                .body("",Matchers.hasSize(5))
                .log().body());

    }

    private ValidatableResponse assertApiResponse(ValidatableResponse body) {
        return body
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.notNullValue())
                .body("description", Matchers.notNullValue())
                .body("abv", Matchers.notNullValue());
    }

}
