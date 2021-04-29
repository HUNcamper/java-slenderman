package com.prog1.slenderman.game.resource;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Swing fájl választó ablak kezelő
 */
public abstract class SwingFileChooser {
    /**
     * Fájl választó ablak megnyitása a jelenlegi mappából
     *
     * @param caller Meghívó komponens
     * @return Fájl elérési útvonala
     */
    public static String open(Component caller) {
        File workingDirectory = new File(System.getProperty("user.dir"));
        return open(caller, workingDirectory);
    }

    /**
     * Fájl választó ablak megnyitása a megadott mappából
     *
     * @param caller Meghívó komponens
     * @param start  Megadott mappa
     * @return Fájl elérési útvonala
     */
    public static String open(Component caller, File start) {
        JFileChooser fc = new JFileChooser();

        if (start != null) {
            fc.setCurrentDirectory(start);
        }

        int result = fc.showOpenDialog(caller);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fc.getSelectedFile();
            return selectedFile.getAbsolutePath();
        } else {
            return null;
        }
    }
}
