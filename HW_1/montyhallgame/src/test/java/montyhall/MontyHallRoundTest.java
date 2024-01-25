package montyhall;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MontyHallRoundTest {

    @Test
    void test_switchDoor_validDoorReturned() {
        MontyHallRound round = new MontyHallRound(1);
        round.makeChoice(0);
        round.hostOpenDoor();

        int newChosenDoor = round.switchDoor();

        assertTrue(newChosenDoor == 1 || newChosenDoor == 2);
    }

    @Test
    void test_isWinner_returnsTrueForCorrectDoor() {
        MontyHallRound round = new MontyHallRound(2);
        round.makeChoice(1);

        assertTrue(round.isWinner(2));
    }

    @Test
    void test_makeChoice_withInvalidDoor() {
        MontyHallRound round = new MontyHallRound(1);
        assertDoesNotThrow(() -> round.makeChoice(-1));
    }
}