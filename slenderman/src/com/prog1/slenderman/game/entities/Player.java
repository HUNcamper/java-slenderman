package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.entities.floor.EntFloor;
import com.prog1.slenderman.game.entities.prop.Prop;
import com.prog1.slenderman.game.level.Level;
import com.prog1.slenderman.game.resource.Sound;
import com.prog1.slenderman.game.resource.SoundLoader;
import com.prog1.slenderman.game.resource.Texture;
import com.prog1.slenderman.game.resource.TextureLoader;

import java.awt.event.ActionEvent;

public class Player extends EntityVisible {
    private final Texture[] directions = new Texture[4]; // 0 up, 1 right, 2 down, 3 left
    private Prop interactWith;
    private Direction facing;

    public static enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }

    @Override
    public void newStep() {
        System.out.println("Manhattan distance from 0: " + Level.manhattanDistance(0, 0, this.cellX, this.cellY));
    }

    public Direction getFacing() {
        return facing;
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

    private Prop getPaperSurface(int x, int y) {
        // Először megnézzük, hogy a player pozícióján van-e collision nélküli prop
        EntityVisible ent = Game.loadedLevel.getEntity(2, this.cellX, this.cellY);
        if (!(ent instanceof Prop)) {
            // Ami előttünk van
            ent = Game.loadedLevel.getEntity(2, x, y);

            // Nincs collision, ne vehessük le róla a papírt, hacsak nem benne állunk
            if (ent != null && !ent.collisions) return null;
        }

        if (ent instanceof Prop) {
            Prop prop = (Prop) ent;

            if (prop.hasPaper) {
                System.out.println("Paper!!");
                //interactWithProp(prop);
                return prop;
            }
        }

        return null;
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

    public void interact() {
        if (this.interactWith == null) return;

        this.interactWith.interact();

        if (!this.interactWith.canInteract()) {
            this.interactWith = null;
        }

        Game.update();
    }

    public boolean canInteract() {
        return this.interactWith != null;
    }

    @Override
    public void handleInput(ActionEvent e) {
        String key = e.getActionCommand();
        //System.out.println("Pressed: " + key);

        switch (key) {
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
            case "f":
                interact();
                break;
        }
    }

    public void move(Direction dir) {
        this.interactWith = null;

        switch (dir) {
            case UP:
                this.texture = this.directions[0];
                move(this.cellX, this.cellY - 1);
                //this.interactWith = getPaperSurface(this.cellX, this.cellY - 1);
                break;
            case RIGHT:
                this.texture = this.directions[1];
                move(this.cellX + 1, this.cellY);
                //this.interactWith = getPaperSurface(this.cellX + 1, this.cellY);
                break;
            case DOWN:
                this.texture = this.directions[2];
                move(this.cellX, this.cellY + 1);
                //this.interactWith = getPaperSurface(this.cellX, this.cellY + 1);
                break;
            case LEFT:
                this.texture = this.directions[3];
                move(this.cellX - 1, this.cellY);
                //this.interactWith = getPaperSurface(this.cellX - 1, this.cellY);
                break;
        }

        this.facing = dir;

        Game.update();
    }

    public void move(int x, int y) {
        int xDiff = x - this.cellX ;
        int yDiff = y - this.cellY;

        if (!checkCollision(2, x, y)) {
            this.setCellPos(x, y);
            Game.newStep = true;
            playFootstep();
        }
        this.interactWith = getPaperSurface(x + xDiff, y + yDiff);
    }
}
