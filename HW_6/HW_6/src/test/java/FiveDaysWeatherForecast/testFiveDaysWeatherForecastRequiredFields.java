package FiveDaysWeatherForecast;

import AccuweatherAbstractTest.AccuweatherAbstractTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import FiveDaysWeatherForecast.Source;

public class testFiveDaysWeatherForecastRequiredFields extends AccuweatherAbstractTest {
/*
*
 */
    @Test
    public void testFiveDaysWeatherForecastRequiredFields() {
        WeatherForecastResponse weatherForecastResponses = given()
                .queryParam("apikey", getApiKey()).pathParam("locationKey",50)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/5day/{locationKey}")
                .then()
                .statusCode(200)
                .extract().as(WeatherForecastResponse.class);

        /*
         *Проверяем что Headline не null и содержит обязательные поля
         */
        assertAll(
                () -> assertNotNull(weatherForecastResponses),
                () -> assertNotNull(weatherForecastResponses.getHeadline()),
                () -> assertNotNull(weatherForecastResponses.getHeadline().getEffectiveDate()),
                () -> assertNotNull(weatherForecastResponses.getHeadline().getSeverity()),
                () -> assertNotNull(weatherForecastResponses.getHeadline().getText()),
                () -> assertNotNull(weatherForecastResponses.getHeadline().getCategory()),
                () -> assertNotNull(weatherForecastResponses.getHeadline().getEndDate()),
                () -> assertNotNull(weatherForecastResponses.getHeadline().getMobileLink()),
                () -> assertNotNull(weatherForecastResponses.getHeadline().getLink()),
                () -> assertNotNull(weatherForecastResponses.getDailyForecasts())
        );

        /*
         *Проверяем что поля Headline соответствуют ожидаемым значениям
         */
        Headline headline = weatherForecastResponses.getHeadline();

        assertAll(
                () -> assertEquals("2024-02-04T19:00:00-07:00", headline.getEffectiveDate()),
                () -> assertEquals(1707098400, headline.getEffectiveEpochDate()),
                () -> assertEquals(4, headline.getSeverity()),
                () -> assertEquals("Snowfall from tomorrow evening into Monday morning will total 1-3 inches", headline.getText()),
                () -> assertEquals("snow", headline.getCategory()),
                () -> assertEquals("2024-02-05T13:00:00-07:00", headline.getEndDate()),
                () -> assertEquals(1707163200, headline.getEndEpochDate()),
                () -> assertEquals("http://www.accuweather.com/en/ca/athabasca/t9s/daily-weather-forecast/50?lang=en-us", headline.getMobileLink()),
                () -> assertEquals("http://www.accuweather.com/en/ca/athabasca/t9s/daily-weather-forecast/50?lang=en-us", headline.getLink())
        );

        /*
         *Проверяем что DailyForecasts не null и содержит обязательные поля
         */
        assertAll(
                () -> assertNotNull(weatherForecastResponses.getDailyForecasts().get(0).getDate()),
                () -> assertNotNull(weatherForecastResponses.getDailyForecasts().get(0).getEpochDate()),
                () -> assertNotNull(weatherForecastResponses.getDailyForecasts().get(0).getTemperature()),
                () -> assertNotNull(weatherForecastResponses.getDailyForecasts().get(0).getDay()),
                () -> assertNotNull(weatherForecastResponses.getDailyForecasts().get(0).getNight()),
                () -> assertNotNull(weatherForecastResponses.getDailyForecasts().get(0).getSources()),
                () -> assertNotNull(weatherForecastResponses.getDailyForecasts().get(0).getMobileLink()),
                () -> assertNotNull(weatherForecastResponses.getDailyForecasts().get(0).getLink())
        );

        /*
         *Проверяем что поля DailyForecasts соответствуют ожидаемым значениям
         */
        assertAll(
                () -> assertEquals("2024-02-02T07:00:00-07:00", weatherForecastResponses.getDailyForecasts().get(0).getDate()),
                () -> assertEquals(1706882400, weatherForecastResponses.getDailyForecasts().get(0).getEpochDate())
        );

        /*
         *Проверяем что поле Temperature соответствуют ожидаемым значениям
         */
        Temperature temperature = weatherForecastResponses.getDailyForecasts().get(0).getTemperature();

        assertAll(
                () -> assertEquals(24.0, temperature.getMinimum().getValue()),
                () -> assertEquals("F", temperature.getMinimum().getUnit()),
                () -> assertEquals(18, temperature.getMinimum().getUnitType()),
                () -> assertEquals(42.0, temperature.getMaximum().getValue()),
                () -> assertEquals("F", temperature.getMaximum().getUnit()),
                () -> assertEquals(18, temperature.getMaximum().getUnitType())
        );

        assertAll(
                // Для поля Day
                () -> assertNotNull(weatherForecastResponses.getDailyForecasts().get(0).getDay().getIcon()),
                () -> assertNotNull(weatherForecastResponses.getDailyForecasts().get(0).getDay().getIconPhrase()),
                () -> assertFalse(weatherForecastResponses.getDailyForecasts().get(0).getDay().isHasPrecipitation()),

                // Для поля Night
                () -> assertNotNull(weatherForecastResponses.getDailyForecasts().get(0).getNight().getIcon()),
                () -> assertNotNull(weatherForecastResponses.getDailyForecasts().get(0).getNight().getIconPhrase()),
                () -> assertFalse(weatherForecastResponses.getDailyForecasts().get(0).getNight().isHasPrecipitation())
        );

        /*
         *Проверяем что поля DayNightInfo соответствуют ожидаемым значениям
         */
        DayNightInfo dayInfo = weatherForecastResponses.getDailyForecasts().get(0).getDay();
        DayNightInfo nightInfo = weatherForecastResponses.getDailyForecasts().get(0).getNight();

        assertAll(
                () -> assertEquals(4, dayInfo.getIcon()),
                () -> assertEquals("Intermittent clouds", dayInfo.getIconPhrase()),
                () -> assertFalse(dayInfo.isHasPrecipitation()),

                () -> assertEquals(38, nightInfo.getIcon()),
                () -> assertEquals("Mostly cloudy", nightInfo.getIconPhrase()),
                () -> assertFalse(nightInfo.isHasPrecipitation())
        );

        /*
         *Проверяем что поля Sources соответствуют ожидаемым значениям
         */
        assertAll(
                () -> assertNotNull(weatherForecastResponses.getDailyForecasts().get(0).getSources()),
                () -> assertFalse(weatherForecastResponses.getDailyForecasts().get(0).getSources().isEmpty()),
                () -> assertEquals("AccuWeather", weatherForecastResponses.getDailyForecasts().get(0).getSources().get(0))
        );

        /*
         *Проверяем что поля MobileLink и Link соответствуют ожидаемым значениям
         */
        assertAll(
                () -> assertNotNull(weatherForecastResponses.getDailyForecasts().get(0).getMobileLink()),
                () -> assertEquals("http://www.accuweather.com/en/ca/athabasca/t9s/daily-weather-forecast/50?lang=en-us",
                        weatherForecastResponses.getDailyForecasts().get(0).getMobileLink())
        );

        assertAll(
                () -> assertNotNull(weatherForecastResponses.getDailyForecasts().get(0).getLink()),
                () -> assertEquals("http://www.accuweather.com/en/ca/athabasca/t9s/daily-weather-forecast/50?lang=en-us",
                        weatherForecastResponses.getDailyForecasts().get(0).getLink())
        );
    }
}
