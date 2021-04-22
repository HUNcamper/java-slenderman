package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.resource.Sound;

import java.net.URL;
import java.util.List;

public abstract class EntFloor extends EntityVisible {
    protected List<String> sounds;

    public EntFloor(int pos_x, int pos_y, int size_x, int size_y) {
        super(pos_x, pos_y, size_x, size_y);
    }

    public void step() {
        System.out.println("playing sound");

        try {
            Sound sound = new Sound(this.sounds);
            sound.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
