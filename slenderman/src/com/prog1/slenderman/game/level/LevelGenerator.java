package com.prog1.slenderman.game.level;

public abstract class LevelGenerator {
    public static Level fromFile() {
        return new Level(2, 15, 15);
    }

    public static Level random() {
        return new Level(2, 15, 15);
    }
}
