package jrpg.utils;

import jrpg.model.Player;
import jrpg.model.Enemy;
import jrpg.model.Turn;
import jrpg.repository.BattleRepository;
import jrpg.repository.TurnRepository;

public class PlayerUtils {
    public PlayerUtils() {
    }

    public static int attack(Player player, Enemy enemy) {
        int rawDamage = 10;
        int baseDamage = rawDamage + player.getLevel() + player.getPower();
        int totalDamage = baseDamage + RandomUtils.generateNumber(-10, 10);
        int attackPercentage = RandomUtils.generateNumber(0, 10);

        if (attackPercentage == 0) {
            totalDamage = 0;
        }
        else if (attackPercentage == 9) {
            totalDamage = totalDamage * 2;
            TurnRepository.getTurns().getLast().setCriticalDamage(true);

            recoverPower(player);
        }

        enemy.setLife(enemy.getLife() - totalDamage);

        if (enemy.getLife() <= 0) {
            BattleRepository.getBattles().getLast().setPlayerWinner(true);
        }

        return totalDamage;
    }

    public static void defend(Player player, Turn turn) {
        int powerPoints = player.getPower();

        if (powerPoints != 0) {
            turn.setDefenseOn(true);
            player.setPower(powerPoints - 1);
        }
    }

    public static int heal(Player player) {
        int newLife;
        int totalHeal;
        int powerPoints = player.getPower();
        int healPercentage = RandomUtils.generateNumber(0, 10);

        if (powerPoints == 0) {
            return 0;
        }

        if (healPercentage < 3) {
            totalHeal = (int) (player.getMaxLife()*0.25);
            newLife = player.getLife() + totalHeal;
        }
        else if (healPercentage < 6) {
            totalHeal = (int) (player.getMaxLife()*0.5);
            newLife = player.getLife() + totalHeal;
        }
        else if (healPercentage < 9) {
            totalHeal = (int) (player.getMaxLife()*0.75);
            newLife = player.getLife() + totalHeal;
        }
        else {
            totalHeal = player.getMaxLife()-player.getLife();
            newLife = player.getLife() + totalHeal;
            TurnRepository.getTurns().getLast().setCriticalHeal(true);
        }

        player.setLife(newLife);

        if (player.getLife() > player.getMaxLife()) {
            player.setLife(player.getMaxLife());
        }

        player.setPower(powerPoints - 1);

        return totalHeal;
    }

    public static String levelUp(Player player) {
        String levelUpText = "";

        player.setLevel(player.getLevel() + 1);
        player.setMaxLife(50 + ((player.getLevel()-1) * 10));

        if (player.getLife() == (player.getMaxLife() - 10)) {
            player.setLife(player.getMaxLife());
        }

        if ((player.getLevel()-1) % 5 == 0) {
            player.setLife(player.getMaxLife());
            player.setMaxPower(player.getMaxPower() + 2);
            player.setPower(player.getMaxPower());
            levelUpText = "\nLIFE and POWER restored.";
        }

        return levelUpText;
    }

    public static void recoverPower(Player player) {
        if (player.getPower() < player.getMaxPower()) {
            player.setPower(player.getPower() + 1);
        }
    }
}
