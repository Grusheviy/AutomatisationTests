package montyhall;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MontyHallRoundTest {

    /**
     * Тест проверяет, что метод switchDoor возвращает допустимую новую дверь после открытия двери ведущего.
     */
    @Test
    void testSwitchDoorValidDoorReturned() {
        MontyHallRound round = new MontyHallRound(1);
        round.makeChoice(0);
        round.hostOpenDoor();

        int newChosenDoor = round.switchDoor();

        assertTrue(newChosenDoor == 1 || newChosenDoor == 2);
    }

    /**
     * Тест проверяет, что метод isWinner возвращает true для правильной двери.
     */
    @Test
    void testIsWinnerReturnsTrueForCorrectDoor() {
        MontyHallRound round = new MontyHallRound(2);
        round.makeChoice(1);

        assertTrue(round.isWinner(2));
    }

    /**
     * Тест проверяет, что метод makeChoice не выбрасывает исключение при передаче недопустимого значения двери.
     */
    @Test
    void testMakeChoiceWithInvalidDoor() {
        MontyHallRound round = new MontyHallRound(1);
        assertDoesNotThrow(() -> round.makeChoice(-1));
    }
}