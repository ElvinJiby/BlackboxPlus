package menus;

import computations.LeaderBoardData;
import main.GameWindow;
import main.OperatingSystem;
import main.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class StartScreen {
    private static JFrame jFrame;
    private static JLabel introLabel;
    private static final Image gameIcon = new ImageIcon(Objects.requireNonNull(GameWindow.class.getResource("/Icons/new_icon.png"))).getImage();
    private static ImageIcon startScreenImage = new ImageIcon(Objects.requireNonNull(StartScreen.class.getResource("/Start Screen/new-start-screen.JPG")));

    public StartScreen() {
        jFrame = new JFrame("Black Box+ By Group 50");
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setSize(1280, 720);
        jFrame.setLocationRelativeTo(null);
        jFrame.setIconImage(gameIcon);

        introLabel = new JLabel(startScreenImage);
        introLabel.setPreferredSize(new Dimension(1280, 720));

        /* New Game Button */
        JButton newGame = generateButtons(130, 230);
        newGame.addActionListener(e -> {
            jFrame.dispose();
            try {
                new Game();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Failed to start a new game.", "Start New Game Open Error", JOptionPane.ERROR_MESSAGE);
                System.exit(-1);
            }
        });

        /* Leaderboard Button */
        JButton leaderboard = generateButtons(400, 300);
        leaderboard.addActionListener(e -> {
            jFrame.dispose();
            try {
                new Leaderboard().run();
            } catch (Exception ex) {
                ex.printStackTrace();
                try {
                    LeaderBoardData.clearTheLeaderboard();
                } catch (IOException exc) {
                    throw new RuntimeException(exc);
                }
                JOptionPane.showMessageDialog(null, "Failed to open leaderboard. Try again!", "Leaderboard Open Error", JOptionPane.ERROR_MESSAGE);
                System.exit(-1);
            }
        });

        /* Credits Button */
        JButton credits = generateButtons(743, 200);
        credits.addActionListener(e -> {
            try {
                new Credits();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Failed to load credits.", "Credits Load Error", JOptionPane.ERROR_MESSAGE);
                System.exit(-1);
            }
        });

        /* Exit Button */
        JButton exitGame = generateButtons(991, 110);
        exitGame.addActionListener(e -> System.exit(0)); // if pressed, exit program

        jFrame.add(introLabel); // add label image to frame
        jFrame.pack(); // size window
        jFrame.setVisible(true); // show window
    }

    private JButton generateButtons(int x, int width) {
        JButton jButton = new JButton();
        boolean isItMac = OperatingSystem.isItMacOS(); // check os type
        jButton.setBounds(x, 560, width, 60);
        jButton.setOpaque(false);
        jButton.setContentAreaFilled(false);
        jButton.setBorderPainted(!isItMac); // if macOS, paint border
        jButton.setFocusable(true);
        introLabel.add(jButton);
        return jButton;
    }
}
