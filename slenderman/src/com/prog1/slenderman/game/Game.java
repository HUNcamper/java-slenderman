package com.prog1.slenderman.game;

import com.prog1.slenderman.game.level.Level;
import com.prog1.slenderman.game.level.LevelGenerator;

public class Game {
    public static float globalVolume = 1.0f;

    public Game() {

    }

    private void Render() {
    }

    public boolean gameLoop() {
        Render();

        return true;
    }
}
