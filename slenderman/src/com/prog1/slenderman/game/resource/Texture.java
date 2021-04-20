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
    private int size_x = -1;
    private int size_y = -1;

    public Texture(URL imageURL) {
        setTexture(imageURL);
    }

    public Texture(String imagePath) {
        try {
            setTexture(URLHandler.convertString(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
            fallbackTexture();
        }
    }

    public void resize(int size_x, int size_y) {
        this.size_x = size_x;
        this.size_y = size_y;

        Image tmp = this.texture.getScaledInstance(size_x, size_y, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(size_x, size_y, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        this.texture = dimg;
    }

    public void setTexture(URL imageURL) {
        BufferedImage img = null;

        try {
            img = ImageIO.read(imageURL);
            setTexture(img);
        } catch (IOException e) {
            e.printStackTrace();
            fallbackTexture();
        }
    }

    public void setTexture(ImageIcon imageIcon) {
        setTexture(iconToBuffered(imageIcon));
    }

    public void setTexture(BufferedImage img) {
        this.texture = img;
        this.size_x = img.getWidth();
        this.size_y = img.getHeight();
    }

    public ImageIcon getIcon() {
        return bufferedToIcon(this.texture);
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
