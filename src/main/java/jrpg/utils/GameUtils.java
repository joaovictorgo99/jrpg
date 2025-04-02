package jrpg.utils;

import jrpg.model.Game;
import jrpg.model.Player;
import jrpg.repository.GameRepository;
import jrpg.repository.PlayerRepository;

public class GameUtils {
    public GameUtils() {
    }

    public static void newGame(Game game) {
        game.setGameOver(false);
        GameRepository.addGame(game);
        PlayerRepository.addPlayer(new Player(50, 1, 50, 3, 3));
        BattleUtils.newBattle(true);
    }
}
