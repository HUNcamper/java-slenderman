package com.prog1.slenderman.game.entities.floor;

import com.prog1.slenderman.game.resource.TextureLoader;

import java.util.ArrayList;

/**
 * Beton textúrájú járható felületet leíró osztály
 */
public class EntFloorConcrete extends EntFloor {

    /**
     * Járható felület inicializálása adott koordinátákon és méretben
     * @param posX X koordináta
     * @param posY Y koordináta
     * @param sizeX X méret
     * @param sizeY Y méret
     */
    public EntFloorConcrete(int posX, int posY, int sizeX, int sizeY) {
        super(posX, posY, sizeX, sizeY);
        this.setTexture(TextureLoader.loadTexture("/textures/stone.png"));

        this.sounds = new ArrayList<>();
        this.sounds.add("/sound/footsteps/concrete1.wav");
        this.sounds.add("/sound/footsteps/concrete2.wav");
        this.sounds.add("/sound/footsteps/concrete3.wav");
        this.sounds.add("/sound/footsteps/concrete4.wav");
    }
}
