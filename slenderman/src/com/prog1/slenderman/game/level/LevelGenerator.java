package com.prog1.slenderman.game.level;

import com.prog1.slenderman.game.entities.SlenderMan;
import com.prog1.slenderman.game.entities.SlenderManOverlay;
import com.prog1.slenderman.game.entities.prop.PropCarHor;
import com.prog1.slenderman.game.entities.prop.PropCarVer;
import com.prog1.slenderman.game.entities.prop.PropRock;
import com.prog1.slenderman.game.entities.prop.PropTreeSmall;

public abstract class LevelGenerator {
    public static Level fromFile() {
        return new Level(5, 15, 15);
    }

    public static Level random() {
        Level level = new Level(5, 15, 15);

        level.spawnEntity(new PropTreeSmall(), 2, 6, 6);
        level.spawnEntity(new PropRock(), 2, 2, 2);
        level.spawnEntity(new PropCarHor(), 2, 0, 8);
        level.spawnEntity(new PropCarVer(), 2, 10, 10);
        level.spawnEntity(new SlenderMan(), 3, 5, 5);

        return level;
    }
}
