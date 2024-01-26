package montyhall;

    /**
     * Класс ResultsDisplay представляет объект для отображения результатов игр в Monty Hall.
     * Этот класс содержит информацию о количестве выигрышей при стратегии смены и стратегии удержания.
     */
public class ResultsDisplay {
    private int switchWins;
    private int stayWins;

    /**
     * Конструктор класса ResultsDisplay.
     * Инициализируем объект с количеством выигрышей при стратегии смены и стратегии удержания.
     * @param switchWins Количество выигрышей при стратегии смены.
     * @param stayWins Количество выигрышей при стратегии удержания.
     * @throws IllegalArgumentException Если переданы отрицательные значения.
     */
    public ResultsDisplay(int switchWins, int stayWins) {
        if (switchWins < 0 || stayWins < 0) {
            throw new IllegalArgumentException("Отрицательные значения не допускаются");
        }
        this.switchWins = switchWins;
        this.stayWins = stayWins;
    }

    /**
     * Метод getSwitchWins возвращает количество выигрышей при стратегии смены.
     * @return Количество выигрышей при стратегии смены.
     */
    public int getSwitchWins() {
        return switchWins;
    }

    /**
     * Метод getStayWins возвращает количество выигрышей при стратегии удержания.
     * @return Количество выигрышей при стратегии удержания.
     */
    public int getStayWins() {
        return stayWins;
    }

    /**
     * Метод getTotalGames возвращает общее количество сыгранных игр.
     * @return Общее количество сыгранных игр.
     */
    public int getTotalGames() {
        return switchWins + stayWins;
    }

     /**
      * Метод displayResults форматирует и отображает результаты игр в виде строки.
      * @return Строка с отформатированными результатами.
      */
    public String displayResults() {
        return "Игр с изменением выбора двери (стратегия смены): " + switchWins + "\n" +
                "Игр без изменения выбора двери (стратегия удержания): " + stayWins;
    }
}

