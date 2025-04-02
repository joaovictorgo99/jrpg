package jrpg.repository;

import jrpg.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerRepository {
    private static final List<Player> players = new ArrayList<>();

    public PlayerRepository() {
    }

    public static void addPlayer(Player player) {
        players.add(player);
    }

    public static List<Player> getPlayers() {
        return players;
    }
}
