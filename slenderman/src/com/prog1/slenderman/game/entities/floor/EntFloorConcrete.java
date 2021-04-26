package com.prog1.slenderman.game.entities.floor;

import com.prog1.slenderman.game.resource.TextureLoader;

import java.util.ArrayList;

public class EntFloorConcrete extends EntFloor {
    public EntFloorConcrete(int pos_x, int pos_y, int size_x, int size_y) {
        super(pos_x, pos_y, size_x, size_y);
        this.setTexture(TextureLoader.loadTexture("/textures/stone.png"));

        this.sounds = new ArrayList<>();
        this.sounds.add("/sound/footsteps/concrete1.wav");
        this.sounds.add("/sound/footsteps/concrete2.wav");
        this.sounds.add("/sound/footsteps/concrete3.wav");
        this.sounds.add("/sound/footsteps/concrete4.wav");
    }
}
