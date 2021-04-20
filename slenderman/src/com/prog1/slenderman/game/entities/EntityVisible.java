package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.display.MainCamera;

import javax.swing.*;

public abstract class EntityVisible extends Entity {
    protected int pos_x = 0;
    protected int pos_y = 0;
    protected int size_x = 32;
    protected int size_y = 32;
    protected JLabel label;

    public EntityVisible() {
        this.label = new JLabel();
    }

    public EntityVisible(int pos_x, int pos_y, int size_x, int size_y) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.size_x = size_x;
        this.size_y = size_y;
    }

    public JLabel getLabel() {
        return this.label;
    }

    public int getPos_x() {
        return pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
        alignToCameraOffset();
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
        alignToCameraOffset();
    }

    public void alignToCameraOffset() {
        MainCamera camera = Game.mainCamera;
        label.setBounds(pos_x - camera.pos_x, pos_y - camera.pos_y, this.size_x, this.size_y);
    }
}
