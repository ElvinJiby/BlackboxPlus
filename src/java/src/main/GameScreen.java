package main;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    // Application Variable
    private final Game game;

    public GameScreen(Game game) {
        this.game = game;

        setPreferredSize(new Dimension(1280, 720)); // setting the resolution of the game
        setBackground(Color.BLACK);
        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame() {
        return game;
    } // accessor
}
