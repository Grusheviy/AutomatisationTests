import berry.Berry;
import berry.BerryFlavorMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BerrysAPITest extends AbstractTest{

    /*
     * Тестирование API для получения информации о ягоде с ID 1.
     *
     * Шаги:
     * 1. Отправляем запрос к API для получения информации о ягоде с ID 1.
     * 2. Извлекаем ответ и маппим его в объект класса Berry.
     * 3. Проверяем различные атрибуты ягоды, используя Assertions.assertAll().
     *
     * В случае несоответствия хотя бы одной проверки, тест завершится, и будут выведены
     * подробности об ошибках для каждой проведенной проверки.
     */
    @Test
    void testGetBerryById() {
        // Отправляем запрос к API для получения информации о ягоде с ID 1
        Berry response = given()
                .when()
                .get(getBaseUrl() + "berry/1")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body().as(Berry.class);

        // Получаем первый элемент списка вкусов ягоды
        BerryFlavorMap flavorMap = response.getFlavors().get(0);

        // Проводим проверки для каждого атрибута ягоды, используя Assertions.assertAll()
        Assertions.assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(1, response.getId()),
                () -> assertEquals("cheri", response.getName()),
                () -> assertEquals(3, response.getGrowthTime()),
                () -> assertEquals(5, response.getMaxHarvest()),
                () -> assertEquals(60, response.getNaturalGiftPower()),
                () -> assertEquals(20, response.getSize()),
                () -> assertEquals(25, response.getSmoothness()),
                () -> assertEquals(15, response.getSoilDryness()),
                () -> assertEquals("soft", response.getFirmness().getName()),
                () -> assertEquals("spicy", flavorMap.getFlavor().getName()),
                () -> assertEquals(10, flavorMap.getPotency()),
                () -> assertEquals("cheri-berry", response.getItem().getName()),
                () -> assertEquals("fire", response.getNaturalGiftType().getName()));
    }
}
