package com.prog1.slenderman.game.resource;

import com.prog1.slenderman.game.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Textúrát kezelő osztály
 */
public class Texture {
    public static Texture fallbackTexture = new Texture();

    private BufferedImage bufferedImage;
    private BufferedImage originalBufferedImage;
    private ImageIcon icon = null;
    private int sizeX = 1;
    private int sizeY = 1;
    private float opacity = 1.0f;

    public boolean applyViewZoom = true;

    /**
     * Textúra méretének beállítása<br>
     *     Ez a méret cellaméret, nem térbeli hosszúság/magasság.
     * @param sizeX X cellaméret
     * @param sizeY Y cellaméret
     */
    public void setSize(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    /**
     * Textúra cella hosszúságának lekérdezése
     * @return Cella hosszúság
     */
    public int getWidth() {
        return sizeX;
    }

    /**
     * Textúra cella magasságának lekérdezése
     * @return Cella magasság
     */
    public int getHeight() {
        return sizeY;
    }

    /**
     * Buffered image lekérdezése a textúrából
     * @return Textúra BufferedImage adattagja
     */
    public BufferedImage getBufferedImage() {
        return this.originalBufferedImage;
    }

    /**
     * Textúra inicializálása MISSING textúrával
     */
    public Texture() {
        fallbackTexture();
    }

    /**
     * Textúra inicializálása URL alapján
     * @param imageURL Textúra elérési URL-je
     */
    public Texture(URL imageURL) {
        setTexture(imageURL);
    }

    /**
     * Textúra inicializálása elérési útvonal alapján
     * @param imagePath Textúra elérési útvonala
     */
    public Texture(String imagePath) {
        try {
            setTexture(URLHandler.convertString(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
            fallbackTexture();
        }
    }

    /**
     * Textúra inicializálása BufferedImage alapján
     * @param bufferedImage BufferedImage
     */
    public Texture(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
        this.originalBufferedImage = bufferedImage;
    }

    /**
     * Áttettszőség beállítása
     * @param amount Áttettszőség mértéke 0.0 és 1.0 között (0: láthatatlan, 1: teljesen látható)
     */
    public void setOpacity(float amount) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        resize(width, height, amount);
    }

    /**
     * Áttettszőség lekérdezése
     * @return Áttettszőség 0.0 és 1.0 között
     */
    public float getOpacity() {
        return this.opacity;
    }

    /**
     * Textúra átméretezése, és áttettszőség módosítása
     * @param width Szélesség
     * @param height Magasság
     * @param opacity Áttettszőség mértéke 0.0 és 1.0 között (0: láthatatlan, 1: teljesen látható)
     */
    public void resize(int width, int height, float opacity) {
        if (width == bufferedImage.getWidth() && height == bufferedImage.getHeight() && this.opacity == opacity)
            return; // Ha az értékek megegyeznek, ne legyen változás

        this.opacity = opacity;

        this.icon = null; // resized, need new icon

        Image tmp = this.originalBufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.opacity));
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        this.bufferedImage = dimg;
    }

    /**
     * View zoom-ra átméretezés<br>
     *     View zoom akkor történhet, mikor átméretezzük az ablakot
     */
    public void resizeToCameraOffset() {
        int width = (int) Math.floor(Game.gridSize * this.sizeX * Game.mainView.zoom);
        int height = (int) Math.floor(Game.gridSize * this.sizeY * Game.mainView.zoom);

        resize(width, height, this.opacity);
    }

    /**
     * Textúra átállítása URL alapján
     * @param imageURL Textúra URL útvonala
     */
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

    /**
     * Textúra átállítása ImageIcon alapján
     * @param imageIcon Swing ImageIcon
     */
    public void setTexture(ImageIcon imageIcon) {
        setTexture(iconToBuffered(imageIcon));
    }

    /**
     * Textúra átállítása BufferedImage alapján
     * @param img Swing BufferedImage
     */
    public void setTexture(BufferedImage img) {
        this.bufferedImage = img;
        this.originalBufferedImage = img;
        //this.sizeX = img.getWidth();
        //this.sizeY = img.getHeight();
    }

    /**
     * Textúra ImageIcon formátumú lekérdezése
     * @return Swing ImageIcon
     */
    public ImageIcon getIcon() {
        if (this.icon == null) {
            this.icon = bufferedToIcon(this.bufferedImage);
        }

        return this.icon;
    }

    /**
     * MISSING textúra, akkor használatos, mikor egy adott textúra elérési útvonala hibás, vagy a fájl nem található.
     */
    private void fallbackTexture() {
        // Error texture
        BufferedImage image = new BufferedImage(this.sizeX * Game.gridSize, this.sizeY * Game.gridSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        graphics.setPaint(new Color(194, 0, 0));
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
        graphics.setPaint(new Color(255, 255, 255));
        graphics.drawString("MISSING", 0, 10);

        setTexture(image);
    }

    /**
     * ImageIcon átkonvertálása BufferedImage formátumba
     * @param icon Swing ImageIcon
     * @return Swing BufferedImage
     */
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

    /**
     * BufferedImage átkonvertálása ImageIcon formátumba
     * @param buffered Swing BufferedImage
     * @return Swing ImageIcon
     */
    private static ImageIcon bufferedToIcon(BufferedImage buffered) {
        return new ImageIcon(buffered);
    }
}
