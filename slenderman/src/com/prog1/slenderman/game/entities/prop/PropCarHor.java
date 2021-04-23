package com.prog1.slenderman.game.entities.prop;

import com.prog1.slenderman.game.resource.TextureLoader;

public class PropCarHor extends Prop {

    public PropCarHor() {
        super();
        this.paperSurface = true;
        this.sizeX = 3;
        this.sizeY = 2;

        this.setTexture(TextureLoader.loadTexture("/textures/entities/car_hor.png"));
    }
}
