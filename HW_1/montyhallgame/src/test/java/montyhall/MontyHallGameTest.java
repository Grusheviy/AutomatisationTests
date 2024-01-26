package montyhall;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class MontyHallGameTest {
    private MontyHallGame game;

    /**
     * Метод setUp выполняется перед каждым тестом и инициализирует новый объект MontyHallGame.
     */
    @BeforeEach
    void setUp() {
        game = new MontyHallGame(100);
    }

    /**
     * Тест проверяет, что сумма выигрышей при стратегии смены и удержания равна общему числу игр.
     */
    @Test
    void testSwitchWinsAndStayWinsSumToTotalGames() {
        game.runGame();

        int switchWins = game.getResultsDisplay().getSwitchWins();
        int stayWins = game.getResultsDisplay().getStayWins();
        int totalGames = game.getResultsDisplay().getTotalGames();

        assertEquals(totalGames, switchWins + stayWins);
    }

    /**
     * Тест проверяет, что метод getResultsDisplay возвращает не нулевой объект ResultsDisplay.
     */
    @Test
    void testGetResultsDisplayReturnsNonNullResultsDisplay() {
        ResultsDisplay resultsDisplay = game.getResultsDisplay();

        assertNotNull(resultsDisplay);
    }

    /**
     * Параметризированный тест проверяет, что конструктор MontyHallGame выбрасывает исключение
     * при передаче недопустимого значения общего числа игр (отрицательного, нулевого или отрицательного).
     * @param invalidTotalGames Недопустимое значение общего числа игр.
     */
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, -100})
    void testConstructorWithInvalidTotalGames_throwsException(int invalidTotalGames) {
        assertThrows(IllegalArgumentException.class, () -> new MontyHallGame(invalidTotalGames));
    }
}