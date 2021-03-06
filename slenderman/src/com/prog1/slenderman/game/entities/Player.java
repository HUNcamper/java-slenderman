package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.entities.floor.EntFloor;
import com.prog1.slenderman.game.entities.prop.Prop;
import com.prog1.slenderman.game.resource.Texture;
import com.prog1.slenderman.game.resource.TextureLoader;

import java.awt.event.ActionEvent;

/**
 * A játékos osztálya
 */
public class Player extends EntityVisible {
    private final Texture[] directions = new Texture[4]; // 0 up, 1 right, 2 down, 3 left
    private Prop interactWith;
    private Direction facing;

    /**
     * Játékos irányát írja le
     */
    public static enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }

    /**
     * A játékos irányának lekérdezése
     *
     * @return Játékos iránya
     */
    public Direction getFacing() {
        return facing;
    }

    /**
     * Játékos létrehozása és textúrák inicializálása
     *
     * @param posX  X koordináta
     * @param posY  Y koordináta
     * @param sizeX X méret
     * @param sizeY Y méret
     */
    public Player(int posX, int posY, int sizeX, int sizeY) {
        super(posX, posY, sizeX, sizeY);
        this.directions[0] = TextureLoader.loadTexture("/textures/player/tile013.png");
        this.directions[1] = TextureLoader.loadTexture("/textures/player/tile009.png");
        this.directions[2] = TextureLoader.loadTexture("/textures/player/tile001.png");
        this.directions[3] = TextureLoader.loadTexture("/textures/player/tile005.png");

        this.directions[0].setSize(this.sizeX, this.sizeY);
        this.directions[1].setSize(this.sizeX, this.sizeY);
        this.directions[2].setSize(this.sizeX, this.sizeY);
        this.directions[3].setSize(this.sizeX, this.sizeY);

        this.acceptInput = true;

        this.setTexture(this.directions[2]);
    }

    /**
     * Vizsgálat arra, hogy a játékos előtt vagy a játékos mezőjén van-e felület, amin van papír
     *
     * @param x X koordináta, ahol ellenőrzünk
     * @param y Y koordináta, ahol ellenőrzünk
     * @return A felület entitásával tér vissza, ha van papír, null-al, ha nincs.
     */
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
                return prop;
            }
        }

        return null;
    }

    /**
     * Ütközés ellenőrzése egy adott koordinátán és rétegen
     *
     * @param layer Réteg
     * @param x     X koordináta
     * @param y     Y koordináta
     * @return Igaz, ha van ütközés, hamis, ha nincs.
     */
    public boolean checkCollision(int layer, int x, int y) {
        if (Game.loadedLevel.isOutOfBounds(x, y)) return true;

        EntityVisible ent = Game.loadedLevel.getEntity(layer, x, y);

        if (ent != null && ent.collisions) return true;

        return false;
    }

    /**
     * Lépés hang lejátszása attól függően, hogy milyen felületen sétálunk.
     */
    public void playFootstep() {
        EntityVisible floor = Game.loadedLevel.getEntity(0, this.cellX, this.cellY);

        if (floor != null) {
            if (floor instanceof EntFloor) {
                ((EntFloor) floor).step();
            }
        }
    }

    /**
     * Interakció egy pályán levő tárggyal.
     */
    public void interact() {
        if (this.interactWith == null) return;

        this.interactWith.interact();

        if (!this.interactWith.canInteract()) {
            this.interactWith = null;
        }

        Game.update();
    }

    /**
     * A játékos tud jelenleg interakciót végrehajtani?
     *
     * @return Igaz, ha igen, hamis, ha nem.
     */
    public boolean canInteract() {
        return this.interactWith != null;
    }

    /**
     * Mozgás lekezelése beolvasott billentyűkkel
     *
     * @param e Swing ActionEvent
     */
    @Override
    public void handleInput(ActionEvent e) {
        String key = e.getActionCommand();

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

    /**
     * Mozgás megkísérlése egy adott irányban<br>
     * (Nem feltétlen sikerül, csak ha nincs ütközés, és nem megy ki a pályáról.)
     *
     * @param dir Irány
     */
    public void move(Direction dir) {
        this.interactWith = null;

        switch (dir) {
            case UP:
                this.texture = this.directions[0];
                move(this.cellX, this.cellY - 1);
                break;
            case RIGHT:
                this.texture = this.directions[1];
                move(this.cellX + 1, this.cellY);
                break;
            case DOWN:
                this.texture = this.directions[2];
                move(this.cellX, this.cellY + 1);
                break;
            case LEFT:
                this.texture = this.directions[3];
                move(this.cellX - 1, this.cellY);
                break;
        }

        this.facing = dir;

        Game.update();
    }

    /**
     * Mozgás megkísérlése adott koordinátákra<br>
     * *     (Nem feltétlen sikerül, csak ha nincs ütközés, és nem megy ki a pályáról.)
     *
     * @param x X koordináta
     * @param y Y koordináta
     */
    public void move(int x, int y) {
        int xDiff = x - this.cellX;
        int yDiff = y - this.cellY;

        if (!checkCollision(2, x, y)) {
            this.setCellPos(x, y);
            Game.newStep = true;
            playFootstep();
        } else {
            // Előttünk van valami, nem tudunk mozogni, nincs különbség az előző pozícióhoz képest
            xDiff = 0;
            yDiff = 0;

            // sima x és y igazából AHOVA szeretnénk menni- nem az, ahol vagyunk.
        }

        this.interactWith = getPaperSurface(x + xDiff, y + yDiff);
    }
}
