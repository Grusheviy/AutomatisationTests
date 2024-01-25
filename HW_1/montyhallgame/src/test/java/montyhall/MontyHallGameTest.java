package montyhall;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class MontyHallGameTest {
    private MontyHallGame game;

    @BeforeEach
    void setUp() {
        game = new MontyHallGame(100);
    }

    @Test
    void runGame_switchWinsAndStayWinsSumToTotalGames() {
        game.runGame();

        int switchWins = game.getResultsDisplay().getSwitchWins();
        int stayWins = game.getResultsDisplay().getStayWins();
        int totalGames = game.getResultsDisplay().getTotalGames();

        assertEquals(totalGames, switchWins + stayWins);
    }

    @Test
    void getResultsDisplay_returnsNonNullResultsDisplay() {
        ResultsDisplay resultsDisplay = game.getResultsDisplay();

        assertNotNull(resultsDisplay);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, -100})
    void constructor_withInvalidTotalGames_throwsException(int invalidTotalGames) {
        assertThrows(IllegalArgumentException.class, () -> new MontyHallGame(invalidTotalGames));
    }
}