package menus;

import inputs.LeaderBoardData;
import inputs.OperatingSystem;
import main.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class StartScreen {
    private static JFrame jFrame;
    private static JLabel introLabel;

    public StartScreen() {
        jFrame = new JFrame("Black Box+ By Group 50"); // title
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // exit button controlls close
        jFrame.setResizable(false); // stay size
        jFrame.setSize(1280, 720); // set size
        jFrame.setLocationRelativeTo(null); //centre window
        jFrame.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Icons/new_icon.png"))).getImage()); // app icon

        introLabel = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Start Screen/new-start-screen.JPG"))));
        introLabel.setPreferredSize(new Dimension(1280, 720));

        /* New Game Button */
        JButton newGame = generateButtons(130, 560, 230, 60);
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
        JButton leaderboard = generateButtons(400, 560, 300, 60);
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
        JButton credits = generateButtons(743, 560, 200, 60);
        credits.addActionListener(e -> {
//            jFrame.dispose();
            try {
                new Credits();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Failed to load credits.", "Credits Load Error", JOptionPane.ERROR_MESSAGE);
                System.exit(-1);
            }
        });

        /* Exit Button */
        JButton exitGame = generateButtons(991, 560, 110, 60);
        exitGame.addActionListener(e -> System.exit(0)); // if pressed, exit program

        jFrame.add(introLabel); // add label image to frame
        jFrame.pack(); // size window
        jFrame.setVisible(true); // show window
    }

    private JButton generateButtons(int x, int y, int width, int height) {
        JButton jButton = new JButton(); // create new button
        boolean isItMac = OperatingSystem.isItMacOS(); // check os type
        jButton.setBounds(x, y, width, height); // set size
        jButton.setOpaque(false); // no opaque
        jButton.setContentAreaFilled(false); // do not fill area
        jButton.setBorderPainted(!isItMac); // if macOS, paint border
        jButton.setFocusable(true); // outline button
        introLabel.add(jButton); // add to image
        return jButton;
    }
}
