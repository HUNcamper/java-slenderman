package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.level.Level;
import com.prog1.slenderman.game.resource.Sound;
import com.prog1.slenderman.game.resource.SoundLoader;
import com.prog1.slenderman.game.resource.Texture;
import com.prog1.slenderman.game.resource.TextureLoader;

import java.util.List;
import java.util.Random;

public class SlenderMan extends EntityVisible {
    private Sound[] nearbySounds;
    private Sound pianoSound;

    public SlenderMan() {
        this.setTexture(TextureLoader.loadTexture("/textures/slenderman/him.png"));
        this.nearbySounds = new Sound[2];
        this.nearbySounds[0] = SoundLoader.loadSound("/sound/slender/staticlight.wav");
        this.nearbySounds[1] = SoundLoader.loadSound("/sound/slender/staticmedium.wav");

        this.nearbySounds[0].setLoop(true);
        this.nearbySounds[1].setLoop(true);

        this.pianoSound = SoundLoader.loadSound("/sound/slender/piano.wav");
    }

    public int[] randomPos(List<int[]> list) {
        Random r = new Random();

        int index = r.nextInt(list.size());
        return list.get(index);
    }

    @Override
    public void newStep() {
        for (Sound sound : nearbySounds) {
            sound.stop();
        }

        int playerX = Game.mainPlayer.getCellX();
        int playerY = Game.mainPlayer.getCellY();

        List<int[]> possibleLocations = Game.loadedLevel.getManhattanCoordinates(playerX, playerY, 5, true);
        int[] location = randomPos(possibleLocations);

        this.setCellPos(location[0], location[1]);

        int playerDistance = Level.manhattanDistance(playerX, playerY, this.cellX, this.cellY);

        this.setVisible(true);

        switch(playerDistance) {
            case 3:
            case 2:
                this.nearbySounds[0].play();
                Game.slenderOverlay.texture.setOpacity(0.1f);
                Game.slenderOverlay.setVisible(true);
                Game.update();
                break;
            case 1:
                this.nearbySounds[1].play();
                Game.slenderOverlay.texture.setOpacity(0.2f);
                Game.slenderOverlay.setVisible(true);
                Game.update();
                break;
            case 0:
                this.nearbySounds[1].play();
                this.pianoSound.play();
                Game.slenderOverlay.texture.setOpacity(0.8f);
                Game.slenderOverlay.setVisible(true);
                Game.update();
                break;
            default:
                Game.slenderOverlay.setVisible(false);
                this.setVisible(false);
                break;
        }
    }
}
