package com.prog1.slenderman.game.resource;

import com.prog1.slenderman.Main;
import com.prog1.slenderman.game.Game;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Sound {
    private final HashMap<URL, AudioInputStream> soundList;
    private AudioInputStream audioInputStream;
    private boolean loop = false;
    private float volume = 1.0f;

    public void setVolume(float volume) {
        if(volume < 0.0f) volume = 0.0f;
        if(volume > 1.0f) volume = 1.0f;

        this.volume = volume;
    }

    public float getVolume() {
        return this.volume;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public Sound(List<String> soundPathList) throws IOException, UnsupportedAudioFileException {
        // Több db hang fájl
        this.soundList = new HashMap<URL, AudioInputStream>();

        for ( String path : soundPathList) {
            URL url = URLHandler.convertString(path);

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

                // Hangerő beállítása
                clipVolume(clip, this.volume);

                if(this.loop) {
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } else {
                    clip.start();
                }
            }

        } catch (Exception e) {
            System.err.println("Nem sikerült lejátszani a " + sound.toString() + " fájlt.");
            e.printStackTrace();
        }
    }

    public void clipVolume(Clip clip, float vol) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        /*System.out.println(gainControl.getMinimum() + " -> " + gainControl.getMaximum());

        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (range * vol) + gainControl.getMinimum();

        System.out.println(gain);

        gainControl.setValue(gain);*/

        gainControl.setValue(13f * (float) Math.log10(vol) * Game.globalVolume);
    }
}
