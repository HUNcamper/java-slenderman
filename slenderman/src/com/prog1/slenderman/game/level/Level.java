package com.prog1.slenderman.game.level;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.entities.PropRockSmall;
import com.prog1.slenderman.game.entities.PropTreeSmall;
import com.prog1.slenderman.game.entities.EntityVisible;

import javax.swing.*;

enum Layer {
    LAYER_FLOOR,
    LAYER_PLAYER,
    LAYER_PROP,
    LAYER_SLENDERMAN
};

public class Level {
    private final int rows;
    private final int columns;
    private final JLayeredPane display = Game.mainView;

    private EntityVisible[][][] entities;

    public Level(int layers, int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        this.entities = new EntityVisible[layers][rows][columns];

        spawnEntity(new PropTreeSmall(), 2, 6, 6);
        spawnEntity(new PropRockSmall(), 2, 3, 3);
    }

    public boolean spawnEntity(EntityVisible entity, int layer, int cellX, int cellY) {
        if (isOutOfBounds(cellX, cellY)) return false;

        entity.cellX = cellX;
        entity.cellY = cellY;
        entity.setLayer(layer);

        for (int y = cellY; y < cellY + entity.getSizeY(); y++) {
            for (int x = cellX; x < cellX + entity.getSizeX(); x++) {
                if (entities[layer][y][x] == null) {
                    entities[layer][y][x] = entity;

                    this.display.add(entity.getLabel(), layer, 0);
                } else {
                    return false;
                }
            }
        }

        return false;
    }

    public boolean removeEntity(int layer, int cell_x, int cell_y) {
        if (isOutOfBounds(cell_x, cell_y)) return false;
        if (entities[layer][cell_y][cell_x] == null) return false;

        entities[layer][cell_y][cell_x] = null;

        return true;
    }

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

    public boolean offsetEntity(int layer, int cell_x, int cell_y, int offset_x, int offset_y) {
        return moveEntity(layer, cell_x, cell_y, layer, cell_x + offset_x, cell_y + offset_y);
    }

    public EntityVisible getEntity(int layer, int cellX, int cellY) {
        return entities[layer][cellY][cellX];
    }

    public boolean isEmpty(int layer, int cellX, int cellY) {
        return entities[layer][cellY][cellX] == null;
    }

    public boolean isOutOfBounds(int cell_x, int cell_y) {
        // Out of bounds? if yes, return yes
        return (cell_x < 0 || cell_x >= this.columns || cell_y < 0 || cell_y >= this.rows);
    }
}
