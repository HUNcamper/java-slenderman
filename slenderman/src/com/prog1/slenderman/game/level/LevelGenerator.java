package com.prog1.slenderman.game.level;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.entities.Entity;
import com.prog1.slenderman.game.entities.EntityVisible;
import com.prog1.slenderman.game.entities.floor.EntFloorGrass;
import com.prog1.slenderman.game.entities.prop.*;
import com.prog1.slenderman.game.entities.prop.house.EntityHouse;
import com.prog1.slenderman.game.resource.URLHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

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
        Level level = new Level(5, 15, 15);

        spawnGrass(level);

        try {
            File file = new File(Objects.requireNonNull(URLHandler.convertString(path)).toURI());
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                EntityVisible ent = ParseLine(line);
                if (ent != null) {
                    level.spawnEntity(ent, 2, ent.cellX, ent.cellY);
                }
            }
            scanner.close();
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
            return preMade();
        }

        spawnPapers(level, 8);

        return level;
    }

    /**
     * Fájl egy sorának feldolgozása, és az őt leíró entitás visszaadása
     * @param line Szöveges sor
     * @return Entitás, amelyet leír a sor
     */
    private static EntityVisible ParseLine(String line) {
        if (line.equals("")) return null;
        if (line.startsWith("//")) return null;

        try {
            String[] split = line.split(" ");
            String entName = split[0];
            int entPosX = Integer.parseInt(split[1]);
            int entPosY = Integer.parseInt(split[2]);

            EntityVisible ent = GetEntity(entName);
            if (ent != null) {
                ent.setCellPos(entPosX, entPosY);
            }

            return ent;
        } catch (Exception e) {
            System.err.println("Tereptárgyat sikertelen volt beolvasni. Győződj meg róla, hogy jó formátumban van megadva!");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Név alapján entitás létrehozása
     * @param entName Entitás neve
     * @return Entitás
     */
    private static EntityVisible GetEntity(String entName) {
        switch (entName) {
            case "BarrelHor":
                return new PropBarrelHor();
            case "BarrelVer":
                return new PropBarrelVer();
            case "CarHor":
                return new PropCarHor();
            case "CarVer":
                return new PropCarVer();
            case "Rock":
                return new PropRock();
            case "TreeBig":
                return new PropTree();
            case "TreeSmall":
                return new PropTreeSmall();
            case "TruckHor":
                return new PropTruckHor();
            case "TruckVer":
                return new PropTruckVer();
            case "House":
                return new EntityHouse();
            default:
                return null;
        }
    }

    /**
     * Előre berögzített pálya létrehozása
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

    /**
     * A megadott pályára járható fű felület generálása
     * @param level Pálya
     */
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

        for (int i = 0; i < maxAmount; i++) {
            if (possiblePropList.size() == 0) break;

            int index = r.nextInt(possiblePropList.size());

            Prop prop = possiblePropList.get(index);
            prop.hasPaper = true;

            possiblePropList.remove(prop);
        }
    }
}
