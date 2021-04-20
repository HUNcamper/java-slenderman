package com.prog1.slenderman.game.resource;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.Buffer;

public class Texture {
    private BufferedImage texture;
    private int size_x;
    private int size_y;

    public Texture(URL imageURL, int size_x, int size_y) {
        this.size_x = size_x;
        this.size_y = size_y;

        setTexture(imageURL);
    }

    public Texture(String imagePath, int size_x, int size_y) {
        this.size_x = size_x;
        this.size_y = size_y;

        try {
            setTexture(URLHandler.convertString(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
            fallbackTexture();
        }
    }

    private void setTexture(URL imageURL) {
        BufferedImage img = null;

        try {
            img = ImageIO.read(imageURL);
            this.texture = img;
        } catch (IOException e) {
            e.printStackTrace();
            fallbackTexture();
        }
    }

    private void setTexture(ImageIcon imageIcon) {
        this.texture = Texture.iconToBuffered(imageIcon);
    }

    private void setTexture(BufferedImage buffered) {
        this.texture = buffered;
    }

    private void fallbackTexture() {
        // Error texture
        BufferedImage image = new BufferedImage(this.size_x, this.size_y, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        graphics.setPaint(new Color(194, 0, 0));
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

        setTexture(image);
    }

    private static BufferedImage iconToBuffered(ImageIcon icon) {
        BufferedImage bi = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        // paint the Icon to the BufferedImage.
        icon.paintIcon(null, g, 0, 0);
        g.dispose();

        return bi;
    }

    private static ImageIcon bufferedToIcon(BufferedImage buffered) {
        return new ImageIcon(buffered);
    }
}
