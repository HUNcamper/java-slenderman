package com.prog1.slenderman.game.entities.prop.house;

import com.prog1.slenderman.game.entities.prop.Prop;
import com.prog1.slenderman.game.resource.Texture;
import com.prog1.slenderman.game.resource.TextureLoader;

import java.util.Arrays;

public class PropHouseWall extends Prop {
    private Texture[] angles;
    private int angle;

    public PropHouseWall() {
        this(0);
    }

    public PropHouseWall(int angle) {
        super();

        this.sizeX = 1;
        this.sizeY = 1;

        this.angles = new Texture[10];
        this.angles[0] = TextureLoader.loadTexture("/textures/house/wall_corner_bottomleft.png");
        this.angles[1] = TextureLoader.loadTexture("/textures/house/wall_corner_bottomright.png");
        this.angles[2] = TextureLoader.loadTexture("/textures/house/wall_corner_topleft.png");
        this.angles[3] = TextureLoader.loadTexture("/textures/house/wall_corner_topright.png");
        this.angles[4] = TextureLoader.loadTexture("/textures/house/wall_ver.png");
        this.angles[5] = TextureLoader.loadTexture("/textures/house/wall_hor.png");
        this.angles[6] = TextureLoader.loadTexture("/textures/house/wall_connect_top.png");
        this.angles[7] = TextureLoader.loadTexture("/textures/house/wall_connect_bottom.png");
        this.angles[8] = TextureLoader.loadTexture("/textures/house/wall_connect_left.png");
        this.angles[9] = TextureLoader.loadTexture("/textures/house/wall_connect_right.png");


        if (angle < 0 || angle > this.angles.length) angle = 0;

        this.angle = angle;
        this.setTexture(this.angles[angle]);

        switch(angle) {
            case 4:
            case 5:
                this.paperSurface = true;
                break;
        }
    }
}
