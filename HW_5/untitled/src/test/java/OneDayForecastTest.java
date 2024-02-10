
import io.restassured.response.Response;
import DailyDorecast.Headline;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OneDayForecastTest extends AbstractTest {

    private static final String ACCUWEATHER_FORECAST_1DAY = "/forecasts/v1/daily/1day/";
    private static final String LOCATION_KEY = "50";
    private static final String API_KEY = "hToCF7Oq420Ga14UJUDuPHWdH2VtSPC0";

    private static final Logger logger = LoggerFactory.getLogger(OneDayForecastTest.class);

    /**
     * Тест на проверку успешного ответа с кодом 200.
     */
    @Test
    @Order(1)
    void getAccuWeatherOneDayForecast_shouldReturn200() throws IOException, URISyntaxException {

        logger.info("Тест кода ответа 200 запущен");
        logger.debug("Формирование мока для GET /forecasts/v1/daily/1day/{locationKey}?{apiKey}");

        stubFor(get(urlEqualTo(ACCUWEATHER_FORECAST_1DAY + LOCATION_KEY + "?" + API_KEY))
                .willReturn(aResponse().withStatus(200).withBody("AccuWeather Forecast")));

        String requestUrl = getBaseUrl() + ACCUWEATHER_FORECAST_1DAY + LOCATION_KEY + "?" + API_KEY;
        logger.info("Sending request to: {}", requestUrl);

        Response response = given()
                .when()
                .get(requestUrl)
                .then()
                .statusCode(200)
                .extract().response();

        assertEquals(200, response.getStatusCode());
        assertEquals("AccuWeather Forecast", response.getBody().asString());

        logger.info("Тест кода ответа 200 завершен");
    }

    /**
     * Тест на проверку ответа с кодом 401.
     */
    @Test
    @Order(2)
    void getAccuWeatherOneDayForecast_shouldReturn401() throws IOException, URISyntaxException {
        logger.info("Тест кода ответа 401 запущен");

        logger.debug("Формирование мока для GET /forecasts/v1/daily/1day/{locationKey}?{apiKey}");
        stubFor(get(urlEqualTo(ACCUWEATHER_FORECAST_1DAY + LOCATION_KEY))
                .willReturn(aResponse().withStatus(401)
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Unauthorized. API authorization failed")));

        String requestUrl = getBaseUrl() + ACCUWEATHER_FORECAST_1DAY + LOCATION_KEY;
        logger.info("Sending request to: {}", requestUrl);

        String response = given()
                .when()
                .get(requestUrl)
                .then()
                .statusCode(401)
                .extract().body().asString();

        assertEquals("Unauthorized. API authorization failed", response);

        logger.info("Тест кода ответа 401 завершен");
    }

    /**
     * Тест на проверку успешного ответа с ожидаемым содержанием.
     */
    @Test
    @Order(3)
    void getAccuWeatherOneDayForecast_shouldReturnExpectedResponse() throws IOException, URISyntaxException {
        logger.info("Тест на проверку ответа запущен");

        // Формирование мока для GET /forecasts/v1/daily/1day/{locationKey}?{apiKey}
        stubFor(get(urlEqualTo(ACCUWEATHER_FORECAST_1DAY + LOCATION_KEY + "?" + API_KEY))
                .willReturn(aResponse().withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"EffectiveDate\":\"2024-02-10T07:00:00-07:00\"," +
                                "\"EffectiveEpochDate\":1707573600," +
                                "\"Severity\":4," +
                                "\"Text\":\"Snowfall tomorrow will total a coating to an inch\"," +
                                "\"Category\":\"snow\"," +
                                "\"EndDate\":\"2024-02-10T19:00:00-07:00\"," +
                                "\"EndEpochDate\":1707616800," +
                                "\"MobileLink\":\"http://www.accuweather.com/en/ca/athabasca/t9s/daily-weather-forecast/50?lang=en-us\"," +
                                "\"Link\":\"http://www.accuweather.com/en/ca/athabasca/t9s/daily-weather-forecast/50?lang=en-us\"}")));

        String requestUrl = getBaseUrl() + ACCUWEATHER_FORECAST_1DAY + LOCATION_KEY + "?" + API_KEY;
        logger.info("Sending request to: {}", requestUrl);

        Headline response = given()
                .when()
                .get(requestUrl)
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().body().as(Headline.class);

        Headline expectedHeadline = new Headline();
        expectedHeadline.setEffectiveDate("2024-02-10T07:00:00-07:00");
        expectedHeadline.setEffectiveEpochDate(1707573600);
        expectedHeadline.setSeverity(4);
        expectedHeadline.setText("Snowfall tomorrow will total a coating to an inch");
        expectedHeadline.setCategory("snow");
        expectedHeadline.setEndDate("2024-02-10T19:00:00-07:00");
        expectedHeadline.setEndEpochDate(1707616800);
        expectedHeadline.setMobileLink("http://www.accuweather.com/en/ca/athabasca/t9s/daily-weather-forecast/50?lang=en-us");
        expectedHeadline.setLink("http://www.accuweather.com/en/ca/athabasca/t9s/daily-weather-forecast/50?lang=en-us");

        // Проверка, что каждое поле соответствует ожиданиям
        assertEquals(expectedHeadline.getEffectiveDate(), response.getEffectiveDate());
        assertEquals(expectedHeadline.getEffectiveEpochDate(), response.getEffectiveEpochDate());
        assertEquals(expectedHeadline.getSeverity(), response.getSeverity());
        assertEquals(expectedHeadline.getText(), response.getText());
        assertEquals(expectedHeadline.getCategory(), response.getCategory());
        assertEquals(expectedHeadline.getEndDate(), response.getEndDate());
        assertEquals(expectedHeadline.getEndEpochDate(), response.getEndEpochDate());
        assertEquals(expectedHeadline.getMobileLink(), response.getMobileLink());
        assertEquals(expectedHeadline.getLink(), response.getLink());

        logger.info("Тест на проверку ответа завершен");
    }
}
