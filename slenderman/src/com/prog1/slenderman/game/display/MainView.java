package com.prog1.slenderman.game.display;

import com.prog1.slenderman.game.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainView extends JPanel {
    private final int baseResolution_width = 1920;
    private final int baseResolution_height = 1080;
    private final float baseRatio = (float) baseResolution_width / baseResolution_height;
    private final float baseZoom = 3f; // change this
    public float zoom = 1f; // automatically calculated at runtime

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

        int newWidth = Game.mainView.getWidth();
        int newHeight = Game.mainView.getHeight();

        this.zoom = ((float) newWidth / baseResolution_width) * baseZoom;

        //System.out.println(this.zoom);

        //System.out.println("Window size: w" + windowWidth + " h" + windowHeight);
        //System.out.println("Panel size: w" + newWidth + " h" + newHeight);

    }
}
