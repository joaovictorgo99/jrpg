package jrpg.repository;

import jrpg.model.Battle;

import java.util.ArrayList;
import java.util.List;

public class BattleRepository {
    private static final List<Battle> battles = new ArrayList<>();

    public BattleRepository() {
    }

    public static void addBattle(Battle battle) {
        battles.add(battle);
    }

    public static List<Battle> getBattles() {
        return battles;
    }
}
