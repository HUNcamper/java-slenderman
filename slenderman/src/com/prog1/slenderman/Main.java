package com.prog1.slenderman;

import com.prog1.slenderman.game.resource.Sound;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        JFrame f=new JFrame();//creating instance of JFrame

        JButton b=new JButton("grass :)");//creating instance of JButton
        b.setBounds(130,100,150, 40);//x axis, y axis, width, height

        JButton b2=new JButton("concrete >:(");//creating instance of JButton
        b2.setBounds(130,150,150, 40);//x axis, y axis, width, height

        f.add(b);//adding button in JFrame
        f.add(b2);//adding button in JFrame

        b.addActionListener((ActionEvent e) -> {
            System.out.println("Playing sound");

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
            System.out.println("Playing sound");

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
                URL imageURL = Main.class.getResource("/textures/grass.png");

                BufferedImage img = null;
                try {
                    img = ImageIO.read(imageURL);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                JLabel label = new JLabel(new ImageIcon(imageURL));

                label.setBounds(x * 50, y * 50, 50, 50);

                Image dimg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);

                label.setIcon(new ImageIcon(dimg));

                f.add(label);
            }
        }

        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

        /*Game game = new Game();

        while(game.gameLoop()) {

            DataInputStream dis = new DataInputStream(System.in) ;

            while(dis.available() == 0) {}

            System.out.println("new loop");
        }*/
    }
}
