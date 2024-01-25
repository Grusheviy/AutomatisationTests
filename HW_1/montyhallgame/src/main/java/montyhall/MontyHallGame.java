package montyhall;

import java.util.Random;

public class MontyHallGame {
    private int totalGames;
    private int switchWins;
    private int stayWins;

    public MontyHallGame(int totalGames) {
        if (totalGames <= 0) {
            throw new IllegalArgumentException("totalGames must be a positive number");
        }
        this.totalGames = totalGames;
        this.switchWins = 0;
        this.stayWins = 0;
    }

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

    public ResultsDisplay getResultsDisplay() {
        return new ResultsDisplay(switchWins, stayWins);
    }
}