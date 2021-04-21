package com.prog1.slenderman.game.resource;

import com.prog1.slenderman.game.Game;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

public class TextureLoader {
    public static Texture loadTexture(String imagePath) throws IOException, UnsupportedAudioFileException {
        return TextureLoader.loadTexture(URLHandler.convertString(imagePath));
    }

    public static Texture loadTexture(URL imageURL) {
        if (!Game.texturePool.containsKey(imageURL)) {
            Texture texture = new Texture(imageURL);
            Game.texturePool.put(imageURL, texture);
        }

        return Game.texturePool.get(imageURL);
    }
}
