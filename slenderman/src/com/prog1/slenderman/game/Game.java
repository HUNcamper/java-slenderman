package com.prog1.slenderman.game;

import com.prog1.slenderman.game.level.Level;
import com.prog1.slenderman.game.level.LevelGenerator;

public class Game {
    private Level currentLevel;

    public Game() {
        this.currentLevel = LevelGenerator.random();
    }

    private void Render() {
    }

    public boolean gameLoop() {
        Render();

        return true;
    }
}
