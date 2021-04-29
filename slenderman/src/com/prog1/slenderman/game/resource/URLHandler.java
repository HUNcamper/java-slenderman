package com.prog1.slenderman.game.resource;

import com.prog1.slenderman.Main;

import java.net.URL;

/**
 * URL-ek kezelésére szolgáló osztály
 */
public class URLHandler {
    /**
     * String elérési útvonal átkonvertálása URL formátumba
     * @param path Elérési útvonal
     * @return URL osztály
     */
    public static URL convertString(String path) {
        URL url;
        if (path.startsWith("/")) {
            url = Main.class.getResource(path); // Belső resource fájl
        } else {
            try {
                url = new URL(path); // Külső fájl, absolute path
            } catch (Exception e) {
                return null;
            }
        }

        return url;
    }
}
