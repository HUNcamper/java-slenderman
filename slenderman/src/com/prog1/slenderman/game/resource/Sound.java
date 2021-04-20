package com.prog1.slenderman.game.resource;

import com.prog1.slenderman.Main;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Sound {
    private final HashMap<URL, AudioInputStream> soundList;
    private AudioInputStream audioInputStream;

    public Sound(List<String> soundPathList) throws IOException, UnsupportedAudioFileException {
        // Több db hang fájl
        this.soundList = new HashMap<URL, AudioInputStream>();

        for ( String path : soundPathList) {
            URL url;
            if (path.startsWith("/")) {
                url = Main.class.getResource(path); // Belső resource fájl
            } else {
                url = new URL(path); // Külső fájl, absolute path
            }

            this.soundList.put(url, AudioSystem.getAudioInputStream(url));
        }
    }

    public Sound(String path) throws IOException, UnsupportedAudioFileException {
        // Egy db hang fájl. Egy elemű lista igazából
        this(new ArrayList<String>(Collections.singletonList(path)));
    }

    public void play() {
        Random rand = new Random();

        Clip clip = null;
        Set<URL> keys = this.soundList.keySet();
        int index = rand.nextInt(keys.size());
        URL randKey = (URL)keys.toArray()[index];
        AudioInputStream sound = this.soundList.get(randKey);

        try {
            clip = AudioSystem.getClip();

            if (clip != null) {
                clip.open(sound);
                clip.start();
            }

        } catch (Exception e) {
            System.err.println("Nem sikerült lejátszani a " + sound.toString() + " fájlt.");
            e.printStackTrace();
        }
    }
}
