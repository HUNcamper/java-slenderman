package com.prog1.slenderman.game;

import com.prog1.slenderman.game.display.MainCamera;
import com.prog1.slenderman.game.display.MainView;
import com.prog1.slenderman.game.display.MainWindow;
import com.prog1.slenderman.game.entities.Player;
import com.prog1.slenderman.game.resource.Texture;
import com.prog1.slenderman.game.resource.URLHandler;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class Game {
    public static float globalVolume = 1.0f;
    public static MainWindow mainWindow;
    public static MainCamera mainCamera;
    public static MainView mainView;
    public static Player mainPlayer;
    public static HashMap<String, Texture> texturePool;

    public Game() {
        Game.texturePool = new HashMap<String, Texture>();
        Game.mainView = new MainView();
        Game.mainCamera = new MainCamera();
        Game.mainWindow = new MainWindow();
        Game.mainPlayer = new Player();

        Game.mainWindow.update();

        Game.mainWindow.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                Game.mainView.update();
                Game.mainWindow.update();
            }
        });


        Game.texturePool.put("dev.error", Texture.fallbackTexture);
    }
}
