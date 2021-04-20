package com.prog1.slenderman.game.entities;

public abstract class Entity {
    protected int size_x = 32;
    protected int size_y = 32;

    protected String[][] display;
    protected String displayChar;
    protected String displayColor;

    public Entity() {
        this.display = new String[size_y][size_x];
    }

    public Entity(int size_x, int size_y) {
        this();

        this.size_x = size_x;
        this.size_y = size_y;
    }
}
