package com.prog1.slenderman.game.entities.prop.house;

import com.prog1.slenderman.game.entities.EntityVisible;
import com.prog1.slenderman.game.entities.floor.EntFloorConcrete;
import com.prog1.slenderman.game.entities.prop.Prop;
import com.prog1.slenderman.game.level.Level;

import java.util.ArrayList;
import java.util.Random;

/**
 * Egy teljes házat leíró, elhelyezhető entitás
 */
public class EntityHouse extends EntityVisible {
    private Prop[][] house; // [height][width]
    private final int width = 6;
    private final int height = 7;

    /**
     * Ház inicializálása egy megadott mérettel és elrendezéssel.<br>
     *     Az elrendezést a walls 2D tömb írja le, a számok a falak textúra indexeit írják le.
     */
    public EntityHouse() {
        this.house = new Prop[height][width];
        int[][] walls = new int[height][width];

        walls[0] = new int[] { 2, 5, 6, 5, 5, 3};
        walls[1] = new int[] {-1,-1, 4,-1,-1,-1};
        walls[2] = new int[] { 4,-1, 4,-1,-1, 4};
        walls[3] = new int[] { 4,-1, 4,-1, 5, 9};
        walls[4] = new int[] { 4,-1,-1,-1,-1, 4};
        walls[5] = new int[] { 4,-1, 4,-1,-1, 4};
        walls[6] = new int[] { 0, 5, 7, 5, 5, 1};

        createWalls(walls);
    }

    /**
     * Összes fal tereptárgy átfutása, és csak egyre engedjük meg, hogy lehetséges papír felület legyen.<br>
     *     Ez a feladat leírás miatt kell, ugyanis egy tereptárgyra (itt a ház egy tereptárgy alapvetően) csak egy papír kerülhet.
     */
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

    /**
     * Falak példányosítása egy 2D szám tömb alapján. Ha -1 szerepel, nem lesz példányosítva fal.
     * @param walls 2D szám tömb, a falak textúra indexével ellátva
     */
    private void createWalls(int[][] walls) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (walls[y][x] == -1) continue;
                this.house[y][x] = new PropHouseWall(walls[y][x]);
            }
        }
    }

    /**
     * Falak elhelyezése a pályán
     */
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

    /**
     * Járható felület elhelyezése a pályán, a ház alatt.<br>
     *     Ha már van meglévő járható felület, akkor azok kicserélése.
     */
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

    /**
     * Pályán való elhelyezés után hívódik meg.<br>
     *     Ezt az entitást kitöröljük, hisz a falak már reprezentálják a házat.<br>
     *         Ezután elhelyezzük a falakat, padlót, és csak egyik falra engedünk papírt helyezni.
     * @param level Pálya, amin el lett helyezve
     */
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
