package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.Game;

import java.awt.event.ActionEvent;

public abstract class Entity {
    protected boolean acceptInput = false;

    public boolean isAcceptInput() {
        return this.acceptInput;
    }

    public void setAcceptInput(boolean acceptInput) {
        this.acceptInput = acceptInput;
    }

    public Entity() {
        Game.entityList.add(this);
    }

    public void handleInput(ActionEvent e) {

    }

    public void newStep() {

    }
}
