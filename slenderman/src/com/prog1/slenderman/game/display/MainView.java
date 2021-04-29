package com.prog1.slenderman.game.display;

import com.prog1.slenderman.game.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * A játékhoz használatos nézetet leíró osztály, amely egy JLayeredPane-ből származik.<br>
 *     Azért a JLayeredPane használatos, mert egyes elemek mások felett lehetnek. pl. fa a player feje felett van.
 */
public class MainView extends JLayeredPane {
    public final int baseResolutionWidth = 1500;
    public final int baseResolution_height = 1500;
    private final float baseRatio = (float) baseResolutionWidth / baseResolution_height;
    public float baseZoom = 2f; // change this
    public float zoom = 1f; // automatically calculated at runtime

    public int padding = 100;

    private JLabel interactLabel;

    /**
     * Interakció label lekérése
     * @return Swing JLabel
     */
    public JLabel getInteractLabel() {
        return interactLabel;
    }

    /**
     * A nézet beállítása egy fekete körvonallal
     */
    public MainView() {
        Border blackline = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackline);

        setupInteractLabel();
    }

    /**
     * "Press 'F' to pick up" label beállítása
     */
    public void setupInteractLabel() {
        if (this.interactLabel != null) this.remove(this.interactLabel);

        this.interactLabel = new JLabel("Press 'F' to pick up", SwingConstants.CENTER);
        this.interactLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 32));
        this.interactLabel.setForeground(Color.WHITE);
        this.add(this.interactLabel, 10, 0);
    }

    /**
     * Újrarajzolás, a komponenes középre helyezése az ablakban, és helyes átmérezetés
     */
    public void update() {
        int windowWidth = Game.mainWindow.getWidth();
        int windowHeight = Game.mainWindow.getHeight();
        float newRatio = (float) windowWidth / windowHeight;

        int newX = 0;
        int newY = 0;

        if (newRatio > baseRatio) {
            int newWidth = (int) (baseResolutionWidth * ((float) windowHeight / baseResolution_height)) - this.padding;
            int newHeight = windowHeight - this.padding;
            newX = windowWidth / 2 - newWidth / 2;
            newY = this.padding / 2;

            Game.mainView.setBounds(newX, newY, newWidth, newHeight);
        } else {
            int new_width = windowWidth - this.padding;
            int new_height = (int) (baseResolution_height * ((float) windowWidth / baseResolutionWidth)) - this.padding;
            newX = this.padding / 2;
            newY = windowHeight / 2 - new_height / 2;

            Game.mainView.setBounds(newX, newY, new_width, new_height);
        }

        int newWidth = Game.mainView.getWidth();

        this.zoom = ((float) newWidth / baseResolutionWidth) * baseZoom;

        updateInteract();
    }

    private void updateInteract() {
        if (Game.mainPlayer.canInteract()) {
            this.interactLabel.setVisible(true);
            this.interactLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
        } else if (this.interactLabel.isVisible()) {
            this.interactLabel.setVisible(false);
        }
    }
}
