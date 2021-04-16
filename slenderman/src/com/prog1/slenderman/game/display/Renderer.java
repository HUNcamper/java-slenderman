package com.prog1.slenderman.game.display;

import com.prog1.slenderman.game.level.Level;

public abstract class Renderer {
    private static boolean isAnsiSupport() {
        return false;
    }

    public static void RenderLevel(Level level) {

    }

    public static void RenderText(String text) {
        System.out.println(text);
    }

    public static void RenderText(String text, String color) {
        RenderText(isAnsiSupport() ? color + text : text);
    }

    public static void Clear() {
        if(isAnsiSupport()) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } else {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}
