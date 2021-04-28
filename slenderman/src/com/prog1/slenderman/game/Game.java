package com.prog1.slenderman.game;

import com.prog1.slenderman.game.display.MainCamera;
import com.prog1.slenderman.game.display.MainView;
import com.prog1.slenderman.game.display.MainWindow;
import com.prog1.slenderman.game.entities.*;
import com.prog1.slenderman.game.level.Level;
import com.prog1.slenderman.game.level.LevelGenerator;
import com.prog1.slenderman.game.resource.SwingFileChooser;
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
    public static MainWindow mainWindow;
    public static MainCamera mainCamera;
    public static MainView mainView;
    public static Player mainPlayer;
    public static Level loadedLevel;
    public static ArrayList<Entity> entityList;
    public static HashMap<String, Texture> texturePool;
    public static HashMap<String, Sound> soundPool;

    public static float globalVolume = 1.0f;
    public static int gridSize = 50;
    public static boolean newStep = false;
    public static int pagesCollected = 0;
    public static boolean gameOver = false;

    private static Sound ambient;
    private static MusicPlayer mp;
    
    private static JButton bStart;
    private static JButton bFile;

    /**
     * Játék inicializálása
     */
    public Game() {
        Game.texturePool = new HashMap<String, Texture>();
        Game.soundPool = new HashMap<String, Sound>();
        Game.mainWindow = new MainWindow();

        Game.mainWindow.showMainMenu();

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
                    Game.gameOver = false;
                }
            }
        };
        
        try {
            if (Game.ambient != null) {
                Game.ambient.stop();
            }

            Game.ambient = new Sound("/sound/ambient/frogs_loop1.wav");
            Game.ambient.setLoop(true);
            Game.ambient.setVolume(0.1f);
            Game.ambient.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

        addKeyBinding("w", KeyEvent.VK_W, handleKeyPress);
        addKeyBinding("d", KeyEvent.VK_D, handleKeyPress);
        addKeyBinding("s", KeyEvent.VK_S, handleKeyPress);
        addKeyBinding("a", KeyEvent.VK_A, handleKeyPress);
        addKeyBinding("f", KeyEvent.VK_F, handleKeyPress);

        Game.update();
    }

    /**
     * Adattagok inicializálása, játék elindítása
     */
    public static void startGame(String mapFile) {
        Game.mainWindow.getTitleLabel().setVisible(true);
        Game.gameOver = false;

        Game.entityList = new ArrayList<Entity>();
        Game.mainView = new MainView();
        Game.mainCamera = new MainCamera();
        if (mapFile != null) {
            Game.loadedLevel = LevelGenerator.fromFile(mapFile);
        } else {
            Game.loadedLevel = LevelGenerator.preMade();
        }
        Game.mainPlayer = new Player(0, 0, 1, 1);
        Game.slenderOverlay = new SlenderManOverlay();
        Game.slenderMan = new SlenderMan();
        Game.pagesCollected = 0;

        Game.loadedLevel.spawnEntity(Game.slenderMan, 3, 0, 0);
        Game.loadedLevel.spawnEntity(Game.slenderOverlay, 4, 0, 0);
        Game.loadedLevel.spawnEntity(Game.mainPlayer, 1, 0, 0);

        Game.mainWindow.setupMainView(Game.mainView);

        Game.mp = new MusicPlayer(); // automatikusan updateli a newStep metódus

        Game.update();
    }

    /**
     * A játék érjen véget.
     *
     * @param fail Vesztett a player?
     */
    public static void handleGameOver(boolean fail) {
        System.out.println("Game over!");

        for (Entity ent : Game.entityList) {
            ent.setAcceptInput(false);
        }

        String text = "GAME OVER";
        if (!fail) text = "YOU WIN!";

        setLabelText(text);

        ActionListener taskPerformer = evt -> {
            endGame();
        };

        Timer timer = new Timer(2000, taskPerformer);
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Játék leállítása, visszatérés a főmenübe
     */
    private static void endGame() {
        if (Game.slenderMan != null) {
            Game.slenderMan.stopSounds();
        }

        if (Game.entityList != null) {
            Game.entityList.clear();
        }

        if (Game.mp != null) {
            Game.mp.stop();
        }

        Game.loadedLevel = null;

        Game.mainWindow.getTitleLabel().setVisible(false);
        Game.mainWindow.remove(Game.mainView);
        Game.mainWindow.repaint();

        Game.mainWindow.showMainMenu();
    }

    /**
     * Képernyő közepén levő labelen levő szöveg megváltoztatása
     *
     * @param text Szöveg, ami megjelenjen
     */
    private static void setLabelText(String text) {
        Game.mainView.getInteractLabel().setText(text);
        Game.mainView.getInteractLabel().setVisible(true);
    }

    /**
     * Elemek megjelenítésének frissítése,<br>
     * illetve új lépésnél jelzés az entitásoknak
     */
    public static void update() {
        //Game.mainCamera.followPlayer();
        if (Game.mainWindow != null) Game.mainWindow.update();

        if (Game.mainView != null) {
            Game.mainView.update();
            updateTextures();
            updateEntities();
            checkPages();
        }

        System.gc();
    }

    private static void checkPages() {
        if (Game.pagesCollected >= 8) {
            Game.handleGameOver(false);
        }
    }

    /**
     * Textúrák frissítése, átméretezése
     */
    private static void updateTextures() {
        for (Texture tex : Game.texturePool.values()) {
            if (tex.applyViewZoom) {
                tex.resizeToCameraOffset();
            }
        }
    }

    /**
     * Pályán levő entitások frissítése, és jelzés új lépésnél
     */
    private static void updateEntities() {
        for (Entity ent : Game.entityList) {
            ent.update();
        }

        if (Game.newStep && Game.entityList != null) {
            Game.newStep = false;

            for (Entity ent : Game.entityList) {
                ent.newStep();
            }
        }
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
