package com.prog1.slenderman.game.entities;

import com.prog1.slenderman.game.resource.TextureLoader;

public class SlenderMan extends EntityVisible {
    public SlenderMan() {
        this.setTexture(TextureLoader.loadTexture("/textures/slenderman/him.png"));
    }

    @Override
    public void newStep() {
        
    }
}
