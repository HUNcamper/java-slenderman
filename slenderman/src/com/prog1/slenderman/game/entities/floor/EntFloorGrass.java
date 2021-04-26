package com.prog1.slenderman.game.entities.floor;

import com.prog1.slenderman.game.resource.TextureLoader;

import java.util.ArrayList;

/**
 * Fű textúrájú járható felületet leíró osztály
 */
public class EntFloorGrass extends EntFloor {

    /**
     * Járható felület inicializálása adott koordinátákon és méretben
     * @param posX X koordináta
     * @param posY Y koordináta
     * @param sizeX X méret
     * @param sizeY Y méret
     */
    public EntFloorGrass(int posX, int posY, int sizeX, int sizeY) {
        super(posX, posY, sizeX, sizeY);
        this.setTexture(TextureLoader.loadTexture("/textures/grass_dark.png"));

        this.sounds = new ArrayList<>();
        this.sounds.add("/sound/footsteps/grass1.wav");
        this.sounds.add("/sound/footsteps/grass2.wav");
        this.sounds.add("/sound/footsteps/grass3.wav");
        this.sounds.add("/sound/footsteps/grass4.wav");
    }
}
