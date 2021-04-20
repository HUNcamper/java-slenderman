package com.prog1.slenderman.game.level;

import com.prog1.slenderman.game.entities.Entity;

public class Level {
    private final int rows;
    private final int columns;

    private Entity[][] entities;

    public Level() {
        this(15, 15);
    }

    public Level(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        this.entities = new Entity[rows][columns];
    }

    public void placeEntity(Entity entity, int x, int y) {

    }
}
