package jrpg.model;

public class Player extends Creature {
    private int maxLife;
    private int power;
    private int maxPower;

    public Player() {
    }

    public Player(int life, int level, int maxLife,  int power, int maxPower) {
        super(life, level);
        this.maxLife = maxLife;
        this.power = power;
        this.maxPower = maxPower;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
    }

    @Override
    public String toString() {
        return "Player{" +
                "maxLife=" + maxLife +
                ", power=" + power +
                ", maxPower=" + maxPower +
                "} " + super.toString();
    }
}
