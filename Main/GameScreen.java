package Main;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    private Game game;

    public GameScreen(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(640,360));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        g.fillRect(100,100,50,100);
    }

    public Game getGame() { return game; }
}
