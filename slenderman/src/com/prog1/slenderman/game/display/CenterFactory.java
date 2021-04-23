package com.prog1.slenderman.game.display;

import com.prog1.slenderman.game.Game;

import javax.swing.*;

public abstract class CenterFactory {
    public static int CenterHorizontal(JComponent component, int parentWidth) {
        return parentWidth / 2 - component.getWidth() / 2;
    }

    public static int CenterVertical(JComponent component, int parentHeight) {
        return parentHeight / 2 - component.getHeight() / 2;
    }
}
