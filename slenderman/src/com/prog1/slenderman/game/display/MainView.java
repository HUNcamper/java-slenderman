package com.prog1.slenderman.game.display;

import com.prog1.slenderman.game.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainView extends JPanel {
    private int baseResolution_width = 1920;
    private int baseResolution_height = 1080;
    private float baseRatio = (float) baseResolution_width / baseResolution_height;

    public MainView() {
        Border blackline = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackline);
    }

    public void update() {
        int windowWidth = Game.mainWindow.getWidth();
        int windowHeight = Game.mainWindow.getHeight();
        float newRatio = (float) windowWidth / windowHeight;

        int x = 0;
        int y = 0;

        if (newRatio > baseRatio) {
            Game.mainView.setBounds(x, y, (int) (baseResolution_width * ((float) windowHeight / baseResolution_height)), windowHeight);
        } else {
            Game.mainView.setBounds(x, y, windowWidth, (int) (baseResolution_height * ((float) windowWidth / baseResolution_width)));
        }


        System.out.println("Window size: w" + windowWidth + " h" + windowHeight);
        System.out.println("Panel size: w" + Game.mainView.getWidth() + " h" + Game.mainView.getHeight());

    }
}
