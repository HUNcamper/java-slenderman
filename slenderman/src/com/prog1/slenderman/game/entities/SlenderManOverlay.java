package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.resource.Texture;
import com.prog1.slenderman.game.resource.TextureLoader;

/**
 * Slenderman overlay<br>
 *     Egy legfelső rétegben levő látható entitás, ami csak akkor jelenik meg, mikor a Slenderman a közelben van
 */
public class SlenderManOverlay extends EntityVisible {
    /**
     * Slenderman overlay inicializálása
     */
    public SlenderManOverlay() {
        this.sizeX = Game.loadedLevel.getColumns();
        this.sizeY = Game.loadedLevel.getRows();

        Texture texture = TextureLoader.loadTexture("/textures/slenderman/overlay_static.png");
        texture.setOpacity(0.3f);
        this.setTexture(texture);

        this.setVisible(false);
    }
}
