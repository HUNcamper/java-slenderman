package com.prog1.slenderman.game.resource;

import com.prog1.slenderman.game.Game;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * Hangfájlt kezelő osztály
 */
public class Sound extends Resource {
    private AudioInputStream audioInputStream;
    private boolean loop = false;
    private float volume = 1.0f;
    private URL soundpath;
    private Clip clip;

    /**
     * Hangerő beállítása
     * @param volume Hangerő, 0.0 és 1.0 között
     */
    public void setVolume(float volume) {
        if (volume < 0.0f) volume = 0.0f;
        if (volume > 1.0f) volume = 1.0f;

        this.volume = volume;
    }

    /**
     * Hangerő lekérdezése
     * @return Hangerő
     */
    public float getVolume() {
        return this.volume;
    }

    /**
     * Ismétlődés beálltása
     * @param loop Igaz, ha ismétlődjön, hamis, ha ne
     */
    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    /**
     * Hang inicializálása elérési útvonal alapján
     * @param soundPath Hangfájl elérési útvonala
     * @throws IOException Ha nem sikerül a fájl beolvasása
     * @throws UnsupportedAudioFileException Ha a hang formátuma nem támogatott
     */
    public Sound(String soundPath) throws IOException, UnsupportedAudioFileException {
        super(soundPath);

        // Egy db hang fájl
        URL url = URLHandler.convertString(soundPath);

        this.audioInputStream = AudioSystem.getAudioInputStream(url);
        try {
            this.clip = AudioSystem.getClip();
            this.clip.open(this.audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.clip != null) {
            clipVolume(this.clip, this.volume);
        }
    }

    /**
     * Hang lejátszása<br>
     *     Ha már le van játszva a hang, akkor megállítás és újra lejátszás
     */
    public void play() {
        if (this.clip != null) {
            stop();

            if (this.loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
            }
        }
    }

    /**
     * Hang megállítása
     */
    public void stop() {
        if (this.clip != null) {
            this.clip.stop();
            this.clip.setMicrosecondPosition(0);
        }
    }

    /**
     * Clip hangerejének beállítása
     * @param clip Clip
     * @param vol Hangerő 0.0 és 1.0 között
     */
    public void clipVolume(Clip clip, float vol) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        gainControl.setValue(13f * (float) Math.log10(vol) * Game.globalVolume);
    }
}
