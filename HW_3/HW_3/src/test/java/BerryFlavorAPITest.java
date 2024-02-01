
import berry.BerryFlavor;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BerryFlavorAPITest extends AbstractTest {

    @Test
    void testGetBerryFlavorById() {

        BerryFlavor response = given()
                .when()
                .get(getBaseUrl() + "berry-flavor/1")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body().as(BerryFlavor.class);
    }

}
