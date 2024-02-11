package LocationsAPI;

import AccuweatherAbstractTest.AccuweatherAbstractTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

public class testTopCities extends AccuweatherAbstractTest {

    @Test
    public void testTopCitties() {
        TopCities[] TopCitiesResponce = given()
                .queryParam("apikey", getApiKey())
                .when().get(getBaseUrl() + "/locations/v1/topcities/50")
                .then()
                .statusCode(200)
                .extract().as(TopCities[].class);

        //Проверяем что массисв не пуст и каждый элемент массива должен содержать обязательные поля
        assertAll(
                () -> assertNotNull(TopCitiesResponce),
                () -> assertNotNull(TopCitiesResponce[0].getVersion()),
                () -> assertNotNull(TopCitiesResponce[0].getKey()),
                () -> assertNotNull(TopCitiesResponce[0].getType()),
                () -> assertNotNull(TopCitiesResponce[0].getRank()),
                () -> assertNotNull(TopCitiesResponce[0].getLocalizedName()),
                () -> assertNotNull(TopCitiesResponce[0].getEnglishName()),
                () -> assertNotNull(TopCitiesResponce[0].getPrimaryPostalCode()),
                () -> assertNotNull(TopCitiesResponce[0].isAlias())
        );

        //Проверяем что поля массива содержат корректные данные
        assertAll(
                () -> assertEquals(1, TopCitiesResponce[0].getVersion()),
                () -> assertEquals("28143", TopCitiesResponce[0].getKey()),
                () -> assertEquals("City", TopCitiesResponce[0].getType()),
                () -> assertEquals(10, TopCitiesResponce[0].getRank()),
                () -> assertEquals("Dhaka", TopCitiesResponce[0].getLocalizedName()),
                () -> assertEquals("Dhaka",TopCitiesResponce[0].getEnglishName()),
                () -> assertEquals("", TopCitiesResponce[0].getPrimaryPostalCode()),
                () -> assertFalse(TopCitiesResponce[0].isAlias())
        );

        //Проверяем что массив Region в запросе TopCities содержит корректные данные
        assertAll(
                () -> assertNotNull(TopCitiesResponce[0].getRegion()),
                () -> assertEquals("ASI", TopCitiesResponce[0].getRegion().getId()),
                () -> assertEquals("Asia", TopCitiesResponce[0].getRegion().getLocalizedName()),
                () -> assertEquals("Asia", TopCitiesResponce[0].getRegion().getEnglishName())
        );

        //Проверяем что массив Country в запросе TopCities содержит корректные данные
        assertAll(
                () -> assertNotNull(TopCitiesResponce[0].getCountry()),
                () -> assertEquals("BD", TopCitiesResponce[0].getCountry().getId()),
                () -> assertEquals("Bangladesh", TopCitiesResponce[0].getCountry().getLocalizedName()),
                () -> assertEquals("Bangladesh", TopCitiesResponce[0].getCountry().getEnglishName())
        );

        //Проверяем что массив AdministrativeArea в запросе TopCities содержит корректные данные
        assertAll(
                () -> assertEquals("C", TopCitiesResponce[0].getAdministrativeArea().getId()),
                () -> assertEquals("Dhaka", TopCitiesResponce[0].getAdministrativeArea().getLocalizedName()),
                () -> assertEquals("Dhaka", TopCitiesResponce[0].getAdministrativeArea().getEnglishName()),
                () -> assertEquals(1, TopCitiesResponce[0].getAdministrativeArea().getLevel()),
                () -> assertEquals("Division", TopCitiesResponce[0].getAdministrativeArea().getLocalizedType()),
                () -> assertEquals("Division", TopCitiesResponce[0].getAdministrativeArea().getEnglishType()),
                () -> assertEquals("BD", TopCitiesResponce[0].getAdministrativeArea().getCountryID())
        );

        //Проверяем что массив TimeZone в запросе TopCities содержит корректные данные
        assertAll(
                () -> assertEquals("BDT", TopCitiesResponce[0].getTimeZone().getCode()),
                () -> assertEquals("Asia/Dhaka", TopCitiesResponce[0].getTimeZone().getName()),
                () -> assertEquals(6.0, TopCitiesResponce[0].getTimeZone().getGmtOffset()),
                () -> assertFalse(TopCitiesResponce[0].getTimeZone().isDaylightSaving()),
                () -> assertNull(TopCitiesResponce[0].getTimeZone().getNextOffsetChange())
        );

        //Проверяем что массив GeoPosition в запросе TopCities содержит корректные данные
        assertAll(
                () -> assertEquals(23.71, TopCitiesResponce[0].getGeoPosition().getLatitude()),
                () -> assertEquals(90.407, TopCitiesResponce[0].getGeoPosition().getLongitude()),
                () -> assertEquals(6.0, TopCitiesResponce[0].getTimeZone().getGmtOffset())
        );

        //Проверяем что массив Elevation в массиве GeoPosition в запросе TopCities содержит корректные данные
        assertAll(
                //Проверяем Массив Metric
                () -> assertEquals(5.0, TopCitiesResponce[0].getGeoPosition().getElevation().getMetric().getValue()),
                () -> assertEquals("m", TopCitiesResponce[0].getGeoPosition().getElevation().getMetric().getUnit()),
                () -> assertEquals(5, TopCitiesResponce[0].getGeoPosition().getElevation().getMetric().getUnitType()),
                //Проверяем Массив Imperial
                () -> assertEquals(16.0, TopCitiesResponce[0].getGeoPosition().getElevation().getImperial().getValue()),
                () -> assertEquals("ft", TopCitiesResponce[0].getGeoPosition().getElevation().getImperial().getUnit()),
                () -> assertEquals(0, TopCitiesResponce[0].getGeoPosition().getElevation().getImperial().getUnitType())
        );

        //Проверяем что массив DataSet в запросе TopCities содержит обязательные поля
        assertThat(TopCitiesResponce[0].getDataSets(), containsInAnyOrder(
                "AirQualityCurrentConditions",
                "AirQualityForecasts",
                "FutureRadar",
                "MinuteCast"));
    }
}
