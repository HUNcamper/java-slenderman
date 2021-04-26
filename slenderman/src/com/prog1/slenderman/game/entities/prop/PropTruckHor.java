package com.prog1.slenderman.game.entities.prop;

import com.prog1.slenderman.game.resource.TextureLoader;

/**
 * Vízszintes teherautót leíró tereptárgy osztály
 */
public class PropTruckHor extends Prop {

    /**
     * Vízszintes teherautó és textúra inicializálás
     */
    public PropTruckHor() {
        super();
        this.paperSurface = true;
        this.sizeX = 5;
        this.sizeY = 3;

        this.setTexture(TextureLoader.loadTexture("/textures/entities/truck_hor.png"));
    }
}
