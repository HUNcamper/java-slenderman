package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.resource.Texture;
import com.prog1.slenderman.game.resource.TextureLoader;

import javax.swing.*;

public class Player extends EntityVisible {
    Texture[] directions = new Texture[4]; // 0 up, 1 right, 2 down, 3 left

    public static enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }

    public Player() {
        this.directions[0] = TextureLoader.loadTexture("/textures/player/tile013.png");
        this.directions[1] = TextureLoader.loadTexture("/textures/player/tile009.png");
        this.directions[2] = TextureLoader.loadTexture("/textures/player/tile001.png");
        this.directions[3] = TextureLoader.loadTexture("/textures/player/tile005.png");
    }

    public void move(Direction dir) {
        switch(dir) {
            case UP:
                this.texture = this.directions[0];
                break;
            case RIGHT:
                this.texture = this.directions[1];
                break;
            case DOWN:
                this.texture = this.directions[2];
                break;
            case LEFT:
                this.texture = this.directions[3];
                break;
        }
    }
}
