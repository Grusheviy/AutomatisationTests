package LocationsAPI;

import AccuweatherAbstractTest.AccuweatherAbstractTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;;
import static io.restassured.RestAssured.*;

public class testRegionList extends AccuweatherAbstractTest {

    @Test
    public void testAdminAreaList() {
        Region[] regions = given()
                .queryParam("apikey", getApiKey())
                .when().get(getBaseUrl() + "/locations/v1/regions")
                .then()
                .statusCode(200)
                .extract().as(Region[].class);
        //Проверяем что массисв не пуст и каждый элемент массива должен содержать обязательные поля
        assertAll(
                () -> assertNotNull(regions),
                () -> assertNotNull(regions[0].getId()),
                () -> assertNotNull(regions[0].getLocalizedName()),
                () -> assertNotNull(regions[0].getEnglishName())
        );

        //Проверяем значения параметров в первом элементе массива
        assertAll(
                () -> assertEquals("AFR", regions[0].getId()),
                () -> assertEquals("Africa", regions[0].getLocalizedName()),
                () -> assertEquals("Africa", regions[0].getEnglishName())
        );
    }
}
