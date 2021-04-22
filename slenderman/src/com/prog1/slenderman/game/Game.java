package com.prog1.slenderman.game;

import com.prog1.slenderman.game.display.MainCamera;
import com.prog1.slenderman.game.display.MainView;
import com.prog1.slenderman.game.display.MainWindow;
import com.prog1.slenderman.game.entities.EntTreeSmall;
import com.prog1.slenderman.game.entities.Entity;
import com.prog1.slenderman.game.entities.Player;
import com.prog1.slenderman.game.level.Level;
import com.prog1.slenderman.game.level.LevelGenerator;
import com.prog1.slenderman.game.resource.Sound;
import com.prog1.slenderman.game.resource.Texture;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    public static float globalVolume = 1.0f;
    public static MainWindow mainWindow;
    public static MainCamera mainCamera;
    public static MainView mainView;
    public static Player mainPlayer;
    public static Level loadedLevel;
    public static ArrayList<Entity> entityList;
    public static HashMap<String, Texture> texturePool;
    public static HashMap<String, Sound> soundPool;
    public static int gridSize = 50;
    public static boolean newStep = false;

    public Game() {
        Game.texturePool = new HashMap<String, Texture>();
        Game.soundPool = new HashMap<String, Sound>();
        Game.entityList = new ArrayList<Entity>();
        Game.mainView = new MainView();
        Game.mainCamera = new MainCamera();
        Game.loadedLevel = LevelGenerator.random();
        Game.mainWindow = new MainWindow();
        Game.mainPlayer = new Player(0, 0, 1, 1);

        Game.mainWindow.update();

        Game.mainWindow.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                Game.update();
            }
        });

        Game.texturePool.put("dev.error", Texture.fallbackTexture);

        Game.loadedLevel.spawnEntity(Game.mainPlayer, 1, 3, 3);
        Game.loadedLevel.spawnEntity(new EntTreeSmall(), 2, 6, 6);
        //Game.mainView.add(Game.mainPlayer.getLabel(), 1, 0);

        Action handleKeyPress = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Entity ent : Game.entityList) {
                    if (ent.isAcceptInput()) {
                        ent.handleInput(e);
                    }
                }
            }
        };

        addKeyBinding("w", KeyEvent.VK_W, handleKeyPress);
        addKeyBinding("d", KeyEvent.VK_D, handleKeyPress);
        addKeyBinding("s", KeyEvent.VK_S, handleKeyPress);
        addKeyBinding("a", KeyEvent.VK_A, handleKeyPress);
    }

    public static void update() {
        //Game.mainCamera.followPlayer();
        Game.mainView.update();
        Game.mainWindow.update();

        for (Entity ent : Game.entityList) {
            ent.update();
        }

        Game.newStep = false;

        System.out.println("Currently used textures: " + Game.texturePool.size());

        System.gc();
    }

    protected void addKeyBinding(String name, int keyCode, Action action) {
        InputMap inputMap = Game.mainView.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = Game.mainView.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(keyCode, 0, false), name + ".pressed");
        actionMap.put(name + ".pressed", action);

        //inputMap.put(KeyStroke.getKeyStroke(keyCode, 0, true), name + ".released");
        //actionMap.put(name + ".released", action);
    }
}
