package com.prog1.slenderman.game.entities.floor;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.entities.EntityVisible;
import com.prog1.slenderman.game.resource.Sound;
import com.prog1.slenderman.game.resource.SoundLoader;

import java.net.URL;
import java.util.List;
import java.util.Random;

/**
 * Játható felületet leíró osztály
 */
public abstract class EntFloor extends EntityVisible {
    protected List<String> sounds = null;
    protected Sound sound = null;

    /**
     * Járható felület inicializálása adott koordinátákon és méretben
     * @param posX X koordináta
     * @param posY Y koordináta
     * @param sizeX X méret
     * @param sizeY Y méret
     */
    public EntFloor(int posX, int posY, int sizeX, int sizeY) {
        super(posX, posY, sizeX, sizeY);
    }

    /**
     * A felületen lépés lekezelése, beállított hangok közül egy random lejátszása
     */
    public void step() {
        if (this.sounds != null) {
            Random r = new Random();

            String soundName = this.sounds.get(r.nextInt(this.sounds.size()));
            this.sound = SoundLoader.loadSound(soundName);

            this.sound.play();
        }
    }
}
