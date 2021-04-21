package com.prog1.slenderman.game;

import com.prog1.slenderman.game.display.MainCamera;
import com.prog1.slenderman.game.display.MainView;
import com.prog1.slenderman.game.display.MainWindow;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Game {
    public static float globalVolume = 1.0f;
    public static MainWindow mainWindow;
    public static MainCamera mainCamera;
    public static MainView mainView;

    public Game() {
        Game.mainView = new MainView();
        Game.mainCamera = new MainCamera();
        Game.mainWindow = new MainWindow();

        Game.mainWindow.update();

        Game.mainWindow.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                Game.mainView.update();
                Game.mainWindow.update();
            }
        });
    }
}
