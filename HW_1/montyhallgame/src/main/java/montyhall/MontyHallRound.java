package montyhall;

public class MontyHallRound {
    private int prizeBehindDoor;
    private int chosenDoor;
    private int doorOpenedByHost;

    public MontyHallRound(int prizeBehindDoor) {
        this.prizeBehindDoor = prizeBehindDoor;
    }

    public void makeChoice(int chosenDoor) {
        this.chosenDoor = chosenDoor;
    }

    public void hostOpenDoor() {
        doorOpenedByHost = 0;
        while (doorOpenedByHost == prizeBehindDoor || doorOpenedByHost == chosenDoor) {
            doorOpenedByHost++;
        }
    }

    public int switchDoor() {
        return 3 - chosenDoor - doorOpenedByHost;
    }

    public boolean isWinner(int newChosenDoor) {
        return newChosenDoor == prizeBehindDoor;
    }
}