package com.prog1.slenderman.game.level;

import com.prog1.slenderman.game.level.entities.Entity;

public class Level {
    private final int size_x;
    private final int size_y;

    private Entity[][] entities;

    public Level() {
        this.size_x = 15;
        this.size_y = 15;
    }

    public Level(int size_x, int size_y) {
        this.size_x = size_x;
        this.size_y = size_y;
    }

    public void placeEntity(Entity entity, int x, int y) {

    }
}
