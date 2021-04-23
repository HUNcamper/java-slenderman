package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.resource.TextureLoader;

public class PropTreeSmall extends Prop {

    public PropTreeSmall() {
        super();
        this.paperSurface = true;
        this.sizeX = 2;
        this.sizeY = 2;
        this.collisions = false;

        System.out.println("Spawned a tree!!! at: x" + this.cellX + " y" + this.cellY);

        this.setTexture(TextureLoader.loadTexture("/textures/entities/tree_small.png"));
    }
}
