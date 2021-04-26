package com.prog1.slenderman.game.resource;

import com.prog1.slenderman.game.Game;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

/**
 * Textúra fájlok betöltésére és cache-elésére szolgáló osztály
 */
public abstract class TextureLoader {
    /**
     * Textúra betöltése fájlból, vagy ha már be van töltve, lekérdezése a globális betöltött listából
     * @param imageName Textúra elérési útvonala
     * @return Textúra osztály
     */
    public static Texture loadTexture(String imageName) {
        if (!Game.texturePool.containsKey(imageName)) {
            Texture texture = new Texture(imageName);
            Game.texturePool.put(imageName, texture);
        }

        return Game.texturePool.get(imageName);
    }
}
