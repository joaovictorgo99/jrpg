package jrpg.model;

public class Enemy extends Creature {
    private String type;

    public Enemy() {
    }

    public Enemy(int life, int level, String type) {
        super(life, level);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "type='" + type + '\'' +
                "} " + super.toString();
    }
}
