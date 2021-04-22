package com.prog1.slenderman.game.resource;

import com.prog1.slenderman.game.Game;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

public abstract class TextureLoader {
    public static Texture loadTexture(String imageName) {
        if (!Game.texturePool.containsKey(imageName)) {
            Texture texture = new Texture(imageName);
            Game.texturePool.put(imageName, texture);
        }

        return Game.texturePool.get(imageName);
    }
}
