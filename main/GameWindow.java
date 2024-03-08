package main;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow {
    private JFrame gameWindow;
    private ImageIcon imageIcon;

    public GameWindow(GameScreen gameScreen) {
        gameWindow = new JFrame(); // creates a new window
        gameWindow.setSize(1280,720);
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // when you click the X to close to program, the program actually closes (by default it just hides the window)
        gameWindow.add(gameScreen); // adding the screen to the window (the screen pretty much contains the game/visuals)
        gameWindow.setLocationRelativeTo(null); // when opened, the window will open in the middle of the screen, instead of the top left
        gameWindow.setResizable(false); // disables the ability to resize the window
        gameWindow.pack(); // creates the window according to a specific resolution
        gameWindow.setTitle("Blackbox+ - By Group 50"); // title of the window
        gameWindow.setVisible(true); // visibility option to actually see the window
        gameWindow.setLayout(null);
        gameWindow.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("res/Icons/icon.png")).getImage());
    }
}
