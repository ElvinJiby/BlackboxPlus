package Main;

import javax.swing.*;

public class GameWindow {
    private JFrame gameWindow;

    public GameWindow(GameScreen gameScreen) {
        gameWindow = new JFrame(); // creates a new window

        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // when you click the X to close to program, the program actually closes (by default it just hides the window)
        gameWindow.add(gameScreen); // adding the screen to the window (the screen pretty much contains the game/visuals)
        gameWindow.setLocationRelativeTo(null); // when opened, the window will open in the middle of the screen, instead of the top left
        gameWindow.setResizable(false); // disables the ability to resize the window
        gameWindow.pack(); // creates the window according to a specific resolution
        gameWindow.setTitle("Blackbox+ - By Group 50"); // title of the window
        gameWindow.setVisible(true); // visibility option to actually see the window
        gameWindow.setLayout(null);
        gameWindow.setIconImage((new ImageIcon("./res/icon.png")).getImage()); // changes the window icon to a custom image
    }
}
