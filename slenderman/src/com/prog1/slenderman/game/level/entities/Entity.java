package com.prog1.slenderman.game.level.entities;

public abstract class Entity {
    protected int size_x = 1;
    protected int size_y = 1;

    protected String[][] display;
    protected String displayChar;
    protected String displayColor;

    protected boolean paperSurface = false;

    public Entity(int size_x, int size_y) {
        this.size_x = size_x;
        this.size_y = size_y;

        this.display = new String[size_y][size_x];
    }

    public boolean isPaperSurface() {
        return paperSurface;
    }
}
