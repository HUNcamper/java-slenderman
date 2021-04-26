package com.prog1.slenderman.game.display;

import com.prog1.slenderman.game.Game;

/**
 * Mozgatható kamerát leíró osztály.<br>
 *     A feladat megkötései miatt nem teljesen használt.
 */
public class MainCamera {
    public int posX = 0;
    public int posY = 0;

    /**
     * Inicializálás
     */
    public MainCamera() {

    }

    /**
     * Inicializálás X és Y koordináták alapján.<br>
     *     A kamera ennyivel lesz eltolva a 0,0 koordinátához képest
     * @param x X koordináta
     * @param y Y koordináta
     */
    public MainCamera(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    /**
     * Kamera mozgatása új X és Y koordinátákra
     * @param newX Új X koordináta
     * @param newY Új Y koordináta
     */
    public void move(int newX, int newY) {
        this.posX = newX;
        this.posY = newY;
    }

    /**
     * Kamera relatív mozgatása X és Y értékekkel
     * @param offsetX Relatív X érték
     * @param offsetY Relatív Y érték
     */
    public void offset(int offsetX, int offsetY) {
        this.posX += offsetX;
        this.posY += offsetY;
    }

    /**
     * Játékos követése úgy, hogy a játékos maga a kép középpontján legyen.
     */
    public void followPlayer() {
        int newX = (int) (Game.mainPlayer.getCellX() * Game.gridSize - (Game.mainView.baseResolutionWidth / Game.mainView.baseZoom)/2);
        int newY = (int) (Game.mainPlayer.getCellY() * Game.gridSize - (Game.mainView.baseResolution_height / Game.mainView.baseZoom)/2);

        move(newX, newY);
    }
}
