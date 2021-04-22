package com.prog1.slenderman.game.display;

import com.prog1.slenderman.game.Game;

public class MainCamera {
    public int pos_x = 0;
    public int pos_y = 0;

    public MainCamera() {

    }

    public MainCamera(int x, int y) {
        this.pos_x = x;
        this.pos_y = y;
    }

    public void move(int new_x, int new_y) {
        this.pos_x = new_x;
        this.pos_y = new_y;
    }

    public void offset(int offset_x, int offset_y) {
        this.pos_x += offset_x;
        this.pos_y += offset_y;
    }

    public void followPlayer() {
        int new_x = (int) (Game.mainPlayer.getCellX() * Game.gridSize - (Game.mainView.baseResolution_width / Game.mainView.baseZoom)/2);
        int new_y = (int) (Game.mainPlayer.getCellY() * Game.gridSize - (Game.mainView.baseResolution_height / Game.mainView.baseZoom)/2);

        move(new_x, new_y);
    }
}
