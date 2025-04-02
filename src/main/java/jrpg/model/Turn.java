package jrpg.model;

public class Turn {
    private int id;
    private boolean playerTurn;
    private boolean defenseOn;
    private boolean criticalDamage;
    private boolean criticalHeal;

    public Turn() {
    }

    public Turn(int id, boolean playerTurn, boolean defenseOn, boolean criticalDamage, boolean criticalHeal) {
        this.id = id;
        this.playerTurn = playerTurn;
        this.defenseOn = defenseOn;
        this.criticalDamage = criticalDamage;
        this.criticalHeal = criticalHeal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public boolean isDefenseOn() {
        return defenseOn;
    }

    public void setDefenseOn(boolean defenseOn) {
        this.defenseOn = defenseOn;
    }

    public boolean isCriticalDamage() {
        return criticalDamage;
    }

    public void setCriticalDamage(boolean criticalDamage) {
        this.criticalDamage = criticalDamage;
    }

    public boolean isCriticalHeal() {
        return criticalHeal;
    }

    public void setCriticalHeal(boolean criticalHeal) {
        this.criticalHeal = criticalHeal;
    }
}
