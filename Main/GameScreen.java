package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameScreen extends JPanel {
    private Game game;
    private Image bgImage = null;

    public GameScreen(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(1280,720)); // setting the resolution of the game
        bgImage = getBGImage();
    }

    private Image getBGImage() {
        try {
            bgImage = ImageIO.read(new File("./res/blackboxbgimage.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            return bgImage;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null);
//        g.setColor(Color.YELLOW); // sets the color of whatever graphic we're going to draw to Yellow
//        g.fillRect(100,100,50,100); // draws a rectangle at the x,y coords with a width of 50 pixels and height of 100 pixels
    }

    public Game getGame() { return game; } // accessor
}
