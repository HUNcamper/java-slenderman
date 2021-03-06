package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.resource.Sound;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Háttér zene lejátszásra használt osztály
 */
public class MusicPlayer extends Entity {
    private ArrayList<String> music;
    private int previousCollected = 0;
    private Sound nowPlaying = null;
    private int playingIndex = -1;

    /**
     * Zene osztály inicializálása, hangok importálása
     */
    public MusicPlayer() {
        this.acceptInput = true;

        this.music = new ArrayList<String>();
        this.music.add("/sound/ost/1-2.wav");
        this.music.add("/sound/ost/3-4.wav");
        this.music.add("/sound/ost/5-6.wav");
        this.music.add("/sound/ost/7.wav");
    }

    /**
     * Adott indexű zene lejátszása, és az előzőleg lejátszott megállítása
     *
     * @param index Zene index
     */
    public void PlayMusic(int index) {
        if (index < 0 || index >= this.music.size()) return;

        try {
            stop();

            Sound music = new Sound(this.music.get(index));
            music.setLoop(true);
            music.setVolume(0.1f);
            music.play();

            this.nowPlaying = music;
            this.playingIndex = index;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Jelenleg játszott zene teljes megállítása
     */
    public void stop() {
        if (this.nowPlaying != null) {
            this.nowPlaying.stop();
            this.nowPlaying = null;
        }
    }

    /**
     * Billentyű input kezelése, amint a játékos felvesz papírt, a zene megváltozhat a papírok számának függvényében
     *
     * @param e Swing ActionEvent
     */
    @Override
    public void handleInput(ActionEvent e) {
        if (e.getActionCommand().equals("f")) {
            int pages = Game.pagesCollected;

            if (previousCollected == pages) return;

            int toPlay = -1;

            if (pages >= 1 && pages < 3) {
                toPlay = 0;
            } else if (pages <= 4) {
                toPlay = 1;
            } else if (pages <= 6) {
                toPlay = 2;
            } else if (pages == 7) {
                toPlay = 3;
            } else if (pages == 8) {
                this.nowPlaying.stop();
                return;
            }

            if (toPlay == this.playingIndex) return;

            PlayMusic(toPlay);
        }
    }
}
