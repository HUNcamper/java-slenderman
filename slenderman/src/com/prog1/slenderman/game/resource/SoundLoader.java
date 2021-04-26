package com.prog1.slenderman.game.resource;

import com.prog1.slenderman.game.Game;

/**
 * Hangfájlok betöltésére és cache-elésére szolgáló osztály
 */
public abstract class SoundLoader {
    /**
     * Hangfájl betöltése fájlból, vagy ha már be van töltve, lekérdezése a globális betöltött listából
     * @param soundName Hangfájl elérési útvonala
     * @return Hang osztály
     */
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
