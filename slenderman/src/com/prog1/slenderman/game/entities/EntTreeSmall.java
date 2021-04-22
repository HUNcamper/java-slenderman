package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.resource.TextureLoader;

public class EntTreeSmall extends Prop {

    public EntTreeSmall() {
        super();
        this.paperSurface = true;
        this.sizeX = 2;
        this.sizeY = 2;

        this.setTexture(TextureLoader.loadTexture("/textures/entities/tree_small.png"));
    }
}
