package com.prog1.slenderman.game.entities.prop.house;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.entities.EntityVisible;
import com.prog1.slenderman.game.entities.floor.EntFloorConcrete;
import com.prog1.slenderman.game.entities.prop.Prop;
import com.prog1.slenderman.game.level.Level;

import java.util.ArrayList;
import java.util.Random;

public class EntityHouse extends EntityVisible {
    private Prop[][] house; // [height][width]
    private final int width = 6;
    private final int height = 6;

    public EntityHouse() {
        this.house = new Prop[height][width];
        int[][] walls = new int[height][width];

        walls[0] = new int[] { 2, 5, 6, 5, 5, 3};
        walls[1] = new int[] {-1,-1, 4,-1,-1,-1};
        walls[2] = new int[] { 4,-1, 4,-1,-1, 4};
        walls[3] = new int[] { 4,-1, 4,-1, 5, 9};
        walls[4] = new int[] { 4,-1,-1,-1,-1, 4};
        walls[5] = new int[] { 0, 5, 7, 5, 5, 1};

        createWalls(walls);
    }

    private void allowOnePaper() {
        ArrayList<Prop> list = new ArrayList<Prop>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (house[y][x] == null) continue;
                if (house[y][x].isPaperSurface()) {
                    list.add(house[y][x]);
                }
            }
        }

        Random r = new Random();
        int index = r.nextInt(list.size());

        for (int i = 0; i < list.size(); i++) {
            if (i == index) continue;

            list.get(i).setPaperSurface(false);
        }
    }

    private void createWalls(int[][] walls) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (walls[y][x] == -1) continue;
                this.house[y][x] = new PropHouseWall(walls[y][x]);
            }
        }
    }

    private void spawnHouseWalls() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (house[y][x] != null) {
                    int newX = x + this.cellX;
                    int newY = y + this.cellY;
                    this.level.spawnEntity(house[y][x], 2, newX, newY);
                }
            }
        }
    }

    private void spawnFloor() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int newX = x + this.cellX;
                int newY = y + this.cellY;
                this.level.removeEntity(0, newX, newY);
                this.level.spawnEntity(new EntFloorConcrete(newX, newY, 1, 1), 0, newX, newY);
            }
        }
    }

    @Override
    public void spawned(Level level) {
        super.spawned(level);

        // Remove self
        level.removeEntity(this.layer, this.cellX, this.cellY);

        spawnHouseWalls();
        allowOnePaper();
        spawnFloor();
    }
}
