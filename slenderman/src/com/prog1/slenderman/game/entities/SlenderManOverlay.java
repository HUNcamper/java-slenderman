package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.resource.Texture;
import com.prog1.slenderman.game.resource.TextureLoader;

public class SlenderManOverlay extends EntityVisible {
    public SlenderManOverlay() {
        this.sizeX = Game.loadedLevel.getColumns();
        this.sizeY = Game.loadedLevel.getRows();

        Texture texture = TextureLoader.loadTexture("/textures/slenderman/close_shot.png");
        texture.setOpacity(0.3f);
        this.setTexture(texture);

        this.setVisible(false);
    }
}
