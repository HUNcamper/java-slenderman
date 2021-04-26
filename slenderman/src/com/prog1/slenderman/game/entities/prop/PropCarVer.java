package com.prog1.slenderman.game.entities.prop;

import com.prog1.slenderman.game.resource.TextureLoader;

/**
 * Függőleges autót leíró tereptárgy osztály
 */
public class PropCarVer extends Prop {

    /**
     * Függőleges autó és textúra inicializálás
     */
    public PropCarVer() {
        super();
        this.paperSurface = true;
        this.sizeX = 2;
        this.sizeY = 3;

        this.setTexture(TextureLoader.loadTexture("/textures/entities/car_ver.png"));
    }
}
