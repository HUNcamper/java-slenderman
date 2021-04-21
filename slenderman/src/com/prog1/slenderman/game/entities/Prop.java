package com.prog1.slenderman.game.entities;

import java.net.URL;

public class Prop extends EntityVisible {
    protected boolean paperSurface = false;

    public Prop() {
        super();
    }

    public Prop(URL textureURL) {
        super(textureURL);
    }

    public Prop(URL textureURL, int pos_x, int pos_y, int size_x, int size_y) {
        super(textureURL, pos_x, pos_y, size_x, size_y);
    }

    public boolean isPaperSurface() {
        return paperSurface;
    }
}
