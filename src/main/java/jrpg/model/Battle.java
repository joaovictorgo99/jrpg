package jrpg.model;

public class Battle {
    private int id;
    private boolean playerWinner;

    public Battle() {
    }

    public Battle(int id, boolean playerWinner) {
        this.id = id;
        this.playerWinner = playerWinner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPlayerWinner() {
        return playerWinner;
    }

    public void setPlayerWinner(boolean playerWinner) {
        this.playerWinner = playerWinner;
    }
}
