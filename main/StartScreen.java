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
        window.setSize(3840, 2160);
        window.setLocationRelativeTo(null);

        introScreen = new ImageIcon(this.getClass().getResource("intro-screen.JPG"));
        introLabel = new JLabel(introScreen);
        introLabel.setPreferredSize(new Dimension(introScreen.getIconWidth(), introScreen.getIconHeight()));
        introLabel.setSize(3840,2160);



        newGame = new JButton("test");
        newGame.setBounds(440,1700,630,150);
        introLabel.add(newGame);

        window.add(introLabel);
        window.setVisible(true);

    }

    public static void main(String[] args) {
        new StartScreen();
    }
}
