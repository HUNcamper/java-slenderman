package com.prog1.slenderman.game.resource;

import com.prog1.slenderman.Main;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

public class URLHandler {
    public static URL convertString(String path) throws IOException, UnsupportedAudioFileException {
        URL url;
        if (path.startsWith("/")) {
            url = Main.class.getResource(path); // Belső resource fájl
        } else {
            url = new URL(path); // Külső fájl, absolute path
        }

        return url;
    }
}
