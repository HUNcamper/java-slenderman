package com.prog1.slenderman.game.entities.prop;

import com.prog1.slenderman.game.resource.TextureLoader;

/**
 * Sziklát leíró tereptárgy osztály
 */
public class PropRock extends Prop {

    /**
     * Szikla és textúra inicializálás
     */
    public PropRock() {
        super();
        this.paperSurface = true;
        this.sizeX = 3;
        this.sizeY = 3;

        this.setTexture(TextureLoader.loadTexture("/textures/entities/rock.png"));
    }
}
