package com.prog1.slenderman.game.display;

import com.prog1.slenderman.Main;
import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.entities.*;
import com.prog1.slenderman.game.resource.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {
    //private JLabel[][] labelArray = new JLabel[15][15];
    private EntFloor[][] floorArray = new EntFloor[15][15];
    private Texture grassTexture;

    private JLabel titleLabel;

    public MainWindow() {
        this.getContentPane().setBackground(Color.BLACK);

        this.setSize(1280, 720);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton b = new JButton("grass :)");//creating instance of JButton
        b.setBounds(130, 100, 150, 40);//x axis, y axis, width, height

        JButton b2 = new JButton("concrete >:(");//creating instance of JButton
        b2.setBounds(130, 150, 150, 40);//x axis, y axis, width, height

        Game.mainView.setBounds(0, 0, this.getWidth(), this.getHeight());
        Game.mainView.setVisible(true);
        Game.mainView.setSize(1280, 720);
        Game.mainView.setLayout(null); //using no layout managers

        Game.mainView.add(b, 5, 0);//adding button in JFrame
        Game.mainView.add(b2, 5, 0);//adding button in JFrame

        this.add(Game.mainView);

        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle( JRootPane. FRAME );

        this.titleLabel = new JLabel("SLENDERMAN", SwingConstants.CENTER);
        this.titleLabel.setBounds(0, 0, 1000, 32);

        this.titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 32));

        this.add(this.titleLabel);

        b.addActionListener((ActionEvent e) -> {

            try {
                for (int y = 0; y < 15; y++) {
                    for (int x = 0; x < 15; x++) {
                        floorArray[y][x].setTexture(TextureLoader.loadTexture("/textures/grass.png"));
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Game.mainView.baseZoom += 0.2;

            Game.update();
        });

        b2.addActionListener((ActionEvent e) -> {

            try {
                for (int y = 0; y < 15; y++) {
                    for (int x = 0; x < 15; x++) {
                        floorArray[y][x].setTexture(TextureLoader.loadTexture("/textures/stone.png"));
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (Game.mainView.baseZoom - 0.2 > 0) Game.mainView.baseZoom -= 0.2;

            Game.update();
        });

        //this.grassTexture = TextureLoader.loadTexture("/textures/grass.png");

        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 15; x++) {
                EntFloorGrass grassFloor = new EntFloorGrass(x, y, 1, 1);
                floorArray[y][x] = grassFloor;

                //Game.mainView.add(grassFloor.getLabel(), 0, 0);

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

        this.titleLabel.setBounds(CenterFactory.CenterHorizontal(this.titleLabel), this.titleLabel.getY(), this.titleLabel.getWidth(), this.titleLabel.getHeight());
    }
}
