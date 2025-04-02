package jrpg.model;

public class Game {
    private boolean gameOver;

    public Game() {
    }

    public Game(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
