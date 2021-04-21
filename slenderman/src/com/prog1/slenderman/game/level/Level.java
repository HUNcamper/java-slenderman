package com.prog1.slenderman.game.level;

import com.prog1.slenderman.game.entities.EntityVisible;
import com.prog1.slenderman.game.resource.Texture;

import javax.swing.*;

public class Level {
    private final int rows;
    private final int columns;
    private JLayeredPane display;

    private EntityVisible[][][] entities;

    public Level(int layers, int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        this.entities = new EntityVisible[layers][rows][columns];
    }

    public boolean spawnEntity(EntityVisible entity, int layer, int cell_x, int cell_y) {
        if (isOutOfBounds(cell_x, cell_y)) return false;

        if (entities[layer][cell_y][cell_x] == null) {
            entities[layer][cell_y][cell_x] = entity;

            this.display.add(entity.getLabel(), layer, 0);

            return true;
        }

        return false;
    }

    public boolean removeEntity(int layer, int cell_x, int cell_y) {
        if (isOutOfBounds(cell_x, cell_y)) return false;
        if (entities[layer][cell_y][cell_x] == null) return false;

        entities[layer][cell_y][cell_x] = null;

        return true;
    }

    public boolean moveEntity(int from_layer, int from_cell_x, int from_cell_y, int to_layer, int to_cell_x, int to_cell_y) {
        if (isOutOfBounds(from_cell_x, from_cell_y) || isOutOfBounds(to_cell_x, to_cell_y)) return false;

        if (entities[from_layer][from_cell_y][from_cell_x] == null ||
                entities[to_layer][to_cell_y][to_cell_x] != null) {
            return false;
        }

        EntityVisible toMove = entities[from_layer][from_cell_y][from_cell_y];
        Texture texture = toMove.getTexture();

        entities[to_layer][to_cell_y][to_cell_x] = toMove;
        entities[from_layer][from_cell_y][from_cell_y] = null;

        toMove.setPos_x(to_cell_x * texture.getWidth());
        toMove.setPos_y(to_cell_y * texture.getHeight());

        return true;
    }

    public boolean offsetEntity(int layer, int cell_x, int cell_y, int offset_x, int offset_y) {
        return moveEntity(layer, cell_x, cell_y, layer, cell_x + offset_x, cell_y + offset_y);
    }

    public boolean isOutOfBounds(int cell_x, int cell_y) {
        // Out of bounds? if yes, return yes
        return (cell_x < 0 || cell_x >= this.columns || cell_y < 0 || cell_y >= this.rows);
    }
}
