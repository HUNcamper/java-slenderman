package com.prog1.slenderman.game.entities.prop;

import com.prog1.slenderman.game.resource.TextureLoader;

/**
 * Vízszintes autót leíró tereptárgy osztály
 */
public class PropCarHor extends Prop {

    /**
     * Vízszintes autó és textúra inicializálás
     */
    public PropCarHor() {
        super();
        this.paperSurface = true;
        this.sizeX = 3;
        this.sizeY = 2;

        this.setTexture(TextureLoader.loadTexture("/textures/entities/car_hor.png"));
    }
}
