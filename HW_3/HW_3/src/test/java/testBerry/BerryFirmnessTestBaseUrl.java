package testBerry;

import berry.BerryFirmness;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class BerryFirmnessTestBaseUrl extends AbstractTestBaseUrl {

    @Test
    void testGetBerryFirmnessById() {
        BerryFirmness response = given()
                .when()
                .get(AbstractTestBaseUrl.getBaseUrl() + "berry-firmness/1")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body().as(BerryFirmness.class);

        Assertions.assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(1, response.getId()),
                () -> assertEquals("very-soft", response.getName())
        );
    }
}
