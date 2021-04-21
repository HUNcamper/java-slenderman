package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.resource.Texture;
import com.prog1.slenderman.game.resource.TextureLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Player extends EntityVisible {
    Texture[] directions = new Texture[4]; // 0 up, 1 right, 2 down, 3 left

    public static enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }

    public Player(int pos_x, int pos_y, int size_x, int size_y) {
        super(pos_x, pos_y, size_x, size_y);
        this.directions[0] = TextureLoader.loadTexture("/textures/player/tile013.png");
        this.directions[1] = TextureLoader.loadTexture("/textures/player/tile009.png");
        this.directions[2] = TextureLoader.loadTexture("/textures/player/tile001.png");
        this.directions[3] = TextureLoader.loadTexture("/textures/player/tile005.png");

        this.acceptInput = true;

        this.setTexture(this.directions[2]);
    }

    @Override
    public void handleInput(ActionEvent e) {
        String key = e.getActionCommand();
        System.out.println("Pressed: " + key);

        switch(key) {
            case "w":
                move(Direction.UP);
                break;
            case "d":
                move(Direction.RIGHT);
                break;
            case "s":
                move(Direction.DOWN);
                break;
            case "a":
                move(Direction.LEFT);
                break;
        }
    }

    public void move(Direction dir) {
        switch(dir) {
            case UP:
                this.texture = this.directions[0];
                this.setPos_y(this.pos_y - 50);
                break;
            case RIGHT:
                this.texture = this.directions[1];
                this.setPos_x(this.pos_x + 50);
                break;
            case DOWN:
                this.texture = this.directions[2];
                this.setPos_y(this.pos_y + 50);
                break;
            case LEFT:
                this.texture = this.directions[3];
                this.setPos_x(this.pos_x - 50);
                break;
        }

        Game.update();
    }
}
