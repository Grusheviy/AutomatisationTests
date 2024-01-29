package org.comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class BoxTest {

    private Box box;

    @BeforeEach
    public void setUp() {
        box = new Box();
    }

    @ParameterizedTest
    @CsvSource({
            "Apple, 150, Orange, 120, 270",
            "Banana, 200, Cherry, 50, 250",
            "Grape, 100, Pineapple, 300, 400"
    })
    void addItemAndGetWeight(String fruit1Name, double fruit1Weight, String fruit2Name, double fruit2Weight, double expectedTotalWeight) {
        Container fruit1 = new Fruit(fruit1Name, fruit1Weight);
        Container fruit2 = new Fruit(fruit2Name, fruit2Weight);

        box.addItem(fruit1);
        box.addItem(fruit2);

        assertEquals(expectedTotalWeight, box.getWeight(), 0.01);
    }


    @Test
    public void testNegativeAddItem() {
        assertThrows(IllegalArgumentException.class, () -> {
            box.addItem(null);
        });
    }

    @Test
    public void testPrint() {
        System.out.flush();
        System.setOut(new java.io.PrintStream(new java.io.ByteArrayOutputStream()));

        box.print();

        String expectedOutput = "В коробке (Общий вес: " + box.getWeight() + "КГ):";

        assertTrue(expectedOutput.contains(expectedOutput));
    }
}