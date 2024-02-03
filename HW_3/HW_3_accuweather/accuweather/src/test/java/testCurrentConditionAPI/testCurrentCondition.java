package testCurrentConditionAPI;

import AccuweatherAbstractTest.AccuweatherAbstractTest;
import CurrentConditionsAPI.CurrentCondition;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class testCurrentCondition extends AccuweatherAbstractTest {

    @Test
    public void testCurrentCondition() {
        CurrentCondition[] currentCondition = given()
                .queryParam("apikey", getApiKey())
                .when().get(getBaseUrl() + "/currentconditions/v1/50")
                .then()
                .statusCode(200)
                .extract().as(CurrentCondition[].class);

        /*
         *Проверяем что CurrentCondition не null и содержит обязательные поля
         */
        assertAll(
                () -> assertNotNull(currentCondition),
                () -> assertNotNull(currentCondition[0].getLocalObservationDateTime()),
                () -> assertNotNull(currentCondition[0].getEpochTime()),
                () -> assertNotNull(currentCondition[0].getWeatherText()),
                () -> assertNotNull(currentCondition[0].getWeatherIcon()),
                () -> assertNotNull(currentCondition[0].isHasPrecipitation()),
                () -> assertNull(currentCondition[0].getPrecipitationType()),
                () -> assertNotNull(currentCondition[0].isDayTime()),
                () -> assertNotNull(currentCondition[0].getTemperature().getMetric()),
                () -> assertNotNull(currentCondition[0].getTemperature().getImperial()),
                () -> assertNotNull(currentCondition[0].getMobileLink()),
                () -> assertNotNull(currentCondition[0].getLink())
        );

        /*
         *Проверяем что поля CurrentCondition соответствуют ожидаемым значениям
         */
        assertAll(
                () -> assertEquals("2024-02-03T03:38:00-07:00", currentCondition[0].getLocalObservationDateTime()),
                () -> assertEquals(1706956680, currentCondition[0].getEpochTime()),
                () -> assertEquals("Cloudy", currentCondition[0].getWeatherText()),
                () -> assertEquals(7, currentCondition[0].getWeatherIcon()),
                () -> assertEquals(false, currentCondition[0].isHasPrecipitation()),
                () -> assertEquals(null, currentCondition[0].getPrecipitationType()),
                () -> assertEquals(false, currentCondition[0].isDayTime()),
                () -> assertEquals("http://www.accuweather.com/en/ca/athabasca/t9s/current-weather/50?lang=en-us",
                        currentCondition[0].getMobileLink()),
                () -> assertEquals("http://www.accuweather.com/en/ca/athabasca/t9s/current-weather/50?lang=en-us",
                        currentCondition[0].getLink()),

                /*
                 *Проверяем что поля Temperature в CurrentCondition соответствуют ожидаемым значениям
                 */
                () -> assertEquals(-2.2, currentCondition[0].getTemperature().getMetric().getValue()),
                () -> assertEquals("C", currentCondition[0].getTemperature().getMetric().getUnit()),
                () -> assertEquals(17, currentCondition[0].getTemperature().getMetric().getUnitType()),

                /*
                 *Проверяем что поля Temperature в CurrentCondition соответствуют ожидаемым значениям
                 */
                () -> assertEquals(28.0, currentCondition[0].getTemperature().getImperial().getValue()),
                () -> assertEquals("F", currentCondition[0].getTemperature().getImperial().getUnit()),
                () -> assertEquals(18, currentCondition[0].getTemperature().getImperial().getUnitType())
        );
    }
}
