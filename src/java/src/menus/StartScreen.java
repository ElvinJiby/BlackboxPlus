package menus;

import inputs.OperatingSystem;
import main.Game;

import javax.swing.*;
import java.awt.*;

public class StartScreen {
    public StartScreen() {
        boolean isItMacOS = OperatingSystem.isItMacOS();
        JFrame window = new JFrame("Black Box+ By Group 50");
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setResizable(false);
        window.setSize(1280, 720);
        window.setLocationRelativeTo(null);
        window.setIconImage(new ImageIcon(getClass().getResource("/Icons/new_icon.png")).getImage());

        JLabel introLabel = new JLabel(new ImageIcon(getClass().getResource("/Start Screen/new-start-screen.JPG")));
        introLabel.setPreferredSize(new Dimension(1280, 720));

        /* New Game Button */
        JButton newGame = new JButton();
        newGame.setBounds(130, 560, 230, 60);
        newGame.setOpaque(false);
        newGame.setContentAreaFilled(false);
        newGame.setBorderPainted(!isItMacOS);
        newGame.setFocusable(true);
        newGame.addActionListener(e -> {
            window.dispose();
            try {
                Game game = new Game();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Failed to start a new game.", "Start New Game Open Error", JOptionPane.ERROR_MESSAGE);
                System.exit(-1);
            }
        });
        introLabel.add(newGame);

        /* Leaderboard Button */
        JButton leaderboard = new JButton();
        leaderboard.setBounds(400, 560, 300, 60);
        leaderboard.setOpaque(false);
        leaderboard.setContentAreaFilled(false);
        leaderboard.setBorderPainted(!isItMacOS);
        leaderboard.setFocusable(true);
        leaderboard.addActionListener(e -> {
//            System.out.println("Leaderboard clicked");
            window.dispose();
            try {
                new Leaderboard().run();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to open leaderboard.", "Leaderboard Open Error", JOptionPane.ERROR_MESSAGE);
                System.exit(-1);
            }

        });
        introLabel.add(leaderboard);

        /* Credits Button */
        JButton credits = new JButton();
        credits.setBounds(743, 560, 200, 60);
        credits.setOpaque(false);
        credits.setContentAreaFilled(false);
        credits.setBorderPainted(!isItMacOS);
        credits.setFocusable(true);
        credits.addActionListener(e -> {
            window.dispose();
            try {
                new Credits().run();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Failed to load credits.", "Credits Load Error", JOptionPane.ERROR_MESSAGE);
                System.exit(-1);
            }
        });
        introLabel.add(credits);

        /* Exit Button */
        JButton exitGame = new JButton();
        exitGame.setBounds(991, 560, 110, 60);
        exitGame.setOpaque(false);
        exitGame.setContentAreaFilled(false);
        exitGame.setBorderPainted(!isItMacOS);
        exitGame.setFocusable(true);
        exitGame.addActionListener(e -> System.exit(0));
        introLabel.add(exitGame);

        window.add(introLabel);
        window.pack();
        window.setVisible(true);
    }
}
