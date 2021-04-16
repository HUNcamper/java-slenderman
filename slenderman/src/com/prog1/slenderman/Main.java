package com.prog1.slenderman;

import com.prog1.slenderman.game.display.Renderer;
import com.prog1.slenderman.game.level.Level;

public class Main {

    public static void main(String[] args) {
        Level level = new Level();
	    Renderer renderer = new Renderer(level);
    }
}
