package com.prog1.slenderman.game.entities.prop;

import com.prog1.slenderman.game.resource.TextureLoader;

public class PropRockSmall extends Prop {

    public PropRockSmall() {
        super();
        this.paperSurface = true;
        this.sizeX = 1;
        this.sizeY = 1;
        this.collisions = true;

        this.setTexture(TextureLoader.loadTexture("/textures/entities/rock.png"));
    }
}
