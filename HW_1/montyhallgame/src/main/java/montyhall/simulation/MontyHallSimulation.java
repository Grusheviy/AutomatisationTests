package montyhall.simulation;

import montyhall.MontyHallGame;
import montyhall.ResultsDisplay;

public class MontyHallSimulation {
    public static void main(String[] args) {
        int totalGames = 1000;

        MontyHallGame game = new MontyHallGame(totalGames);
        game.runGame();

        ResultsDisplay resultsDisplay = game.getResultsDisplay();
        resultsDisplay.displayResults();
    }
}