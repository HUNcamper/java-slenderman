package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.display.MainCamera;
import com.prog1.slenderman.game.display.MainView;
import com.prog1.slenderman.game.resource.Texture;
import com.prog1.slenderman.game.resource.TextureLoader;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public abstract class EntityVisible extends Entity {
    protected int pos_x = 0;
    protected int pos_y = 0;
    protected int size_x = 50;
    protected int size_y = 50;
    protected JLabel label = new JLabel();
    protected Texture texture = Texture.fallbackTexture;

    public EntityVisible() {
        super();
    }

    public EntityVisible( int pos_x, int pos_y, int size_x, int size_y) {
        this();
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.size_x = size_x;
        this.size_y = size_y;

        System.out.println("Spawned entity at x" + pos_x + " y" + pos_y);

        this.alignToCameraOffset();
    }

    public void setTexture(URL textureURL) {
        this.texture.setTexture(textureURL);
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
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
        MainView view = Game.mainView;
        MainCamera camera = Game.mainCamera;

        //int new_x = (int) (this.pos_x * 50 * view.zoom);
        //int new_y = (int) (this.pos_y * 50 * view.zoom);
        int new_x = (int) Math.floor(this.pos_x * view.zoom - camera.pos_x * view.zoom);
        int new_y = (int) Math.floor(this.pos_y * view.zoom - camera.pos_y * view.zoom);

        int new_width = (int) Math.floor(this.size_x * view.zoom);
        int new_height = (int) Math.floor(this.size_y * view.zoom);

        this.label.setBounds(new_x, new_y, new_width, new_height);

        label.setIcon(this.texture.getIcon());
    }

    public void alignTexture() {
        MainView view = Game.mainView;

        int new_width = (int) Math.floor(this.size_x * view.zoom);
        int new_height = (int) Math.floor(this.size_y * view.zoom);

        this.label.setBounds(label.getX(), label.getY(), new_width, new_height);
        this.texture.resize(new_width, new_height);

        label.setIcon(this.texture.getIcon());
    }
}
