package com.prog1.slenderman.game.display;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.entities.*;
import com.prog1.slenderman.game.resource.*;

import javax.swing.*;
import java.awt.*;

/**
 * A fő ablak, amiben a játék fut.
 */
public class MainWindow extends JFrame {
    private JLabel titleLabel;

    /**
     * Fő ablak inicializálása
     */
    public MainWindow() {
        setupWindow();

        setupTitleLabel();
    }

    /**
     * A játék panel beállítása
     */
    public void setupMainView(MainView view) {
        view.setBounds(0, 0, this.getWidth(), this.getHeight());
        view.setVisible(true);
        view.setSize(1280, 720);
        view.setLayout(null); //using no layout managers

        this.add(view);
    }

    /**
     * A GUI ablak beállítása
     */
    private void setupWindow() {
        this.getContentPane().setBackground(Color.BLACK);
        this.setSize(1280, 720);

        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle( JRootPane. FRAME );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(500, 500); //500 width and 500 height
        this.setLayout(null); //using no layout managers
        this.setVisible(true); //making the frame visible
    }

    /**
     * Fő cím label beállítása, amely a papírok számát jelzi
     */
    private void setupTitleLabel() {
        this.titleLabel = new JLabel("PAGES: ", SwingConstants.CENTER);
        this.titleLabel.setBounds(0, 0, 0, 32);
        this.titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 32));
        this.titleLabel.setForeground(Color.WHITE);
        this.add(this.titleLabel);
    }

    /**
     * Ablak újrarajzolás, labelek frissítése
     */
    public void update() {
        for (Texture texture : Game.texturePool.values()) {
            if (texture.applyViewZoom) {
                texture.resizeToCameraOffset();
            }
        }

        for (Entity ent : Game.entityList) {
            if (ent instanceof EntityVisible) {
                ((EntityVisible) ent).alignToCameraOffset();
            }
        }
        this.titleLabel.setText("PAGES: " + Game.pagesCollected);
        this.titleLabel.setBounds(0, 0, Game.mainWindow.getWidth(), this.titleLabel.getHeight());
    }
}
