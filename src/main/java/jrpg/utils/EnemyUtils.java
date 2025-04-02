package jrpg.utils;

import jrpg.model.Enemy;
import jrpg.model.Player;
import jrpg.model.Turn;
import jrpg.repository.GameRepository;
import jrpg.repository.TurnRepository;

public class EnemyUtils {
    public EnemyUtils() {
    }

    public static int attack(Enemy enemy, Player player, Turn turn) {
        int rawDamage = 10;
        int baseDamage = rawDamage + enemy.getLevel();
        int totalDamage = baseDamage + RandomUtils.generateNumber(-10, 10);
        int attackPercentage = RandomUtils.generateNumber(0, 10);

        if (turn.isDefenseOn()) {
            totalDamage = 0;
            turn.setDefenseOn(false);
        }

        if (attackPercentage == 0) {
            totalDamage = 0;
        }
        else if (attackPercentage == 9) {
            totalDamage = totalDamage * 2;
            TurnRepository.getTurns().getLast().setCriticalDamage(true);
        }

        player.setLife(player.getLife() - totalDamage);

        if (player.getLife() <= 0) {
            GameRepository.getGames().getLast().setGameOver(true);
        }

        return totalDamage;
    }
}
