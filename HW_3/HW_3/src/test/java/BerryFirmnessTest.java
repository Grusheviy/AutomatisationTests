import berry.BerryFirmness;
import berry.NamedAPIResource;
import berry.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class BerryFirmnessTest extends AbstractTest {

    @Test
    void testGetBerryFirmnessById() {
        BerryFirmness response = given()
                .when()
                .get(getBaseUrl() + "berry-firmness/1")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body().as(BerryFirmness.class);

        Assertions.assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(1, response.getId()),
                () -> assertEquals("very-soft", response.getName())
        );
//
//        // Проверка списка berries
//        List<NamedAPIResource> berries = response.getBerries();
//        assertNotNull(berries, "Список ягод не должен быть null");
//        assertFalse(berries.isEmpty(), "Список ягод не должен быть пустым");
//        assertEquals(8, berries.size(), "Ожидаемый размер списка ягод: 8");
//
//        // Проверка списка names
//        List<Name> names = response.getNames();
//        assertNotNull(names, "Список имен не должен быть null");
//        assertFalse(names.isEmpty(), "Список имен не должен быть пустым");
//        assertEquals(6, names.size(), "Ожидаемый размер списка имен: 1");
    }
}
