package com.prog1.slenderman.game.entities.prop;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.entities.EntityVisible;
import com.prog1.slenderman.game.resource.Sound;
import com.prog1.slenderman.game.resource.SoundLoader;

import java.net.URL;

public class Prop extends EntityVisible {
    protected boolean paperSurface = false;
    protected Sound paperSound;

    public boolean hasPaper = false;

    public Prop() {
        super();

        this.paperSound = SoundLoader.loadSound("/sound/paper.wav");
    }

    public Prop(int pos_x, int pos_y, int size_x, int size_y) {
        super(pos_x, pos_y, size_x, size_y);
    }

    public void interact() {
        this.hasPaper = false;
        Game.pagesCollected++;
        paperSound.play();
    }

    public boolean canInteract() {
        return this.hasPaper;
    }

    public boolean isPaperSurface() {
        return paperSurface;
    }

    public void setPaperSurface(boolean paperSurface) {
        this.paperSurface = paperSurface;
    }
}
