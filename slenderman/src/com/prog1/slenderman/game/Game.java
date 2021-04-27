package com.prog1.slenderman.game;

import com.prog1.slenderman.game.display.MainCamera;
import com.prog1.slenderman.game.display.MainView;
import com.prog1.slenderman.game.display.MainWindow;
import com.prog1.slenderman.game.entities.*;
import com.prog1.slenderman.game.level.Level;
import com.prog1.slenderman.game.level.LevelGenerator;
import com.prog1.slenderman.game.resource.Sound;
import com.prog1.slenderman.game.resource.Texture;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A játék osztály írja le a játék működését, és tárolja a fontosabb globális játékelemeket.
 *
 * @version 1.0
 */
public class Game {
    public static SlenderManOverlay slenderOverlay;
    public static SlenderMan slenderMan;
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
    public static int pagesCollected = 0;
    public static boolean gameOver = false;

    /**
     * Játék inicializálása
     */
    public Game() {
        Game.mainWindow = new MainWindow();

        start();

        Game.mainWindow.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                Game.update();
            }
        });

        Action handleKeyPress = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Entity ent : Game.entityList) {
                    if (ent.isAcceptInput()) {
                        ent.handleInput(e);
                    }
                }

                if (Game.gameOver) {
                    Game.handleGameOver(true);
                }
            }
        };

        addKeyBinding("w", KeyEvent.VK_W, handleKeyPress);
        addKeyBinding("d", KeyEvent.VK_D, handleKeyPress);
        addKeyBinding("s", KeyEvent.VK_S, handleKeyPress);
        addKeyBinding("a", KeyEvent.VK_A, handleKeyPress);
        addKeyBinding("f", KeyEvent.VK_F, handleKeyPress);

        Game.update();
    }

    private static void start() {
        Game.gameOver = false;

        if (Game.slenderMan != null) {
            Game.slenderMan.stopSounds();
        }

        if (Game.entityList != null) {
            Game.entityList.clear();
        }

        Game.texturePool = new HashMap<String, Texture>();
        Game.soundPool = new HashMap<String, Sound>();
        Game.entityList = new ArrayList<Entity>();
        Game.mainView = new MainView();
        Game.mainCamera = new MainCamera();
        //Game.loadedLevel = LevelGenerator.preMade();
        Game.loadedLevel = LevelGenerator.fromFile("/test.txt");
        Game.mainPlayer = new Player(0, 0, 1, 1);
        Game.slenderOverlay = new SlenderManOverlay();
        Game.slenderMan = new SlenderMan();
        Game.pagesCollected = 0;

        Game.loadedLevel.spawnEntity(Game.slenderMan, 3, 0, 0);
        Game.loadedLevel.spawnEntity(Game.slenderOverlay, 4, 0, 0);
        Game.loadedLevel.spawnEntity(Game.mainPlayer, 1, 0, 0);

        Game.mainWindow.setupMainView(Game.mainView);

        MusicPlayer mp = new MusicPlayer(); // automatikusan updateli a newStep metódus

        try {
            Sound ambient = new Sound("/sound/ambient/frogs_loop1.wav");
            ambient.setLoop(true);
            ambient.setVolume(0.1f);
            ambient.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * A játék érjen véget.
     *
     * @param fail Vesztett a player?
     */
    public static void handleGameOver(boolean fail) {
        if (fail) {
            System.out.println("Game over!");
            //Game.mainPlayer.setVisible(false);
            //Game.mainPlayer.setAcceptInput(false);
        }

        Game.mainWindow.remove(Game.mainView);
        start();
        Game.mainWindow.repaint();
    }

    /**
     * Elemek megjelenítésének frissítése,<br>
     * illetve új lépésnél jelzés az entitásoknak
     */
    public static void update() {
        //Game.mainCamera.followPlayer();
        Game.mainView.update();
        Game.mainWindow.update();

        if (Game.newStep) {
            Game.newStep = false;

            for (Entity ent : Game.entityList) {
                ent.newStep();
            }
        }

        System.gc();
    }

    /**
     * Billentyű lenyomás figyelése
     *
     * @param name    Jelző név
     * @param keyCode Billentyűkód
     * @param action  Swing Action
     */
    protected void addKeyBinding(String name, int keyCode, Action action) {
        InputMap inputMap = Game.mainWindow.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = Game.mainWindow.getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(keyCode, 0, false), name + ".pressed");
        actionMap.put(name + ".pressed", action);
    }
}
