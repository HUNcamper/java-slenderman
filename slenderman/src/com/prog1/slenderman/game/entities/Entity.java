package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.resource.Texture;

import javax.swing.*;

public abstract class Entity {
    protected int size_x = 32;
    protected int size_y = 32;
    protected Texture texture;

    protected String[][] display;
    protected String displayChar;
    protected String displayColor;

    protected JLabel label;

    public JLabel getLabel() {
        return this.label;
    }

    public Entity() {
        this.display = new String[size_y][size_x];

        this.label = new JLabel();
    }

    public Entity(int size_x, int size_y) {
        this();

        this.size_x = size_x;
        this.size_y = size_y;
    }
}
