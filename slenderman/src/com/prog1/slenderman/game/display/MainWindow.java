package com.prog1.slenderman.game.display;

import com.prog1.slenderman.Main;
import com.prog1.slenderman.game.resource.Sound;
import com.prog1.slenderman.game.resource.Texture;
import com.prog1.slenderman.game.resource.URLHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainWindow extends JFrame {
    public MainWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton b = new JButton("grass :)");//creating instance of JButton
        b.setBounds(130, 100, 150, 40);//x axis, y axis, width, height

        JButton b2 = new JButton("concrete >:(");//creating instance of JButton
        b2.setBounds(130, 150, 150, 40);//x axis, y axis, width, height

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, this.getWidth(), this.getHeight());
        panel.setVisible(true);
        panel.setSize(400, 500);//400 width and 500 height
        panel.setLayout(null);//using no layout managers

        panel.add(b);//adding button in JFrame
        panel.add(b2);//adding button in JFrame

        this.add(panel);

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                componentEvent.getComponent();
            }
        });

        b.addActionListener((ActionEvent e) -> {

            try {
                ArrayList<String> sounds = new ArrayList<String>();
                sounds.add("/sound/footsteps/grass1.wav");
                sounds.add("/sound/footsteps/grass2.wav");
                sounds.add("/sound/footsteps/grass3.wav");
                sounds.add("/sound/footsteps/grass4.wav");

                Sound sound = new Sound(sounds);
                sound.play();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {

                JLabel label = new JLabel();
                label.setBounds(x * 50, y * 50, 50, 50);

                Texture grassTexture = new Texture("/textures/grass.png");

                grassTexture.resize(label.getWidth(), label.getHeight());

                label.setIcon(grassTexture.getIcon());

                panel.add(label);
            }
        }

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
}
