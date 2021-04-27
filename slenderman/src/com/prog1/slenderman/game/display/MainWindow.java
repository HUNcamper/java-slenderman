package com.prog1.slenderman.game.display;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.entities.*;
import com.prog1.slenderman.game.entities.floor.EntFloor;
import com.prog1.slenderman.game.entities.floor.EntFloorGrass;
import com.prog1.slenderman.game.resource.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {
    private JLabel titleLabel;
    private JLabel interactLabel;

    public MainWindow() {
        setupWindow();

        setupMainView();
        setupInteractLabel();
        setupTitleLabel();
    }

    private void setupMainView() {
        Game.mainView.setBounds(0, 0, this.getWidth(), this.getHeight());
        Game.mainView.setVisible(true);
        Game.mainView.setSize(1280, 720);
        Game.mainView.setLayout(null); //using no layout managers

        this.add(Game.mainView);
    }

    private void setupWindow() {
        this.getContentPane().setBackground(Color.BLACK);
        this.setSize(1280, 720);

        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle( JRootPane. FRAME );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(400, 500);//400 width and 500 height
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible
    }

    private void setupInteractLabel() {
        this.interactLabel = new JLabel("Press 'F' to pick up", SwingConstants.CENTER);
        this.interactLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 32));
        this.interactLabel.setForeground(Color.WHITE);
        Game.mainView.add(this.interactLabel, 4, 0);
    }

    private void setupTitleLabel() {
        this.titleLabel = new JLabel("PAGES: ", SwingConstants.CENTER);
        this.titleLabel.setBounds(0, 0, 0, 32);
        this.titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 32));
        this.titleLabel.setForeground(Color.WHITE);
        this.add(this.titleLabel);
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }

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

        if (Game.mainPlayer.canInteract()) {
            this.interactLabel.setVisible(true);
            this.interactLabel.setBounds(0, 0, Game.mainView.getWidth(), Game.mainView.getHeight());
        } else if (this.interactLabel.isVisible()) {
            this.interactLabel.setVisible(false);
        }
    }
}
