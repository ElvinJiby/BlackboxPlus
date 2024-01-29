package Main;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    private Game game;

    public GameScreen(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(640,360)); // setting the resolution of the game
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.YELLOW); // sets the color of whatever graphic we're going to draw to Yellow
        g.fillRect(100,100,50,100); // draws a rectangle at the x,y coords with a width of 50 pixels and height of 100 pixels
    }

    public Game getGame() { return game; } // accessor
}
