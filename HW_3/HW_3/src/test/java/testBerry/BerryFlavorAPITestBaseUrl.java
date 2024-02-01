package testBerry;

import berry.BerryFlavor;
import berry.FlavorBerryMap;
import berry.Name;
import berry.NamedAPIResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BerryFlavorAPITestBaseUrl extends AbstractTestBaseUrl {

    @Test
    void testGetBerryFlavorById() {

        BerryFlavor response = given()
                .when()
                .get(AbstractTestBaseUrl.getBaseUrl() + "berry-flavor/1")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body().as(BerryFlavor.class);

        List<FlavorBerryMap> berries = response.getBerries();
        NamedAPIResource contestType = response.getContestType();
        List<Name> names = response.getNames();
        Name name = names.get(0);

        Assertions.assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(1, response.getId()),
                () -> assertEquals("spicy", response.getName()),
                () -> assertNotNull(berries),
                () -> assertNotNull(contestType),
                () -> assertNotNull(names),
                () -> assertEquals(5, names.size()),
                () -> assertNotNull(name.getName()),
                () -> assertNotNull(name.getLanguage())
        );
    }
}
