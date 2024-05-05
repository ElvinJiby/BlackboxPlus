package main;

import javax.swing.*;
import java.awt.*;

/**
 * Used for the displaying of the BlackBox game elements inside the GameWindow
 * Extends the JPanel class for its paintComponent method
 */
public class GameScreen extends JPanel {
    /**
     * An instance variable of the Game class.
     */
    private final Game game;

    /**
     * Constructor for the GameScreen class.
     *
     * @param game Used for connecting the Game and GameScreen classes together
     */
    public GameScreen(Game game) {
        this.game = game;

        setPreferredSize(new Dimension(1280, 720)); // setting the resolution of the game
        setBackground(Color.BLACK);
        setVisible(true);
    }

    /**
     * Handles the drawing of game graphics onto the screen.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    /**
     * Accessor method for the Game instance in the GameScreen class.
     *
     * @return Returns the Game instance used for the GameScreen
     */
    public Game getGame() {
        return game;
    }
}
