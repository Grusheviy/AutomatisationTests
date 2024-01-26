package montyhall;

/**
 * Класс MontyHallRound представляет раунд игры "Monty Hall".
 * В каждом раунде игрок выбирает одну из трех дверей, за одной из которых находится приз.
 * Ведущий открывает одну из оставшихся дверей, за которой приза нет.
 * Игрок может решить остаться при своем выборе или переключиться на другую дверь.
 * Этот класс отслеживает распределение приза, выбор игрока, открытую дверь ведущего и результат игры.
 */
public class MontyHallRound {
    private int prizeBehindDoor;
    private int chosenDoor;
    private int doorOpenedByHost;


    /**
     * Конструктор класса MontyHallRound.
     * Инициализируем распределение приза за одной из дверей в текущем раунде.
     * @param prizeBehindDoor Индекс двери с призом.
     */
    public MontyHallRound(int prizeBehindDoor) {
        this.prizeBehindDoor = prizeBehindDoor;
    }

    /**
     * Метод makeChoice устанавливает выбор игрока в текущем раунде.
     * @param chosenDoor Индекс двери выбранной игроком.
     */
    public void makeChoice(int chosenDoor) {
        this.chosenDoor = chosenDoor;
    }

    /**
     * Метод hostOpenDoor моделирует действие ведущего, который открывает дверь,без приза,
     * и которая не была выбрана игроком. Результат хранится в doorOpenedByHost
     */
    public void hostOpenDoor() {
        doorOpenedByHost = 0;
        while (doorOpenedByHost == prizeBehindDoor || doorOpenedByHost == chosenDoor) {
            doorOpenedByHost++;
        }
    }

    /**
     * Метод switchDoor возвращает индекс двери, на которую игрок меняет после открытия двери ведущего.
     * @return Индекс новой выбранной двери после переключения.
     */
    public int switchDoor() {
        return 3 - chosenDoor - doorOpenedByHost;
    }

    /**
     * Метод isWinner проверяет, является ли новый выбор игрока выигрышным.
     * @param newChosenDoor Индекс новой выбранной двери.
     * @return true, если новый выбор игрока является выигрышным, иначе false.
     */
    public boolean isWinner(int newChosenDoor) {
        return newChosenDoor == prizeBehindDoor;
    }
}