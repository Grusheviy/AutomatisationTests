package montyhall;

public class ResultsDisplay {
    private int switchWins;
    private int stayWins;

    public ResultsDisplay(int switchWins, int stayWins) {
        if (switchWins < 0 || stayWins < 0) {
            throw new IllegalArgumentException("Отрицательные значения не допускаются");
        }
        this.switchWins = switchWins;
        this.stayWins = stayWins;
    }

    public int getSwitchWins() {
        return switchWins;
    }

    public int getStayWins() {
        return stayWins;
    }

    public int getTotalGames() {
        return switchWins + stayWins;
    }

    public String displayResults() {
        return "Игр с изменением выбора двери (стратегия смены): " + switchWins + "\n" +
                "Игр без изменения выбора двери (стратегия удержания): " + stayWins;
    }
}

