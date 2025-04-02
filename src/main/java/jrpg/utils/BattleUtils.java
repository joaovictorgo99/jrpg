package jrpg.utils;

import jrpg.model.Battle;
import jrpg.model.Enemy;
import jrpg.model.Player;
import jrpg.repository.BattleRepository;
import jrpg.repository.EnemyRepository;
import jrpg.repository.PlayerRepository;

public class BattleUtils {
    public BattleUtils() {
    }

    public static void newBattle(boolean newGame) {
        Player player = PlayerRepository.getPlayers().getLast();
        Enemy enemy = new Enemy(50 + ((player.getLevel()-1) * 10), player.getLevel(), RandomUtils.generateType());
        Battle battle = new Battle(0, false);

        if (newGame) {
            battle.setId(1);
        }
        else {
            battle.setId(BattleRepository.getBattles().getLast().getId() + 1);
        }

        EnemyRepository.addEnemy(enemy);
        BattleRepository.addBattle(battle);
        TurnUtils.newTurn(true);
    }
}
