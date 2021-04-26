package com.prog1.slenderman.game.entities.prop;

import com.prog1.slenderman.game.resource.TextureLoader;

/**
 * Függőleges teherautót leíró tereptárgy osztály
 */
public class PropTruckVer extends Prop {

    /**
     * Függőleges teherautó és textúra inicializálás
     */
    public PropTruckVer() {
        super();
        this.paperSurface = true;
        this.sizeX = 3;
        this.sizeY = 5;

        this.setTexture(TextureLoader.loadTexture("/textures/entities/truck_ver.png"));
    }
}
