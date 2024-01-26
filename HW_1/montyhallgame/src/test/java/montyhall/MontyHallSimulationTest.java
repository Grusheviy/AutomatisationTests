package montyhall;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MontyHallSimulationTest {

    /**
     * Тест проверяет, что результаты симуляции Monty Hall (игры с заданным числом раундов) верны.
     */
    @Test
    void testRunSimulationCheckResults() {
        int totalGames = 1000;

        MontyHallGame game = new MontyHallGame(totalGames);
        game.runGame();

        ResultsDisplay resultsDisplay = game.getResultsDisplay();

        // Проверка, что сумма выигранных сменой двери и удержанием равна общему числу игр
        assertEquals(totalGames, resultsDisplay.getSwitchWins() + resultsDisplay.getStayWins());
    }
}