import DailyDorecast.DailyForecast;
import LocationsAPI.AdminArea;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.response.Response;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.reflect.Array.get;

public class AdminAreasTest extends AbstractTest{

    private static final Logger logger = LoggerFactory.getLogger(AdminAreasTest.class);

    private static final String ACCUWEATHER_ADMINAREAS = "/locations/v1/adminareas/";
    private static final String COUNTRY_CODE = "RU";
    private static final String API_KEY = "FFD4otcIaRRb3PAk0Y2mzayFIbOZ1oAr";

    /**
     * Тест на проверку успешного ответа с кодом 200.
     */
    @Test
    @Order(1)
    void getAccuWeatherAdminAreas_shouldReturn200() throws IOException, URISyntaxException {

        logger.info("Тест на проверку ответа запущен");
        logger.debug("Формирование мока для GET /locations/v1/adminareas/{countruCode}?apikey={apiKey}");

        // Формирование мока для GET /locations/v1/adminareas/{countruCode}?{apiKey}
        stubFor(WireMock.get(urlEqualTo(ACCUWEATHER_ADMINAREAS + COUNTRY_CODE + "?" + API_KEY))
                .willReturn(aResponse().withStatus(200).withBody("AccuWeather AdminsArea")));

        String requestUrl = getBaseUrl() + ACCUWEATHER_ADMINAREAS + COUNTRY_CODE + "?apikey=" + API_KEY;
        logger.info("Sending request to: {}", requestUrl);

        Response response = given()
                .when()
                .get(requestUrl)
                .then()
                .statusCode(200)
                .extract().response();

        assertEquals(200, response.getStatusCode());
        assertEquals("AccuWeather AdminsArea", response.getBody().asString());

        logger.info("Тест на проверку ответа завершен");
    }

    /**
     * Тест на проверку ответа с кодом 500.
     */
    @Test
    @Order(2)
    void getAccuWeatherTenDayForecast_shouldReturn500() throws IOException, URISyntaxException {
        logger.info("Тест кода ответа 500 запущен");

        logger.debug("Формирование мока для GET /locations/v1/adminareas/{countruCode}?apikey={apiKey}");
        stubFor(WireMock.get(urlPathEqualTo(ACCUWEATHER_ADMINAREAS + COUNTRY_CODE))
                .willReturn(aResponse().withStatus(500)
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Server encountered an unexpected condition which prevented it from fulfilling the request")));

        String requestUrl = getBaseUrl() + ACCUWEATHER_ADMINAREAS + COUNTRY_CODE;
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
    void getAccuWeatherAdminAreas_shouldReturnExpectedResponse() throws IOException, URISyntaxException {
        logger.info("Тест на проверку ответа запущен");

        // Формирование мока для GET Формирование мока для GET /locations/v1/adminareas/{countruCode}?{apiKey}
        stubFor(WireMock.get(urlPathEqualTo(ACCUWEATHER_ADMINAREAS + COUNTRY_CODE + API_KEY))
                .willReturn(aResponse().withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("[\n" +
                                "  {\n" +
                                "    \"ID\": \"AD\",\n" +
                                "    \"LocalizedName\": \"Adygeya\",\n" +
                                "    \"EnglishName\": \"Adygeya\",\n" +
                                "    \"Level\": 1,\n" +
                                "    \"LocalizedType\": \"Republic\",\n" +
                                "    \"EnglishType\": \"Republic\",\n" +
                                "    \"CountryID\": \"RU\"\n" +
                                "  }\n" +
                                "]")));

        String requestUrl = getBaseUrl() + ACCUWEATHER_ADMINAREAS + COUNTRY_CODE + API_KEY;
        logger.info("Sending request to: {}", requestUrl);

        List<AdminArea> response = given()
                .when()
                .get(requestUrl)
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().body().jsonPath().getList(".", AdminArea.class);


        AdminArea expectedAdminArea = new AdminArea();
        expectedAdminArea.setId("AD");
        expectedAdminArea.setLocalizedName("Adygeya");
        expectedAdminArea.setEnglishName("Adygeya");
        expectedAdminArea.setLevel(1);
        expectedAdminArea.setLocalizedType("Republic");
        expectedAdminArea.setEnglishType("Republic");
        expectedAdminArea.setCountryID("RU");

        AdminArea actualAdminArea = response.get(0);

        assertAll(
                () -> assertEquals(expectedAdminArea.getId(), actualAdminArea.getId()),
                () -> assertEquals(expectedAdminArea.getLocalizedName(), actualAdminArea.getLocalizedName()),
                () -> assertEquals(expectedAdminArea.getEnglishName(), actualAdminArea.getEnglishName()),
                () -> assertEquals(expectedAdminArea.getLevel(), actualAdminArea.getLevel()),
                () -> assertEquals(expectedAdminArea.getLocalizedType(), actualAdminArea.getLocalizedType()),
                () -> assertEquals(expectedAdminArea.getEnglishType(), actualAdminArea.getEnglishType()),
                () -> assertEquals(expectedAdminArea.getCountryID(), actualAdminArea.getCountryID())
        );

        logger.info("Тест на проверку ответа завершен");
    }
}
