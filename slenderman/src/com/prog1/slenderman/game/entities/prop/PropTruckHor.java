package com.prog1.slenderman.game.entities.prop;

import com.prog1.slenderman.game.resource.TextureLoader;

public class PropTruckHor extends Prop {

    public PropTruckHor() {
        super();
        this.paperSurface = true;
        this.sizeX = 5;
        this.sizeY = 3;

        this.setTexture(TextureLoader.loadTexture("/textures/entities/truck_hor.png"));
    }
}
