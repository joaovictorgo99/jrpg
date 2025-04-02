package jrpg.repository;

import jrpg.model.Game;

import java.util.ArrayList;
import java.util.List;

public class GameRepository {
    private static final List<Game> games = new ArrayList<>();

    public GameRepository() {
    }

    public static void addGame(Game game) {
        games.add(game);
    }

    public static List<Game> getGames() {
        return games;
    }
}
