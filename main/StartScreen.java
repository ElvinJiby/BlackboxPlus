package main;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class StartScreen {
    private final JFrame window;
    private final ImageIcon introScreen;
    private final JLabel introLabel;
    private final JButton newGame;
//    private final JButton leaderboard;
//    private final JButton credits;
//    private final JButton exitGame;

    public StartScreen() {

        window = new JFrame("Background Image Example");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1920, 1080);
        window.setLocationRelativeTo(null);

        introScreen = new ImageIcon("./res/IMG_1914.JPG");
        System.out.println("image width:" + introScreen.getIconWidth());
        System.out.println("image height:" + introScreen.getIconHeight());
        introLabel = new JLabel(introScreen);



        introLabel.setSize(1920, 1080);

        newGame = new JButton("test");
        newGame.setBounds(220,850,630,150);
        introLabel.add(newGame);

        window.add(introLabel);
        window.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            StartScreen startScreen = new StartScreen();
        });
    }
}
