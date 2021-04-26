package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.display.MainCamera;
import com.prog1.slenderman.game.display.MainView;
import com.prog1.slenderman.game.level.Level;
import com.prog1.slenderman.game.resource.Texture;

import javax.swing.*;
import java.net.URL;

/**
 * Egy pályán elhelyezhető, látható entitást leíró osztály
 */
public abstract class EntityVisible extends Entity {
    public int cellX = 0;
    public int cellY = 0;
    protected int sizeX = 1;
    protected int sizeY = 1;
    protected int layer = 0;
    protected JLabel label = new JLabel();
    protected Texture texture = Texture.fallbackTexture;
    protected boolean visible = true;
    protected Level level = null;

    public boolean collisions = true;

    /**
     * Entitás inicializálása
     */
    public EntityVisible() {
        super();
    }

    /**
     * Entitás megadott koordinátákon
     */
    public EntityVisible(int cellX, int cellY, int sizeX, int sizeY) {
        this();
        this.cellX = cellX;
        this.cellY = cellY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        System.out.println("Spawned entity at x" + cellX + " y" + cellY);

        this.alignToCameraOffset();
    }

    /**
     * Entitás láthatóságának beállítása
     * @param visible
     */
    public void setVisible(boolean visible) {
        this.label.setVisible(visible);
        this.visible = visible;
    }

    /**
     * Látható-e az objektum?
     * @return Igaz, ha igen, hamis ha nem
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Textúra beállítása URL-el
     * @param textureURL Textúra URL-je
     */
    public void setTexture(URL textureURL) {
        this.texture.setTexture(textureURL);
    }

    /**
     * Textúra kicserélése másikra
     * @param texture Másik textúra
     */
    public void setTexture(Texture texture) {
        this.texture = texture;
        this.texture.setSize(this.sizeX, this.sizeY);
        //this.texture.setSizeX(this.sizeX);
        //this.texture.setSizeY(this.sizeY);
    }

    /**
     * Textúra lekérdezése
     * @return Entitás textúrája
     */
    public Texture getTexture() {
        return this.texture;
    }

    /**
     * Swing-es komponens (JLabel) lekérdezése
     * @return Swing JLabel Component
     */
    public JLabel getLabel() {
        return this.label;
    }

    /**
     * X koordináta beállítása
     * @param cellX X koordináta
     */
    public void setCellX(int cellX) {
        this.setCellPos(cellX, this.cellY);
    }

    /**
     * Y koordináta beállítása
     * @param cellY
     */
    public void setCellY(int cellY) {
        this.setCellPos(this.cellX, cellY);
    }

    /**
     * Réteg beállítása
     * @param layer Réteg
     */
    public void setLayer(int layer) {
        this.layer = layer;
    }

    /**
     * X koordináta lekérdezése
     * @return X koordináta
     */
    public int getCellX() {
        return cellX;
    }

    /**
     * Y koordináta lekérdezése
     * @return Y koordináta
     */
    public int getCellY() {
        return cellY;
    }

    /**
     * X térbeli pozíció lekérdezése<br>
     *     Térbeli pozíció: koordináták megszorzása a pálya cellaméretével
     * @return X térbeli pozíciója
     */
    public int getPosX() {
        return this.cellX * Game.gridSize;
    }

    /**
     * Y térbeli pozíció lekérdezése<br>
     *     Térbeli pozíció: koordináták megszorzása a pálya cellaméretével
     * @return Y térbeli pozíciója
     */
    public int getPosY() {
        return this.cellY * Game.gridSize;
    }

    /**
     * X méret lekérdezése
     * @return X méret
     */
    public int getSizeX() {
        return this.sizeX;
    }

    /**
     * Y méret lekérdezése
     * @return Y méret
     */
    public int getSizeY() {
        return this.sizeY;
    }

    /**
     * Térbeli szélesség lekérdezése<br>
     *     Térbeli szélesség: méret megszorzása a pálya cellaméretével
     * @return
     */
    public int getWidth() {
        return this.sizeX * Game.gridSize;
    }

    /**
     * Térbeli magasság lekérdezése<br>
     *     Térbeli szélesség: méret megszorzása a pálya cellaméretével
     * @return
     */
    public int getHeight() {
        return this.sizeY * Game.gridSize;
    }

    /**
     * Akkor hívódik meg, mikor az entitás el lett helyezve egy pályán
     * @param level Pálya, amin el lett helyezve
     */
    public void spawned(Level level) {
        this.level = level;
    }

    /**
     * X és Y koordináta beállításának megkísérlése<br>
     *     Esetleg nem sikerülhet, ha a pályán az adott cella már foglalt.
     * @param cellX X koordináta
     * @param cellY Y koordináta
     * @return Igaz, ha sikerült, hamis, ha nem
     */
    public boolean setCellPos(int cellX, int cellY) {
        System.out.println("Trying to move from: x" + this.cellX + " y" + this.cellY + " l" + this.layer + " to x" + cellX + " y" + cellY);

        if (Game.loadedLevel.getEntity(layer, this.cellX, this.cellY) != this) return false;
        if (!Game.loadedLevel.moveEntity(layer, this.cellX, this.cellY, layer, cellX, cellY)) return false;

        System.out.println("Success");

        this.cellX = cellX;
        this.cellY = cellY;
        alignToCameraOffset();

        return true;
    }

    /**
     * Kamerához igazítás, zoomhoz méretezés<br>
     *     Zoom akkor történhet, mikor megváltoztatjuk az ablak méretét
     */
    public void alignToCameraOffset() {
        MainView view = Game.mainView;
        MainCamera camera = Game.mainCamera;

        int newX = (int) Math.floor(this.cellX * Game.gridSize * view.zoom - camera.posX * view.zoom);
        int newY = (int) Math.floor(this.cellY * Game.gridSize * view.zoom - camera.posY * view.zoom);

        int newWidth = (int) Math.floor(this.getWidth() * view.zoom);
        int newHeight = (int) Math.floor(this.getHeight() * view.zoom);

        this.label.setBounds(newX, newY, newWidth, newHeight);

        label.setIcon(this.texture.getIcon());
    }
}
