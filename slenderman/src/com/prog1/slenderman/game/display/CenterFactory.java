package com.prog1.slenderman.game.display;

import javax.swing.*;

/**
 * Komponensek középpontra helyezéséhez használt számításokat kezelő osztály
 */
public abstract class CenterFactory {
    /**
     * Vízszintes középre helyezés
     * @param component Swing Komponens
     * @param parentWidth Szülő komponens szélessége
     * @return X koordináta, ahol mozgatva középre lesz helyezve a komponens
     */
    public static int CenterHorizontal(JComponent component, int parentWidth) {
        return parentWidth / 2 - component.getWidth() / 2;
    }

    /**
     * Függőleges középre helyezés
     * @param component Swing Komponens
     * @param parentHeight Szülő komponens magassága
     * @return Y koordináta, ahol mozgatva középre lesz helyezve a komponens
     */
    public static int CenterVertical(JComponent component, int parentHeight) {
        return parentHeight / 2 - component.getHeight() / 2;
    }
}
