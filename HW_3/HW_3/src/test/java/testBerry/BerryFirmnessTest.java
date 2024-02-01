package testBerry;

import berry.BerryFirmness;
import berry.NamedAPIResource;
import berry.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class BerryFirmnessTest extends AbstractTest {

    @Test
    void testGetBerryFirmnessById() {
        BerryFirmness response = given()
                .when()
                .get(AbstractTest.getBaseUrl() + "berry-firmness/1")
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
