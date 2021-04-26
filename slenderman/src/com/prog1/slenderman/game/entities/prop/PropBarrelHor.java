package com.prog1.slenderman.game.entities.prop;

import com.prog1.slenderman.game.resource.TextureLoader;

public class PropBarrelHor extends Prop {

    public PropBarrelHor() {
        super();
        this.paperSurface = true;
        this.sizeX = 4;
        this.sizeY = 2;

        this.setTexture(TextureLoader.loadTexture("/textures/entities/barrel_hor.png"));
    }
}
