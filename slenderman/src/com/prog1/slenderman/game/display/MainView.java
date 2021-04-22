package com.prog1.slenderman.game.display;

import com.prog1.slenderman.game.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainView extends JLayeredPane {
    public final int baseResolution_width = 1500;
    public final int baseResolution_height = 1500;
    private final float baseRatio = (float) baseResolution_width / baseResolution_height;
    public float baseZoom = 2f; // change this
    public float zoom = 1f; // automatically calculated at runtime

    public int padding = 100;

    public MainView() {
        Border blackline = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackline);
    }

    public void update() {
        int windowWidth = Game.mainWindow.getWidth();
        int windowHeight = Game.mainWindow.getHeight();
        float newRatio = (float) windowWidth / windowHeight;

        int new_x = 0;
        int new_y = 0;

        if (newRatio > baseRatio) {
            int new_width = (int) (baseResolution_width * ((float) windowHeight / baseResolution_height)) - this.padding;
            int new_height = windowHeight - this.padding;
            new_x = windowWidth / 2 - new_width / 2;
            new_y = this.padding / 2;

            Game.mainView.setBounds(new_x, new_y, new_width, new_height);
        } else {
            int new_width = windowWidth - this.padding;
            int new_height = (int) (baseResolution_height * ((float) windowWidth / baseResolution_width)) - this.padding;
            new_x = this.padding / 2;
            new_y = windowHeight / 2 - new_height / 2;

            Game.mainView.setBounds(new_x, new_y, new_width, new_height);
        }

        int newWidth = Game.mainView.getWidth();

        this.zoom = ((float) newWidth / baseResolution_width) * baseZoom;
    }
}
