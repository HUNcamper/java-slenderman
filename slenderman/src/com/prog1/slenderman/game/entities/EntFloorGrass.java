package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.resource.TextureLoader;

public class EntFloorGrass extends EntFloor {
    public EntFloorGrass(int pos_x, int pos_y, int size_x, int size_y) {
        super(pos_x, pos_y, size_x, size_y);
        this.setTexture(TextureLoader.loadTexture("/textures/grass.png"));
    }
}
