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
        this.getContentPane().setBackground(Color.BLACK);

        this.setSize(1280, 720);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Game.mainView.setBounds(0, 0, this.getWidth(), this.getHeight());
        Game.mainView.setVisible(true);
        Game.mainView.setSize(1280, 720);
        Game.mainView.setLayout(null); //using no layout managers

        this.add(Game.mainView);

        this.interactLabel = new JLabel("Press 'F' to pick up", SwingConstants.CENTER);
        this.interactLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 32));
        this.interactLabel.setForeground(Color.WHITE);
        Game.mainView.add(this.interactLabel, 4, 0);
        //this.interactLabel.setBounds(CenterFactory.CenterHorizontal(this.interactLabel, Game.mainView.getWidth()), CenterFactory.CenterVertical(this.interactLabel, Game.mainView.getHeight()), 100, 100 );

        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle( JRootPane. FRAME );

        this.titleLabel = new JLabel("PAGES: ", SwingConstants.CENTER);
        this.titleLabel.setBounds(0, 0, 0, 32);
        this.titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 32));
        this.titleLabel.setForeground(Color.WHITE);

        this.add(this.titleLabel);

        //this.grassTexture = TextureLoader.loadTexture("/textures/grass.png");

        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 15; x++) {
                EntFloorGrass grassFloor = new EntFloorGrass(x, y, 1, 1);

                Game.loadedLevel.spawnEntity(grassFloor, 0, x, y);
            }
        }

        System.out.println("Texture count: " + Game.texturePool.values().size());

        this.setSize(400, 500);//400 width and 500 height
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible

        try {
            Sound ambient = new Sound("/sound/ambient/frogs_loop1.wav");
            ambient.setLoop(true);
            ambient.setVolume(0.1f);
            ambient.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        this.interactLabel.setBounds(0, 0, Game.mainView.getWidth(), Game.mainView.getHeight());
    }
}
