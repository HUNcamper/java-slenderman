package com.prog1.slenderman.game.display;

import com.prog1.slenderman.Main;
import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.entities.*;
import com.prog1.slenderman.game.resource.Sound;
import com.prog1.slenderman.game.resource.Texture;
import com.prog1.slenderman.game.resource.TextureLoader;
import com.prog1.slenderman.game.resource.URLHandler;

import javax.imageio.ImageIO;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
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

    public MainWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton b = new JButton("grass :)");//creating instance of JButton
        b.setBounds(130, 100, 150, 40);//x axis, y axis, width, height

        JButton b2 = new JButton("concrete >:(");//creating instance of JButton
        b2.setBounds(130, 150, 150, 40);//x axis, y axis, width, height

        Game.mainView.setBounds(0, 0, this.getWidth(), this.getHeight());
        Game.mainView.setVisible(true);
        Game.mainView.setSize(400, 500);//400 width and 500 height
        Game.mainView.setLayout(null);//using no layout managers

        Game.mainView.add(b, 5, 0);//adding button in JFrame
        Game.mainView.add(b2, 5, 0);//adding button in JFrame

        this.add(Game.mainView);

        b.addActionListener((ActionEvent e) -> {

            try {
                ArrayList<String> sounds = new ArrayList<String>();
                sounds.add("/sound/footsteps/grass1.wav");
                sounds.add("/sound/footsteps/grass2.wav");
                sounds.add("/sound/footsteps/grass3.wav");
                sounds.add("/sound/footsteps/grass4.wav");

                Sound sound = new Sound(sounds);
                sound.play();

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
                ArrayList<String> sounds = new ArrayList<String>();
                sounds.add("/sound/footsteps/concrete1.wav");
                sounds.add("/sound/footsteps/concrete2.wav");
                sounds.add("/sound/footsteps/concrete3.wav");
                sounds.add("/sound/footsteps/concrete4.wav");

                Sound sound = new Sound(sounds);
                sound.play();

                for (int y = 0; y < 15; y++) {
                    for (int x = 0; x < 15; x++) {
                        floorArray[y][x].setTexture(TextureLoader.loadTexture("/textures/stone.png"));
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (Game.mainView.baseZoom-0.2 > 0) Game.mainView.baseZoom -= 0.2;

            Game.update();
        });

        //this.grassTexture = TextureLoader.loadTexture("/textures/grass.png");

        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 15; x++) {
                EntFloorGrass grassFloor = new EntFloorGrass(x, y, 1, 1);
                floorArray[y][x] = grassFloor;

                Game.mainView.add(grassFloor.getLabel(), 0, 0);
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
        return super.getHeight() - this.getInsets().top;
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
    }
}
