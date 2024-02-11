package LocationsAPI;

import AccuweatherAbstractTest.AccuweatherAbstractTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;;
import static io.restassured.RestAssured.*;

public class testCountryList extends AccuweatherAbstractTest {

    @Test
    public void testAdminAreaList() {
        Country[] country = given()
                .queryParam("apikey", getApiKey())
                .when().get(getBaseUrl() + "/locations/v1/countries/ASI")
                .then()
                .statusCode(200)
                .extract().as(Country[].class);

        assertAll(
                () -> assertNotNull(country),
                () -> assertNotNull(country[0].getId()),
                () -> assertNotNull(country[0].getLocalizedName()),
                () -> assertNotNull(country[0].getEnglishName()),
                () -> assertEquals("AF", country[0].getId()),
                () -> assertEquals("Afghanistan", country[0].getLocalizedName()),
                () -> assertEquals("Afghanistan", country[0].getEnglishName())
        );
    }
}
