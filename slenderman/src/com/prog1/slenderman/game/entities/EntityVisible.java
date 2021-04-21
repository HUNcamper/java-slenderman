package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.display.MainCamera;
import com.prog1.slenderman.game.resource.Texture;
import com.prog1.slenderman.game.resource.TextureLoader;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public abstract class EntityVisible extends Entity {
    protected int pos_x = 0;
    protected int pos_y = 0;
    protected int size_x = 32;
    protected int size_y = 32;
    protected JLabel label = new JLabel();
    protected Texture texture;

    public EntityVisible() {
        try {
            this.texture = TextureLoader.loadTexture("dev");
        } catch (Exception ignored) {

        }
    }

    public EntityVisible(URL textureURL) {
        this.texture = TextureLoader.loadTexture(textureURL);
    }

    public EntityVisible(URL textureURL, int pos_x, int pos_y, int size_x, int size_y) {
        this(textureURL);
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.size_x = size_x;
        this.size_y = size_y;
    }

    public Texture getTexture() {
        return texture;
    }

    public JLabel getLabel() {
        return this.label;
    }

    public int getPos_x() {
        return pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
        alignToCameraOffset();
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
        alignToCameraOffset();
    }

    public void alignToCameraOffset() {
        MainCamera camera = Game.mainCamera;
        label.setBounds(pos_x - camera.pos_x, pos_y - camera.pos_y, this.size_x, this.size_y);
    }
}
