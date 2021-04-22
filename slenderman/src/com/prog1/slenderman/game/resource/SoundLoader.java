package com.prog1.slenderman.game.resource;

import com.prog1.slenderman.game.Game;

public abstract class SoundLoader {
    public static Sound loadSound(String soundName) {
        if (!Game.soundPool.containsKey(soundName)) {
            Sound sound = null;

            try {
                sound = new Sound(soundName);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Game.soundPool.put(soundName, sound);
        }

        return Game.soundPool.get(soundName);
    }
}
