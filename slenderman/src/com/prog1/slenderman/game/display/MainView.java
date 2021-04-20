package com.prog1.slenderman.game.display;

import com.prog1.slenderman.game.Game;

import javax.swing.*;

public class MainView extends JPanel {
    private int baseResolution_width = 1920;
    private int baseResolution_height = 1080;
    private float baseRatio = (float) baseResolution_width / baseResolution_height;

    public void update() {
        int windowWidth = Game.mainWindow.getWidth();
        int windowHeight = Game.mainWindow.getHeight();
        float newRatio = (float) windowWidth / windowHeight;

        int x = 100;
        int y = 100;

        if(newRatio > baseRatio) {
            Game.mainView.setBounds(x, y, baseResolution_width * (windowHeight / baseResolution_height), windowHeight);
        } else {
            Game.mainView.setBounds(x, y, windowWidth, baseResolution_height * (windowWidth / baseResolution_width));
        }

    }
}
