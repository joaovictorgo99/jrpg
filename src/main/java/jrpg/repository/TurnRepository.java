package jrpg.repository;

import jrpg.model.Turn;

import java.util.ArrayList;
import java.util.List;

public class TurnRepository {
    private static final List<Turn> turns = new ArrayList<>();

    public TurnRepository() {
    }

    public static void addTurn(Turn turn) {
        turns.add(turn);
    }

    public static List<Turn> getTurns() {
        return turns;
    }
}
