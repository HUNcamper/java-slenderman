package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.resource.TextureLoader;

import java.util.List;
import java.util.Random;

public class SlenderMan extends EntityVisible {
    public SlenderMan() {
        this.setTexture(TextureLoader.loadTexture("/textures/slenderman/him.png"));
    }

    public int[] randomPos(List<int[]> list) {
        Random r = new Random();

        int index = r.nextInt(list.size());
        return list.get(index);
    }

    @Override
    public void newStep() {
        List<int[]> possibleLocations = Game.loadedLevel.getManhattanCoordinates(Game.mainPlayer.getCellX(), Game.mainPlayer.getCellY(), 5, true);
        int[] location = randomPos(possibleLocations);

        this.setCellPos(location[0], location[1]);
    }
}
