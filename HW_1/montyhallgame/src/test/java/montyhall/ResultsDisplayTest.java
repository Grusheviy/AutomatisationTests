package montyhall;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResultsDisplayTest {

    /**
     * Тест проверяет, что метод displayResults
     * возвращает правильное отформатированное представление результатов.
     */
    @Test
    void testDisplayResultsValidDisplayOutput() {
        ResultsDisplay resultsDisplay = new ResultsDisplay(5, 10);

        assertEquals("Игр с изменением выбора двери (стратегия смены): 5\n" +
                        "Игр без изменения выбора двери (стратегия удержания): 10",
                resultsDisplay.displayResults());
    }

    /**
     * Тест проверяет, что конструктор ResultsDisplay
     * выбрасывает исключение при передаче отрицательных значений.
     */
    @Test
    void testConstructorWithNegativeValuesThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new ResultsDisplay(-1, 5));
    }
}
