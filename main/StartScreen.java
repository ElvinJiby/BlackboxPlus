package main;

import javax.swing.*;

public class StartScreen {
    public StartScreen() {

        JFrame window = new JFrame("Black Box+ By Group 50");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1280, 720);
        window.setLocationRelativeTo(null);
        window.setIconImage(new ImageIcon("./res/icon-transparent-background.PNG").getImage());

        ImageIcon introScreen = new ImageIcon("./res/new-start-screen.JPG");
        System.out.println("image width:" + introScreen.getIconWidth());
        System.out.println("image height:" + introScreen.getIconHeight());
        JLabel introLabel = new JLabel(introScreen);



        introLabel.setSize(1280, 720);

        JButton newGame = new JButton();
        newGame.setBounds(130,550,230,60);
        newGame.setOpaque(false);
        newGame.setContentAreaFilled(false);
        newGame.setBorderPainted(false);
        newGame.setFocusable(true);
        newGame.addActionListener(e -> System.out.println("Button clicked"));
        introLabel.add(newGame);

        JButton leaderboard = new JButton();
        leaderboard.setBounds(400,550,300,60);
        leaderboard.setOpaque(false);
        leaderboard.setContentAreaFilled(false);
        leaderboard.setBorderPainted(false);
        leaderboard.setFocusable(true);
        leaderboard.addActionListener(e -> System.out.println("Button clicked"));
        introLabel.add(leaderboard);

        JButton credits = new JButton();
        credits.setBounds(743,550,200,60);
        credits.setOpaque(false);
        credits.setContentAreaFilled(false);
        credits.setBorderPainted(false);
        credits.setFocusable(true);
        credits.addActionListener(e -> System.out.println("Button clicked"));
        introLabel.add(credits);

        JButton exitGame = new JButton();
        exitGame.setBounds(991,550,110,60);
        exitGame.setOpaque(false);
        exitGame.setContentAreaFilled(false);
        exitGame.setBorderPainted(false);
        exitGame.setFocusable(true);
        exitGame.addActionListener(e -> System.exit(0));
        introLabel.add(exitGame);

        window.add(introLabel);
        window.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartScreen());
    }
}
