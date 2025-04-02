package jrpg.utils;

import java.util.Random;

public class RandomUtils {
    public RandomUtils() {
    }

    public static int generateNumber(int lower, int upper) {
        Random random = new Random();

        return random.nextInt(lower, upper);
    }

    public static String generateType() {
        String[] type = {"Humanoid", "Beast", "Undead", "Machine", "Mystic"};

        return type[generateNumber(0, 5)];
    }
}
