package com.prog1.slenderman.game.entities.prop.house;

import com.prog1.slenderman.game.entities.prop.Prop;
import com.prog1.slenderman.game.resource.Texture;
import com.prog1.slenderman.game.resource.TextureLoader;

/**
 * Házon belül levő falat leíró osztály
 */
public class PropHouseWall extends Prop {
    private Texture[] textures;
    private int texIndex;

    public PropHouseWall() {
        this(0);
    }

    /**
     * Fal és textúrák inicializálása
     * @param texIndex Textúra indexe
     */
    public PropHouseWall(int texIndex) {
        super();

        this.sizeX = 1;
        this.sizeY = 1;

        this.textures = new Texture[10];
        this.textures[0] = TextureLoader.loadTexture("/textures/house/wall_corner_bottomleft.png");
        this.textures[1] = TextureLoader.loadTexture("/textures/house/wall_corner_bottomright.png");
        this.textures[2] = TextureLoader.loadTexture("/textures/house/wall_corner_topleft.png");
        this.textures[3] = TextureLoader.loadTexture("/textures/house/wall_corner_topright.png");
        this.textures[4] = TextureLoader.loadTexture("/textures/house/wall_ver.png");
        this.textures[5] = TextureLoader.loadTexture("/textures/house/wall_hor.png");
        this.textures[6] = TextureLoader.loadTexture("/textures/house/wall_connect_top.png");
        this.textures[7] = TextureLoader.loadTexture("/textures/house/wall_connect_bottom.png");
        this.textures[8] = TextureLoader.loadTexture("/textures/house/wall_connect_left.png");
        this.textures[9] = TextureLoader.loadTexture("/textures/house/wall_connect_right.png");


        if (texIndex < 0 || texIndex > this.textures.length) texIndex = 0;

        this.texIndex = texIndex;
        this.setTexture(this.textures[texIndex]);

        switch(texIndex) {
            case 4:
            case 5:
                this.paperSurface = true;
                break;
        }
    }
}
