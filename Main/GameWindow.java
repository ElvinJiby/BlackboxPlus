package Main;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow {
    private JFrame gameWindow;

    public GameWindow(GameScreen gameScreen) {
        gameWindow = new JFrame();

        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setResizable(false);
        gameWindow.setSize(1280,720);
        gameWindow.setTitle("Blackbox+ - By Group 50");
        gameWindow.setVisible(true);
    }
}
