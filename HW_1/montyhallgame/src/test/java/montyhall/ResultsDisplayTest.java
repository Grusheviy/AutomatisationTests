package montyhall;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResultsDisplayTest {

    @Test
    void displayResults_validDisplayOutput() {
        ResultsDisplay resultsDisplay = new ResultsDisplay(5, 10);

        assertEquals("Игр с изменением выбора двери (стратегия смены): 5\n" +
                        "Игр без изменения выбора двери (стратегия удержания): 10",
                resultsDisplay.displayResults());
    }

    @Test
    void constructor_withNegativeValues_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new ResultsDisplay(-1, 5));
    }
}
