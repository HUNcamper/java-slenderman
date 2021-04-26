package com.prog1.slenderman.game.entities.prop;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.entities.EntityVisible;
import com.prog1.slenderman.game.resource.Sound;
import com.prog1.slenderman.game.resource.SoundLoader;

/**
 * Pályán elhelyezhető tereptárgyat leíró osztály, ami akár lehet papírfelület is.
 */
public class Prop extends EntityVisible {
    protected boolean paperSurface = false;
    protected Sound paperSound;

    public boolean hasPaper = false;

    /**
     * Tereptárgy inicializálása és papír hang betöltése
     */
    public Prop() {
        super();

        this.paperSound = SoundLoader.loadSound("/sound/paper.wav");
    }

    /**
     * Interakció a tereptárgyyal, amennyiben papír van rajta
     */
    public void interact() {
        this.hasPaper = false;
        Game.pagesCollected++;
        paperSound.play();
    }

    /**
     * Lehet interakciót folytatni a tereptárggyal?
     * @return Igaz, ha lehet, hamis, ha nem
     */
    public boolean canInteract() {
        return this.hasPaper;
    }

    /**
     * Ezen a tereptárgyon lehet papír?
     * @return Igaz ha lehet, hamis, ha nem
     */
    public boolean isPaperSurface() {
        return paperSurface;
    }

    /**
     * Tereptárgyon lehessen, vagy ne lehessen papír
     * @param paperSurface Igaz ha lehessen papír, hamis ha nem
     */
    public void setPaperSurface(boolean paperSurface) {
        this.paperSurface = paperSurface;
    }
}
