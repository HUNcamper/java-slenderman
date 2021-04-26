package com.prog1.slenderman.game.entities.prop;

import com.prog1.slenderman.game.resource.TextureLoader;

/**
 * Nagy méretű fát leíró tereptárgy osztály
 */
public class PropTree extends Prop {

    /**
     * Nagy méretű fa és textúra inicializálás
     */
    public PropTree() {
        super();
        this.paperSurface = true;
        this.sizeX = 2;
        this.sizeY = 2;
        this.collisions = false;

        this.setTexture(TextureLoader.loadTexture("/textures/entities/tree_big.png"));
    }
}
