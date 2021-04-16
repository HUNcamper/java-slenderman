package com.prog1.slenderman.game.level;

public abstract class LevelGenerator {
    public static Level fromFile() {
        return new Level();
    }

    public static Level random() {
        return new Level();
    }
}
