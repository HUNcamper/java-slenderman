package com.prog1.slenderman.game.level;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.entities.Entity;
import com.prog1.slenderman.game.entities.SlenderMan;
import com.prog1.slenderman.game.entities.prop.*;
import com.prog1.slenderman.game.entities.prop.house.EntityHouse;
import com.prog1.slenderman.game.entities.prop.house.PropHouseWall;

import java.util.ArrayList;
import java.util.Random;

public abstract class LevelGenerator {
    public static Level fromFile() {
        return new Level(5, 15, 15);
    }

    public static Level random() {
        Level level = new Level(5, 15, 15);

        level.spawnEntity(new PropTreeSmall(), 2, 7,0);
        level.spawnEntity(new PropTreeSmall(), 2, 14,0);

        level.spawnEntity(new PropTree(), 2, 1, 1);
        level.spawnEntity(new EntityHouse(), 2, 7, 1);

        level.spawnEntity(new PropTreeSmall(), 2, 14,4);

        level.spawnEntity(new PropRock(), 2, 1, 5);
        level.spawnEntity(new PropCarVer(), 2, 5, 5);

        level.spawnEntity(new PropTreeSmall(), 2, 13, 7);

        level.spawnEntity(new PropTreeSmall(), 2, 10, 8);

        level.spawnEntity(new PropTreeSmall(), 2, 4, 9);
        level.spawnEntity(new PropBarrelHor(), 2, 8, 9);

        level.spawnEntity(new PropTreeSmall(), 2, 1, 10);

        level.spawnEntity(new PropTreeSmall(), 2, 9, 11);

        level.spawnEntity(new PropTruckHor(), 2, 0, 12);
        level.spawnEntity(new PropRock(), 2, 10, 12);
        level.spawnEntity(new PropTreeSmall(), 2, 13, 12);

        level.spawnEntity(new PropTreeSmall(), 2, 6, 14);

        spawnPapers(level,8);

        return level;
    }

    public static void spawnPapers(Level level, int maxAmount) {
        // Másoljuk a listát
        ArrayList<Prop> possiblePropList = new ArrayList<Prop>();

        // Betesszük, ami Prop
        for (Entity ent : level.getEntityList()) {
            if (ent instanceof Prop) {
                Prop prop = (Prop) ent;

                if (prop.isPaperSurface()) {
                    possiblePropList.add(prop);
                }
            }
        }

        Random r = new Random();

        System.out.println("Possible prop list: " + possiblePropList.size());

        for (int i = 0; i < maxAmount; i++) {
            if (possiblePropList.size() == 0) break;

            int index = r.nextInt(possiblePropList.size());

            Prop prop = possiblePropList.get(index);
            prop.hasPaper = true;

            possiblePropList.remove(prop);

            System.out.println("Spawned paper #" + i);
        }
    }
}
