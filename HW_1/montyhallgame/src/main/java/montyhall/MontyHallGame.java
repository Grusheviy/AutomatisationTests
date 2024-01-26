package montyhall;

import java.util.Random;

public class MontyHallGame {
    private int totalGames;
    private int switchWins;
    private int stayWins;


    /**
     * Конструктор класса MontyHallGame.
     * Инициализируем общее количество игр, убеждаемся что они положительны.
     * Инициализируем счетчики выигрышей при выборе стратегий.
     */
    public MontyHallGame(int totalGames) {
        if (totalGames <= 0) {
            throw new IllegalArgumentException("totalGames must be a positive number");
        }
        this.totalGames = totalGames;
        this.switchWins = 0;
        this.stayWins = 0;
    }


    /**
     * Метод runGame запускает серию игр в соответствии с общим количеством игр.
     * В каждой игре создается раунд, в котором игрок делает выбор, ведущий открывает одну из дверей,
     * и затем игрок решает оставить выбранную дверь или открыть другую дверь.
     * Результаты каждой игры учитываются в счетчиках выигрышей.
     */
    public void runGame() {
        Random random = new Random();

        for (int i = 0; i < totalGames; i++) {
            MontyHallRound round = new MontyHallRound(random.nextInt(3));
            round.makeChoice(random.nextInt(3));
            round.hostOpenDoor();

            int newChosenDoor = round.switchDoor();

            if (round.isWinner(newChosenDoor)) {
                switchWins++;
            } else {
                stayWins++;
            }
        }
    }

    /**
     * Метод getResultsDisplay возвращает объект ResultsDisplay, который содержит результаты игр.
     */
    public ResultsDisplay getResultsDisplay() {
        return new ResultsDisplay(switchWins, stayWins);
    }
}