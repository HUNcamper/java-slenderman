package com.prog1.slenderman.game.level;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.entities.Entity;
import com.prog1.slenderman.game.entities.floor.EntFloor;
import com.prog1.slenderman.game.entities.floor.EntFloorGrass;
import com.prog1.slenderman.game.entities.prop.*;
import com.prog1.slenderman.game.entities.prop.house.EntityHouse;

import java.util.ArrayList;
import java.util.Random;

/**
 * Pálya generátor osztály
 */
public abstract class LevelGenerator {
    /**
     * Pálya generálása fájlból
     *
     * @param path Fájl útvonal
     * @return Pálya
     */
    public static Level fromFile(String path) {
        return new Level(5, 15, 15);
    }

    /**
     * Előre létrehozott pálya létrehozása
     *
     * @return Pálya
     */
    public static Level preMade() {
        Level level = new Level(5, 15, 15);

        spawnGrass(level);

        level.spawnEntity(new PropTreeSmall(), 2, 7, 0);
        level.spawnEntity(new PropTreeSmall(), 2, 14, 0);

        level.spawnEntity(new PropTree(), 2, 1, 1);
        level.spawnEntity(new EntityHouse(), 2, 7, 1);

        level.spawnEntity(new PropTreeSmall(), 2, 14, 4);

        level.spawnEntity(new PropRock(), 2, 1, 5);
        level.spawnEntity(new PropCarVer(), 2, 5, 5);

        level.spawnEntity(new PropTreeSmall(), 2, 13, 7);

        level.spawnEntity(new PropTreeSmall(), 2, 10, 8);
        level.spawnEntity(new PropCarHor(), 2, 12, 8);

        level.spawnEntity(new PropTreeSmall(), 2, 4, 9);
        level.spawnEntity(new PropTree(), 2, 5, 9);
        level.spawnEntity(new PropBarrelHor(), 2, 8, 9);

        level.spawnEntity(new PropTreeSmall(), 2, 1, 10);

        level.spawnEntity(new PropTreeSmall(), 2, 9, 11);

        level.spawnEntity(new PropTruckHor(), 2, 0, 12);
        level.spawnEntity(new PropRock(), 2, 10, 12);
        level.spawnEntity(new PropTreeSmall(), 2, 13, 12);

        level.spawnEntity(new PropTreeSmall(), 2, 6, 14);

        spawnPapers(level, 8);

        return level;
    }

    public static void spawnGrass(Level level) {
        for (int y = 0; y < level.getRows(); y++) {
            for (int x = 0; x < level.getColumns(); x++) {
                EntFloorGrass grassFloor = new EntFloorGrass(x, y, 1, 1);

                level.spawnEntity(grassFloor, 0, x, y);
            }
        }
    }

    /**
     * Papírok véletlenszerű elhelyezése.<br>
     * Amennyiben nincs elég tárgy a pályán, az összes tárgyra kerül papír.
     *
     * @param level     Pálya, amin tárgyak vannak
     * @param maxAmount Hány db papír max
     */
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
