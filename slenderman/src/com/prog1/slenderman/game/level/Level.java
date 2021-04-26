package com.prog1.slenderman.game.level;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.entities.EntityVisible;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Level {
    private final int rows;
    private final int columns;
    private final JLayeredPane display = Game.mainView;

    private ArrayList<EntityVisible> entityList;

    private EntityVisible[][][] entities;

    /**
     * Manhattan-távolság kiszámítása két pont között
     *
     * @param x1 Első pont X koordinátája
     * @param y1 Első pont Y koordinátája
     * @param x2 Második pont X koordinátája
     * @param y2 Második pont Y koordinátája
     * @return Manhattan-távolság
     */
    public static int manhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    /**
     * Koordináták lekérése, amik legfeljebb vagy legalább ennyi Manhattan-távolságra vannak egy adott ponttól.
     *
     * @param cellX    Adott pont X koordinátája
     * @param cellY    Adott pont Y koordinátája
     * @param distance Távolság
     * @param maximum  Legfeljebb ennyi, vagy legalább ennyi távolság?
     * @return X, Y koordináta párok listája
     */
    public List<int[]> getManhattanCoordinates(int cellX, int cellY, int distance, boolean maximum) {
        List<int[]> coordinateList = new ArrayList<int[]>();

        for (int y = 0; y < this.rows; y++) {
            for (int x = 0; x < this.columns; x++) {
                if (x == cellX && y == cellY) continue;

                int manhattan = Level.manhattanDistance(cellX, cellY, x, y);

                if (maximum) {
                    if (manhattan <= distance) {
                        coordinateList.add(new int[]{x, y});
                    }
                } else {
                    if (manhattan >= distance) {
                        coordinateList.add(new int[]{x, y});
                    }
                }
            }
        }

        return coordinateList;
    }

    /**
     * Pályán levő sorok száma
     *
     * @return Sorok száma
     */
    public int getRows() {
        return rows;
    }

    /**
     * Pályán levő oszlopok száma
     *
     * @return Oszlopok száma
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Pályán levő entitások listája
     *
     * @return Entitások listája
     */
    public ArrayList<EntityVisible> getEntityList() {
        return entityList;
    }

    /**
     * Üres pálya inicializálása
     *
     * @param layers  Rétegek száma
     * @param rows    Sorok száma
     * @param columns Oszlopok száma
     */
    public Level(int layers, int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        this.entities = new EntityVisible[layers][rows][columns];
        this.entityList = new ArrayList<EntityVisible>();
    }

    /**
     * Elhelyezhető entitás (EntityVisible) elhelyezése a pályának adott koordinátáin és rétegén
     *
     * @param entity Entitás
     * @param layer  Réteg
     * @param cellX  X koordináta
     * @param cellY  Y koordináta
     * @return Igaz, ha sikerült az elhelyezés, hamis ha nem.
     */
    public boolean spawnEntity(EntityVisible entity, int layer, int cellX, int cellY) {
        if (isOutOfBounds(cellX, cellY)) return false;

        entity.cellX = cellX;
        entity.cellY = cellY;
        entity.setLayer(layer);

        this.entityList.add(entity);

        for (int y = cellY; y < cellY + entity.getSizeY(); y++) {
            for (int x = cellX; x < cellX + entity.getSizeX(); x++) {
                if (entities[layer][y][x] == null) {
                    entities[layer][y][x] = entity;

                    this.display.add(entity.getLabel(), layer, 0);

                    entity.spawned(this);
                } else {
                    return false;
                }
            }
        }

        return false;
    }

    /**
     * Entitás kitörlése a pálya egy adott koordinátájáról és rétegéről
     *
     * @param layer Réteg
     * @param cellX X koordináta
     * @param cellY Y koordináta
     * @return Igaz, ha sikerült a törlés, hamis ha nem.
     */
    public boolean removeEntity(int layer, int cellX, int cellY) {
        if (isOutOfBounds(cellX, cellY)) return false;
        if (entities[layer][cellY][cellX] == null) return false;

        Game.mainView.remove(entities[layer][cellY][cellX].getLabel());
        entities[layer][cellY][cellX] = null;
        this.entityList.remove(entities[layer][cellY][cellX]);

        return true;
    }

    /**
     * Entitás mozgatása rétegek és koordináták között
     *
     * @param fromLayer Réteg (Innen)
     * @param fromCellX X koordináta (Innen)
     * @param fromCellY Y koordináta (Innen)
     * @param toLayer   Réteg (Ide)
     * @param toCellX   X koordináta (Ide)
     * @param toCellY   Y koordináta (Ide)
     * @return Igaz, ha sikerült a mozgatás, hamis ha nem.
     */
    public boolean moveEntity(int fromLayer, int fromCellX, int fromCellY, int toLayer, int toCellX, int toCellY) {
        if (isOutOfBounds(fromCellX, fromCellY) || isOutOfBounds(toCellX, toCellY)) return false;

        if (isEmpty(fromLayer, fromCellX, fromCellY) || !isEmpty(toLayer, toCellX, toCellY)) {
            return false;
        }

        EntityVisible toMove = entities[fromLayer][fromCellY][fromCellX];

        entities[toLayer][toCellY][toCellX] = toMove;
        entities[fromLayer][fromCellY][fromCellX] = null;

        toMove.cellX = toCellX;
        toMove.cellY = toCellY;

        return true;
    }

    /**
     * Entitás relatív mozgatása ugyanazon a rétegen
     *
     * @param layer   Réteg
     * @param cellX   X koordináta
     * @param cellY   Y koordináta
     * @param offsetX Ennyi X-el mozgatás (lehet negatív)
     * @param offsetY Ennyi Y-al mozgatás (lehet negatív)
     * @return Igaz, ha sikerült a mozgatás, hamis ha nem.
     */
    public boolean offsetEntity(int layer, int cellX, int cellY, int offsetX, int offsetY) {
        return moveEntity(layer, cellX, cellY, layer, cellX + offsetX, cellY + offsetY);
    }

    /**
     * Entitás lekérése egy adott koordinátárol és rétegről
     *
     * @param layer Réteg
     * @param cellX X koordináta
     * @param cellY Y koordináta
     * @return Adott ponton levő entitás
     */
    public EntityVisible getEntity(int layer, int cellX, int cellY) {
        if (isOutOfBounds(cellX, cellY)) return null;

        return entities[layer][cellY][cellX];
    }

    /**
     * Üres-e az adott pont?
     *
     * @param layer Réteg
     * @param cellX X koordináta
     * @param cellY Y koordináta
     * @return Igaz ha üres, hamis ha nem.
     */
    public boolean isEmpty(int layer, int cellX, int cellY) {
        return entities[layer][cellY][cellX] == null;
    }

    /**
     * Az adott pont a pályán kívülre esik?
     *
     * @param cellX X koordináta
     * @param cellY Y koordináta
     * @return Igaz, ha a pont a pályán kívülre esik, hamis ha nem.
     */
    public boolean isOutOfBounds(int cellX, int cellY) {
        // Out of bounds? if yes, return yes
        return (cellX < 0 || cellX >= this.columns || cellY < 0 || cellY >= this.rows);
    }
}
