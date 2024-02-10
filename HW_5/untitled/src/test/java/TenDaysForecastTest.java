
import io.restassured.response.Response;
import DailyDorecast.DailyForecast;
import DailyDorecast.DayNightInfo;
import DailyDorecast.Temperature;
import DailyDorecast.TemperatureInfo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TenDaysForecastTest extends AbstractTest {

    private static final String ACCUWEATHER_FORECAST_10DAY = "/forecasts/v1/daily/10day/";
    private static final String LOCATION_KEY = "50";
    private static final String API_KEY = "FFD4otcIaRRb3PAk0Y2mzayFIbOZ1oAr";

    private static final Logger logger = LoggerFactory.getLogger(OneDayForecastTest.class);

    /**
     * Тест на проверку успешного ответа с кодом 200.
     */
    @Test
    @Order(1)
    void getAccuWeatherTenDayForecast_shouldReturn200() throws IOException, URISyntaxException {

        logger.info("Тест кода ответа 200 запущен");
        logger.debug("Формирование мока для GET /forecasts/v1/daily/1day/{locationKey}?{apiKey}");

        stubFor(get(urlEqualTo(ACCUWEATHER_FORECAST_10DAY + LOCATION_KEY + "?" + API_KEY))
                .willReturn(aResponse().withStatus(200).withBody("AccuWeather 10 Days Forecast")));

        String requestUrl = getBaseUrl() + ACCUWEATHER_FORECAST_10DAY + LOCATION_KEY + "?" + API_KEY;
        logger.info("Sending request to: {}", requestUrl);

        Response response = given()
                .when()
                .get(requestUrl)
                .then()
                .statusCode(200)
                .extract().response();

        assertEquals(200, response.getStatusCode());
        assertEquals("AccuWeather 10 Days Forecast", response.getBody().asString());

        logger.info("Тест кода ответа 200 завершен");
    }

    /**
     * Тест на проверку ответа с кодом 500.
     */
    @Test
    @Order(2)
    void getAccuWeatherTenDayForecast_shouldReturn500() throws IOException, URISyntaxException {
        logger.info("Тест кода ответа 500 запущен");

        logger.debug("Формирование мока для GET /forecasts/v1/daily/1day/{locationKey}?{apiKey}");
        stubFor(get(urlPathEqualTo(ACCUWEATHER_FORECAST_10DAY + LOCATION_KEY))
                .willReturn(aResponse().withStatus(500)
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Server encountered an unexpected condition which prevented it from fulfilling the request")));

        String requestUrl = getBaseUrl() + ACCUWEATHER_FORECAST_10DAY + LOCATION_KEY;
        logger.info("Sending request to: {}", requestUrl);

        String response = given()
                .when()
                .get(requestUrl)
                .then()
                .statusCode(500)
                .extract().body().asString();

        assertEquals("Server encountered an unexpected condition which prevented it from fulfilling the request", response);

        logger.info("Тест кода ответа 500 завершен");
    }

    /**
     * Тест на проверку успешного ответа с ожидаемым содержанием.
     */
    @Test
    @Order(3)
    void getAccuWeatherTenDayForecast_shouldReturnExpectedResponse() throws IOException, URISyntaxException {
        logger.info("Тест на проверку ответа запущен");

        // Формирование мока для GET /forecasts/v1/daily/10day/{locationKey}?{apiKey}
        stubFor(get(urlPathEqualTo(ACCUWEATHER_FORECAST_10DAY + LOCATION_KEY))
                .willReturn(aResponse().withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{" +
                                "\"DailyForecasts\":" +
                                " [" +
                                "{ \"Date\": \"2024-02-10\"," +
                                " \"EpochDate\": 1707573600," +
                                "\"Temperature\":" +
                                "{ \"Minimum\":" +
                                "{ \"Value\": -5.0," +
                                "\"Unit\": \"C\" }," +
                                "\"Maximum\":" +
                                "{ \"Value\": 2.0," +
                                "\"Unit\": \"C\" } }," +
                                "\"Day\":" +
                                "{ \"Icon\": 5," +
                                "\"IconPhrase\": \"Showers\"," +
                                "\"ShortPhrase\": \"Cloudy with showers\"," +
                                "\"LongPhrase\": \"Cloudy with showers\"," +
                                "\"HasPrecipitation\": true }," +
                                "\"Night\":" +
                                "{ \"Icon\": 37," +
                                "\"IconPhrase\": \"Snow\"," +
                                "\"ShortPhrase\": \"Cloudy with a snow shower\"," +
                                "\"LongPhrase\": \"Cloudy with a snow shower\"," +
                                "\"HasPrecipitation\": true }," +
                                "\"Sources\": [ \"AccuWeather\" ]," +
                                "\"MobileLink\": \"http://www.accuweather.com/en/ca/athabasca/t9s/daily-weather-forecast/50?lang=en-us\"," +
                                "\"Link\": \"http://www.accuweather.com/en/ca/athabasca/t9s/daily-weather-forecast/50?lang=en-us\" }" +
                                "]" +
                                "}")));

        String requestUrl = getBaseUrl() + ACCUWEATHER_FORECAST_10DAY + LOCATION_KEY;
        logger.info("Sending request to: {}", requestUrl);

        List<DailyForecast> response = given()
                .when()
                .get(requestUrl)
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().body().jsonPath().getList("DailyForecasts", DailyForecast.class);

        assertEquals(1, response.size());

        // Создаем ожидаемый объект DailyForecast
        DailyForecast expectedForecast = new DailyForecast();
        expectedForecast.setDate("2024-02-10");
        expectedForecast.setEpochDate(1707573600);

        // Проверка для объекта Temperature
        TemperatureInfo minimumTemperatureInfo = new TemperatureInfo();
        minimumTemperatureInfo.setValue(-5.0);
        minimumTemperatureInfo.setUnit("C");

        TemperatureInfo maximumTemperatureInfo = new TemperatureInfo();
        maximumTemperatureInfo.setValue(2.0);
        maximumTemperatureInfo.setUnit("C");

        Temperature expectedTemperature = new Temperature();
        expectedTemperature.setMinimum(minimumTemperatureInfo);
        expectedTemperature.setMaximum(maximumTemperatureInfo);

        // Проверка для объекта DayNightInfo (Day)
        DayNightInfo expectedDayInfo = new DayNightInfo();
        expectedDayInfo.setIcon(5);
        expectedDayInfo.setIconPhrase("Showers");
        expectedDayInfo.setShortPhrase("Cloudy with showers");
        expectedDayInfo.setLongPhrase("Cloudy with showers");
        expectedDayInfo.setHasPrecipitation(true);
        expectedForecast.setDay(expectedDayInfo);

        // Получаем фактический объект DailyForecast
        DailyForecast actualForecast = response.get(0);

        // Проверки для каждого поля
        assertEquals(expectedForecast.getDate(), actualForecast.getDate());
        assertEquals(expectedForecast.getEpochDate(), actualForecast.getEpochDate());

        // Проверки для объекта Temperature
        Temperature actualTemperature = actualForecast.getTemperature();
        assertAll(
                () -> assertEquals(expectedTemperature.getMinimum().getValue(), actualTemperature.getMinimum().getValue()),
                () -> assertEquals(expectedTemperature.getMinimum().getUnit(), actualTemperature.getMinimum().getUnit()),
                () -> assertEquals(expectedTemperature.getMaximum().getValue(), actualTemperature.getMaximum().getValue()),
                () -> assertEquals(expectedTemperature.getMaximum().getUnit(), actualTemperature.getMaximum().getUnit())
        );

        // Проверки для объекта DayNightInfo (Day)
        DayNightInfo actualDayInfo = actualForecast.getDay();

        assertAll(
                () -> assertEquals(expectedDayInfo.getIcon(), actualDayInfo.getIcon()),
        () -> assertEquals(expectedDayInfo.getIconPhrase(), actualDayInfo.getIconPhrase()),
        () -> assertEquals(expectedDayInfo.getShortPhrase(), actualDayInfo.getShortPhrase()),
        () -> assertEquals(expectedDayInfo.getLongPhrase(), actualDayInfo.getLongPhrase()),
        () -> assertEquals(expectedDayInfo.isHasPrecipitation(), actualDayInfo.isHasPrecipitation())
        );

        logger.info("Тест на проверку ответа завершен");
    }
}
