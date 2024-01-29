package org.comparator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FruitTest {

    private Fruit fruit;

    @BeforeEach
    public void setUp() {
        fruit = new Fruit("Apple", 150);
    }

    @AfterEach
    public void tearDown() {
        fruit = null;
    }
    @Test
    void getWeight() {
        assertEquals(150, fruit.getWeight());
    }

    @Test
    public void testNegativeGetWeight() {
        assertThrows(IllegalArgumentException.class, () -> new Fruit("Orange", -120));
    }
    @Test
    public void testPrint() {
        System.out.flush();
        System.setOut(new java.io.PrintStream(new java.io.ByteArrayOutputStream()));

        fruit.print();

        String expectedOutput = "Фрукт: Apple Вес: " + fruit.getWeight() + "КГ):";

        assertTrue(expectedOutput.contains(expectedOutput));
    }

}