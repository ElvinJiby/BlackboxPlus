package main;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class StartScreen {
    public StartScreen() {
        JFrame window = new JFrame("Black Box+ By Group 50");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1280, 720);
        window.setLocationRelativeTo(null);
        window.setIconImage(new ImageIcon("./res/Icons/icon-transparent-background.PNG").getImage());

        ImageIcon introScreen = new ImageIcon("./res/Start Screen/new-start-screen.JPG");
        System.out.println("image width:" + introScreen.getIconWidth());
        System.out.println("image height:" + introScreen.getIconHeight());
        JLabel introLabel = new JLabel(introScreen);
        introLabel.setPreferredSize(new Dimension(1280,720));

        /* New Game Button */
        JButton newGame = new JButton();
        newGame.setBounds(130,560,230,60);
        newGame.setOpaque(false);
        newGame.setContentAreaFilled(false);
        newGame.setBorderPainted(true);
        newGame.setFocusable(true);
        newGame.addActionListener(e -> {
            System.out.println("New Game clicked");
            window.dispose();
            new Game();
        });
        introLabel.add(newGame);

        /* Leaderboard Button */
        JButton leaderboard = new JButton();
        leaderboard.setBounds(400,560,300,60);
        leaderboard.setOpaque(false);
        leaderboard.setContentAreaFilled(false);
        leaderboard.setBorderPainted(true);
        leaderboard.setFocusable(true);
        leaderboard.addActionListener(e -> {
            System.out.println("Leaderboard clicked");
            window.dispose();
            try {
                new LeaderBoard();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        introLabel.add(leaderboard);

        /* Credits Button */
        JButton credits = new JButton();
        credits.setBounds(743,560,200,60);
        credits.setOpaque(false);
        credits.setContentAreaFilled(false);
        credits.setBorderPainted(true);
        credits.setFocusable(true);
        credits.addActionListener(e -> System.out.println("Credits clicked"));
        introLabel.add(credits);

        /* Exit Button */
        JButton exitGame = new JButton();
        exitGame.setBounds(991,560,110,60);
        exitGame.setOpaque(false);
        exitGame.setContentAreaFilled(false);
        exitGame.setBorderPainted(true);
        exitGame.setFocusable(true);
        exitGame.addActionListener(e -> System.exit(0));
        introLabel.add(exitGame);

        window.add(introLabel);
        window.pack();
        window.setVisible(true);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new StartScreen());
//    }
}
