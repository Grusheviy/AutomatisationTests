package LocationsAPI;

import AccuweatherAbstractTest.AccuweatherAbstractTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;;
import static io.restassured.RestAssured.*;

public class testAdminAreaList extends AccuweatherAbstractTest {

    @Test
    public void testAdminAreaList() {
        AdminArea[] adminAreas = given()
                .queryParam("apikey", getApiKey())
                .when().get(getBaseUrl() + "/locations/v1/adminareas/RU")
                .then()
                .statusCode(200)
                .extract().as(AdminArea[].class);

        assertAll(
                () -> assertNotNull(adminAreas),
                () -> assertNotNull(adminAreas[0].getId()),
                () -> assertNotNull(adminAreas[0].getLocalizedName()),
                () -> assertNotNull(adminAreas[0].getEnglishName()),
                () -> assertNotNull(adminAreas[0].getLevel()),
                () -> assertNotNull(adminAreas[0].getLocalizedType()),
                () -> assertNotNull(adminAreas[0].getEnglishType()),
                () -> assertNotNull(adminAreas[0].getCountryID())
                );

        assertAll(
                () -> assertEquals("AD", adminAreas[0].getId()),
                () -> assertEquals("Adygeya", adminAreas[0].getLocalizedName()),
                () -> assertEquals("Adygeya", adminAreas[0].getEnglishName()),
                () -> assertEquals(1, adminAreas[0].getLevel()),
                () -> assertEquals("Republic", adminAreas[0].getLocalizedType()),
                () -> assertEquals("Republic", adminAreas[0].getEnglishType()),
                () -> assertEquals("RU", adminAreas[0].getCountryID())
        );
    }
}
