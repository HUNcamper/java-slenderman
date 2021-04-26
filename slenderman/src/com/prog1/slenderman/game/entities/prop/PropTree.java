package com.prog1.slenderman.game.entities.prop;

import com.prog1.slenderman.game.resource.TextureLoader;

public class PropTree extends Prop {

    public PropTree() {
        super();
        this.paperSurface = true;
        this.sizeX = 2;
        this.sizeY = 2;
        this.collisions = false;

        this.setTexture(TextureLoader.loadTexture("/textures/entities/tree_big.png"));
    }
}
