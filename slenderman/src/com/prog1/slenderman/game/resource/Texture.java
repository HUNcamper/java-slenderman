package com.prog1.slenderman.game.resource;

import com.prog1.slenderman.game.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Texture {
    public static Texture fallbackTexture = new Texture();

    private BufferedImage bufferedImage;
    private BufferedImage originalBufferedImage;
    private ImageIcon icon = null;
    private int sizeX = 1;
    private int sizeY = 1;
    private float opacity = 1.0f;

    public boolean applyViewZoom = true;

    public void setSize(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public int getWidth() {
        return sizeX;
    }

    public int getHeight() {
        return sizeY;
    }

    public BufferedImage getBufferedImage() {
        return this.originalBufferedImage;
    }

    public Texture() {
        fallbackTexture();
    }

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

    public Texture(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
        this.originalBufferedImage = bufferedImage;
    }

    public void setOpacity(float amount) {
        if (amount == this.opacity) return;
        this.opacity = amount;

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        resize(width, height);
    }

    public void resize(int width, int height) {
        if (width == bufferedImage.getWidth() && height == bufferedImage.getHeight() && this.opacity == 1.0f)
            return; // Ha az értékek megegyeznek, ne legyen változás

        this.icon = null; // resized, need new icon

        Image tmp = this.originalBufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.opacity));
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        this.bufferedImage = dimg;
    }

    public void resizeToCameraOffset() {
        int width = (int) Math.floor(Game.gridSize * this.sizeX * Game.mainView.zoom);
        int height = (int) Math.floor(Game.gridSize * this.sizeY * Game.mainView.zoom);

        resize(width, height);
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
        this.bufferedImage = img;
        this.originalBufferedImage = img;
        //this.sizeX = img.getWidth();
        //this.sizeY = img.getHeight();
    }

    public ImageIcon getIcon() {
        if (this.icon == null) {
            this.icon = bufferedToIcon(this.bufferedImage);
        }

        return this.icon;
    }

    private void fallbackTexture() {
        // Error texture
        BufferedImage image = new BufferedImage(this.sizeX * Game.gridSize, this.sizeY * Game.gridSize, BufferedImage.TYPE_INT_RGB);
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
