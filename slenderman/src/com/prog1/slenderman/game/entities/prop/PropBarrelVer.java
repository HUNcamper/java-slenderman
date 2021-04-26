package com.prog1.slenderman.game.entities.prop;

import com.prog1.slenderman.game.resource.TextureLoader;

/**
 * Függőleges hordót leíró tereptárgy osztály
 */
public class PropBarrelVer extends Prop {

    /**
     * Függőleges hordó és textúra inicializálás
     */
    public PropBarrelVer() {
        super();
        this.paperSurface = true;
        this.sizeX = 2;
        this.sizeY = 4;

        this.setTexture(TextureLoader.loadTexture("/textures/entities/barrel_ver.png"));
    }
}
