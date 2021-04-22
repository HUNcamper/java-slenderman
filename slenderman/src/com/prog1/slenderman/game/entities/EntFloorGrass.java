package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.resource.Sound;
import com.prog1.slenderman.game.resource.SoundLoader;
import com.prog1.slenderman.game.resource.TextureLoader;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;

public class EntFloorGrass extends EntFloor {
    public EntFloorGrass(int pos_x, int pos_y, int size_x, int size_y) {
        super(pos_x, pos_y, size_x, size_y);
        this.setTexture(TextureLoader.loadTexture("/textures/grass.png"));

        this.sounds = new ArrayList<>();
        this.sounds.add("/sound/footsteps/grass1.wav");
        this.sounds.add("/sound/footsteps/grass2.wav");
        this.sounds.add("/sound/footsteps/grass3.wav");
        this.sounds.add("/sound/footsteps/grass4.wav");
    }
}
