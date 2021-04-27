package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.level.Level;
import com.prog1.slenderman.game.resource.Sound;
import com.prog1.slenderman.game.resource.SoundLoader;
import com.prog1.slenderman.game.resource.TextureLoader;

import java.util.List;
import java.util.Random;

/**
 * Slendermant leíró osztály
 */
public class SlenderMan extends EntityVisible {
    private Sound[] nearbySounds;
    private Sound pianoSound;
    private int status = 0;
    private boolean jumpscareAlreadyHappening = false;

    /**
     * Slenderman inicializálása textúrákkal és hangeffektusokkal
     */
    public SlenderMan() {
        this.setTexture(TextureLoader.loadTexture("/textures/slenderman/him.png"));
        this.nearbySounds = new Sound[3];
        this.nearbySounds[0] = SoundLoader.loadSound("/sound/slender/staticlight.wav");
        this.nearbySounds[1] = SoundLoader.loadSound("/sound/slender/staticmedium.wav");
        this.nearbySounds[2] = SoundLoader.loadSound("/sound/slender/staticheavy.wav");

        this.nearbySounds[0].setLoop(true);
        this.nearbySounds[1].setLoop(true);
        this.nearbySounds[2].setLoop(true);

        this.pianoSound = SoundLoader.loadSound("/sound/slender/piano.wav");

        this.setVisible(false);
    }

    /**
     * Koordináta páros listából random páros kiválasztása
     * @param list Koordináta párok listája
     * @return Egy random koordináta páros
     */
    public int[] randomPos(List<int[]> list) {
        Random r = new Random();

        int index = r.nextInt(list.size());
        return list.get(index);
    }

    /**
     * Státusz frissítése
     */
    public void updateStatus() {
        switch (Game.pagesCollected) {
            case 0:
            case 8:
                this.status = 0;
                break;
            case 1:
                this.status = 1;
                break;
            case 2:
            case 3:
                this.status = 2;
                break;
            case 4:
            case 5:
                this.status = 3;
                break;
            case 6:
            case 7:
                this.status = 4;
                break;
        }
    }

    /**
     * Slenderman overlay megjelenítése és hozzá kötött hang lejátszása
     * @param opacity Áttetszőség mértéke 0.0 és 1.0 között
     * @param soundIndex Hang indexe
     * @param jumpscare Igaz, ha ez egy jumpscare, Hamis ha nem
     */
    public void overlayAppear(float opacity, int soundIndex, boolean jumpscare) {
        float cOpacity = Game.slenderOverlay.texture.getOpacity();

        System.out.println("cOpacity: " + cOpacity + " new opacity: " + opacity);

        if (this.jumpscareAlreadyHappening && cOpacity == opacity) return;

        this.nearbySounds[soundIndex].play();
        if (jumpscare) this.pianoSound.play();

        Game.slenderOverlay.texture.setOpacity(opacity);
        System.out.println("Supposedly set new opacity. Let's see: " + Game.slenderOverlay.texture.getOpacity());
        Game.slenderOverlay.setVisible(true);
        jumpscareAlreadyHappening = true;
        Game.update();
    }

    /**
     * Slenderman mozgatása a játékos köré a jelenlegi státusz alapján
     */
    public void moveSlender() {
        int playerX = Game.mainPlayer.getCellX();
        int playerY = Game.mainPlayer.getCellY();

        if (this.cellX == playerX && this.cellY == playerY) {
            gameOver();
            return;
        }

        int distance = 5;
        boolean maximum = false;

        if (this.status > 1) {
            maximum = true;
        }
        if (this.status == 3) {
            distance = 4;
        } else if (this.status == 4) {
            distance = 3;
        }

        List<int[]> possibleLocations = Game.loadedLevel.getManhattanCoordinates(playerX, playerY, distance, maximum);
        int[] location = randomPos(possibleLocations);

        this.setCellPos(location[0], location[1]);
    }

    /**
     * Játékos és slenderman közötti távolság alapján az overlay megjelenítése
     */
    public void handleDistance() {
        int playerX = Game.mainPlayer.getCellX();
        int playerY = Game.mainPlayer.getCellY();

        int playerDistance = Level.manhattanDistance(playerX, playerY, this.cellX, this.cellY);

        this.setVisible(true);

        switch (playerDistance) {
            case 3:
            case 2:
                overlayAppear(0.1f, 0, false);
                break;
            case 1:
                overlayAppear(0.2f, 1, false);
                break;
            case 0:
                break;
            default:
                // Túl messze van, ne mutassa
                Game.slenderOverlay.setVisible(false);
                this.setVisible(false);
                break;
        }
    }

    /**
     * Jumpscare megkísérlése<br>
     *     Akkor nem sikerül, ha slenderman nem a player előtt van, vagy már volt jumpscare meghívva egy lépéssel ezelőtt
     */
    public void tryJumpscare() {
        // Ha player előtt van, akkor legyen jumpscare
        Player.Direction dir = Game.mainPlayer.getFacing();
        int xDiff = this.cellX - Game.mainPlayer.getCellX();
        int yDiff = this.cellY - Game.mainPlayer.getCellY();

        boolean ok = false;

        if (dir == Player.Direction.UP && xDiff == 0 && yDiff == -1) {
            ok = true;
        } else if (dir == Player.Direction.LEFT && xDiff == -1 && yDiff == 0) {
            ok = true;
        } else if (dir == Player.Direction.DOWN && xDiff == 0 && yDiff == 1) {
            ok = true;
        } else if (dir == Player.Direction.RIGHT && xDiff == 1 && yDiff == 0) {
            ok = true;
        }

        if (ok) {
            overlayAppear(0.8f, 2, true);
        }
    }

    /**
     * Minden hang megállítása
     */
    public void stopSounds() {

        for (Sound sound : nearbySounds) {
            sound.stop();
        }
    }

    /**
     * Játék vége
     */
    private void gameOver() {
        overlayAppear(1.0f, 2, false);
        Game.gameOver = true;
    }

    /**
     * Játékos új lépése után kezeljük Slenderman lépését
     */
    @Override
    public void newStep() {
        updateStatus();
        if (this.status == 0) return;
        this.jumpscareAlreadyHappening = false;

        stopSounds();
        moveSlender();
        handleDistance();
        tryJumpscare();
    }
}
