package jrpg.utils;

import jrpg.model.Turn;
import jrpg.repository.TurnRepository;

public class TurnUtils {
    public TurnUtils() {
    }

    public static void newTurn(boolean newBattle) {
        Turn turn = new Turn(0, true, false, false, false);

        if (newBattle) {
            turn.setId(1);
        }
        else {
            turn.setId(TurnRepository.getTurns().getLast().getId() + 1);
        }

        TurnRepository.addTurn(turn);
    }
}
