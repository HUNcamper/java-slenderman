package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.resource.Texture;
import com.prog1.slenderman.game.resource.TextureLoader;

import java.awt.event.ActionEvent;

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

        this.directions[0].setSize(sizeX, sizeY);
        this.directions[1].setSize(sizeX, sizeY);
        this.directions[2].setSize(sizeX, sizeY);
        this.directions[3].setSize(sizeX, sizeY);

        this.acceptInput = true;

        this.setTexture(this.directions[2]);
    }

    public boolean checkCollision(int layer, int x, int y) {
        if (Game.loadedLevel.isOutOfBounds(x, y)) return true;

        EntityVisible ent = Game.loadedLevel.getEntity(layer, x, y);

        if (ent != null && ent.collisions) return true;

        return false;
    }

    public void playFootstep() {
        System.out.println("Trying to play sound at x" + this.cellX + " y" + this.cellY);
        EntityVisible floor = Game.loadedLevel.getEntity(0, this.cellX, this.cellY);

        if (floor != null) {
            if (floor instanceof EntFloor) {
                ((EntFloor) floor).step();
            }
        }
    }

    @Override
    public void handleInput(ActionEvent e) {
        String key = e.getActionCommand();
        //System.out.println("Pressed: " + key);

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
                if (!checkCollision(2, this.cellX, this.cellY - 1)) {
                    this.setCellY(this.cellY - 1);
                    Game.newStep = true;
                }
                break;
            case RIGHT:
                this.texture = this.directions[1];
                if (!checkCollision(2, this.cellX + 1, this.cellY)) {
                    this.setCellX(this.cellX + 1);
                    Game.newStep = true;
                }
                break;
            case DOWN:
                this.texture = this.directions[2];
                if (!checkCollision(2, this.cellX, this.cellY + 1)) {
                    this.setCellY(this.cellY + 1);
                    Game.newStep = true;
                }
                break;
            case LEFT:
                this.texture = this.directions[3];
                if (!checkCollision(2, this.cellX - 1, this.cellY)) {
                    this.setCellX(this.cellX - 1);
                    Game.newStep = true;
                }
                break;
        }

        playFootstep();

        Game.update();
    }
}
