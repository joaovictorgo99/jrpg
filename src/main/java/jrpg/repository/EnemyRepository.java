package jrpg.repository;

import jrpg.model.Enemy;

import java.util.ArrayList;
import java.util.List;

public class EnemyRepository {
    private static final List<Enemy> enemies = new ArrayList<>();

    public EnemyRepository() {
    }

    public static void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public static List<Enemy> getEnemies() {
        return enemies;
    }
}
