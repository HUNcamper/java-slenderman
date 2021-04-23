package com.prog1.slenderman.game.entities.prop;

import com.prog1.slenderman.game.entities.EntityVisible;

import java.net.URL;

public class Prop extends EntityVisible {
    protected boolean paperSurface = false;
    public boolean hasPaper = true;

    public Prop() {
        super();
    }

    public Prop(int pos_x, int pos_y, int size_x, int size_y) {
        super(pos_x, pos_y, size_x, size_y);
    }

    public boolean isPaperSurface() {
        return paperSurface;
    }
}
