package Main;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow {
    private JFrame gameWindow;

    public GameWindow(GameScreen gameScreen) {
        gameWindow = new JFrame();

        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.add(gameScreen);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setResizable(false);
        gameWindow.pack();
        gameWindow.setTitle("Blackbox+ - By Group 50");
        gameWindow.setVisible(true);
    }
}
