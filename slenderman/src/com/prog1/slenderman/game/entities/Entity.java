package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.Game;

import java.awt.event.ActionEvent;

/**
 * Entitást leíró osztály
 */
public abstract class Entity {
    protected boolean acceptInput = false;

    /**
     * Elfogad billentyű inputot az osztály?
     *
     * @return Igaz, ha igen, hamis, ha nem
     */
    public boolean isAcceptInput() {
        return this.acceptInput;
    }

    /**
     * Annak beállítása, hogy elfogadjon-e billentyű inputot az osztály
     *
     * @param acceptInput Igaz, ha igen, hamis, ha nem
     */
    public void setAcceptInput(boolean acceptInput) {
        this.acceptInput = acceptInput;
    }

    /**
     * Entitás inicializálása, és hozzáadása a globális entitás listához
     */
    public Entity() {
        Game.entityList.add(this);
    }

    /**
     * Billentyű input lekezelése
     *
     * @param e Swing ActionEvent
     */
    public void handleInput(ActionEvent e) {

    }

    /**
     * Entitás frissítése
     */
    public void update() {

    }

    /**
     * A játékos által új lépés történt
     */
    public void newStep() {

    }
}
