package jrpg.model;

public abstract class Creature {
    private int life;
    private int level;

    public Creature() {
    }

    public Creature(int life, int level) {
        this.life = life;
        this.level = level;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "life=" + life +
                ", level=" + level +
                '}';
    }
}
