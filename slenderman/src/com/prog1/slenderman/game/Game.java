package com.prog1.slenderman.game;

import com.prog1.slenderman.game.display.Renderer;
import com.prog1.slenderman.game.level.Level;
import com.prog1.slenderman.game.level.LevelGenerator;

public class Game {
    private Level currentLevel;
    private Renderer renderer;

    public Game() {
        this.currentLevel = LevelGenerator.random();
    }

    private void Render() {
        Renderer.RenderLevel(this.currentLevel);
        Renderer.RenderText("Hello");
        Renderer.Clear();
    }

    public boolean gameLoop() {
        Render();

        return true;
    }
}
