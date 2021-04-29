package com.prog1.slenderman.game.display;

import com.prog1.slenderman.game.Game;
import com.prog1.slenderman.game.resource.*;

import javax.swing.*;
import java.awt.*;

/**
 * A fő ablak, amiben a játék fut.
 */
public class MainWindow extends JFrame {
    private JLabel titleLabel;
    private JButton bStart;
    private JButton bFile;

    /**
     * Cím label lekérdezése
     *
     * @return Swing JLabel
     */
    public JLabel getTitleLabel() {
        return titleLabel;
    }

    /**
     * Fő ablak inicializálása
     */
    public MainWindow() {
        setupWindow();

        setupTitleLabel();
    }

    /**
     * Főmenü inicializálása és mutatása
     */
    public void showMainMenu() {
        this.bStart = new JButton("JÁTÉK INDÍTÁSA");
        this.bStart.setBounds(50, 0, 400, 100);

        this.bFile = new JButton("JÁTÉK INDÍTÁSA FÁJLBÓL");
        this.bFile.setBounds(50, 200, 400, 100);

        this.bStart.addActionListener(e -> {
            this.remove(this.bStart);
            this.remove(this.bFile);
            this.bStart = null;
            this.bFile = null;
            Game.startGame(null);
        });

        this.bFile.addActionListener(e -> {
            String mapFile = SwingFileChooser.open(this);

            System.out.println("Pálya betöltése: " + mapFile);

            if (mapFile != null) {
                this.remove(this.bStart);
                this.remove(this.bFile);
                this.bStart = null;
                this.bFile = null;
                Game.startGame(mapFile);
            }
        });

        this.add(this.bStart);
        this.add(this.bFile);

        Game.update();
    }

    /**
     * A játék panel beállítása
     *
     * @param view Játék panel
     */
    public void setupMainView(MainView view) {
        view.setBounds(0, 0, this.getWidth(), this.getHeight());
        view.setVisible(true);
        view.setSize(1280, 720);
        view.setLayout(null); //using no layout managers

        this.add(view);
    }

    /**
     * A GUI ablak beállítása
     */
    private void setupWindow() {
        this.getContentPane().setBackground(Color.BLACK);
        this.setSize(1280, 720);

        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(500, 500); //500 width and 500 height
        this.setLayout(null); //using no layout managers
        this.setVisible(true); //making the frame visible
    }

    /**
     * Fő cím label beállítása, amely a papírok számát jelzi
     */
    private void setupTitleLabel() {
        this.titleLabel = new JLabel("PAGES: ", SwingConstants.CENTER);
        this.titleLabel.setBounds(0, 0, 0, 32);
        this.titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 32));
        this.titleLabel.setForeground(Color.WHITE);
        this.titleLabel.setVisible(false);
        this.add(this.titleLabel);
    }

    /**
     * Ablak újrarajzolás, labelek frissítése
     */
    public void update() {
        if (this.titleLabel != null) {
            this.titleLabel.setText("PAGES: " + Game.pagesCollected);
            this.titleLabel.setBounds(0, 0, Game.mainWindow.getWidth(), this.titleLabel.getHeight());
        }

        updateMainMenuButtons();
    }

    /**
     * Menü gombjainak frissítése
     */
    private void updateMainMenuButtons() {
        if (this.bStart != null && this.bFile != null) {
            int x = CenterFactory.CenterHorizontal(this.bStart, this.getWidth());
            int y = CenterFactory.CenterVertical(this.bStart, this.getHeight());
            this.bStart.setBounds(x, y - 75, this.bStart.getWidth(), this.bStart.getHeight());

            x = CenterFactory.CenterHorizontal(this.bFile, this.getWidth());
            y = CenterFactory.CenterVertical(this.bFile, this.getHeight());
            this.bFile.setBounds(x, y + 75, this.bFile.getWidth(), this.bFile.getHeight());
        }
    }
}
