package com.prog1.slenderman.game.resource;

import com.prog1.slenderman.game.Game;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    private AudioInputStream audioInputStream;
    private boolean loop = false;
    private float volume = 1.0f;
    private URL soundpath;
    private Clip clip;

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

    public Sound(String soundPath) throws IOException, UnsupportedAudioFileException {
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

    public void play() {
        if (this.clip != null) {
            this.clip.stop();
            this.clip.setMicrosecondPosition(0);

            if (this.loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
            }
        }
    }

    public void clipVolume(Clip clip, float vol) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        gainControl.setValue(13f * (float) Math.log10(vol) * Game.globalVolume);
    }
}
