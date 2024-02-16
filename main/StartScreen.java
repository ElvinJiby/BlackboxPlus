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
        window.setSize(1280, 720);
        window.setLocationRelativeTo(null);
        window.setIconImage(new ImageIcon("./res/icon-transparent-background.PNG").getImage());

        introScreen = new ImageIcon("./res/new-start-screen.JPG");
        System.out.println("image width:" + introScreen.getIconWidth());
        System.out.println("image height:" + introScreen.getIconHeight());
        introLabel = new JLabel(introScreen);



        introLabel.setSize(1280, 720);

        newGame = new JButton();
        newGame.setBounds(130,550,230,60);
        newGame.setOpaque(false);
        newGame.setContentAreaFilled(false);
        newGame.setBorderPainted(false);
        newGame.setFocusable(false);
        newGame.addActionListener(e -> {
            System.out.println("Button clicked");
        });
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
