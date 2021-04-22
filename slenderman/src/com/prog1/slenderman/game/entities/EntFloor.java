package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.resource.Sound;
import com.prog1.slenderman.game.resource.SoundLoader;

import java.net.URL;
import java.util.List;
import java.util.Random;

public abstract class EntFloor extends EntityVisible {
    protected List<String> sounds = null;
    protected Sound sound = null;

    public EntFloor(int pos_x, int pos_y, int size_x, int size_y) {
        super(pos_x, pos_y, size_x, size_y);
    }

    public void step() {
        if (this.sounds != null) {
            Random r = new Random();

            String soundName = this.sounds.get(r.nextInt(this.sounds.size()));
            this.sound = SoundLoader.loadSound(soundName);

            this.sound.play();
        }
    }
}
