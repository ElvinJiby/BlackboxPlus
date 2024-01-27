package Main;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    private Game game;

    public GameScreen(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(1280,720));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public Game getGame() { return game; }
}
