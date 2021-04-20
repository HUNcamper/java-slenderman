package com.prog1.slenderman.game.entities;

public class Prop extends Entity {
    protected boolean paperSurface = false;

    public Prop() {
        super();
    }

    public Prop(int size_x, int size_y) {
        super(size_x, size_y);
    }

    public boolean isPaperSurface() {
        return paperSurface;
    }
}
