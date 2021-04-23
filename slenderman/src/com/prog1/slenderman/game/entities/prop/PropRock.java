package com.prog1.slenderman.game.entities.prop;

import com.prog1.slenderman.game.resource.TextureLoader;

public class PropRock extends Prop {

    public PropRock() {
        super();
        this.paperSurface = true;
        this.sizeX = 3;
        this.sizeY = 3;
        this.collisions = true;

        this.setTexture(TextureLoader.loadTexture("/textures/entities/rock.png"));
    }
}
