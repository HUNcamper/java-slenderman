package com.prog1.slenderman.game.entities.prop;

import com.prog1.slenderman.game.resource.TextureLoader;

/**
 * Kis méretű fát leíró tereptárgy osztály
 */
public class PropTreeSmall extends Prop {

    /**
     * Kis méretű fa és textúra inicializálás
     */
    public PropTreeSmall() {
        super();
        this.paperSurface = false;
        this.sizeX = 1;
        this.sizeY = 1;
        this.collisions = false;

        this.setTexture(TextureLoader.loadTexture("/textures/entities/tree_small.png"));
    }
}
