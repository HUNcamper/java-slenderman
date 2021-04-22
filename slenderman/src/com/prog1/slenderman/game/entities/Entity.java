package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.resource.Texture;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public abstract class Entity {
    protected boolean acceptInput = false;

    public boolean isAcceptInput() {
        return this.acceptInput;
    }

    public Entity() {
        Game.entityList.add(this);
    }

    public void handleInput(ActionEvent e) {

    }

    public void update() {

    }
}
