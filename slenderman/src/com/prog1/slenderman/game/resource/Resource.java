package com.prog1.slenderman.game.resource;

/**
 * Egy resource-t leíró osztály.<br>
 * A játékban a külső fájlok, pl. hang, képek, stb. tárolására alkalmas.
 */
public class Resource {
    protected String name;

    /**
     * Resource inicializálása név nélkül
     */
    public Resource() {

    }

    /**
     * Resource inicializálása névvel
     *
     * @param name Resource neve
     */
    public Resource(String name) {
        this.name = name;
    }
}
