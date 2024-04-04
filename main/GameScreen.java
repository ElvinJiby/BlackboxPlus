package main;

import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    // Application Variables
    private final Game game;
    private final MouseInputs mouseInputs;

    public GameScreen(Game game) {
        this.game = game;

        mouseInputs = new MouseInputs(this); // add mouse input support
        addMouseListener(mouseInputs); // needed to perform mouse actions

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
