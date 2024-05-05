package menus;

import computations.LeaderBoardData;
import main.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Leaderboard implements Runnable {
    private static List<Map.Entry<String, Integer>> sortedScores = new ArrayList<>();

    @Override public void run() { jFrame.setVisible(true); }

    // all variables are private to allow for class accessibility
    // final variables allow for blocking further modification to my variables
    private final JFrame jFrame;
    private final JPanel jPanel;

    private static final Image gameIcon = new ImageIcon(Objects.requireNonNull(GameWindow.class.getResource("/Icons/new_icon.png"))).getImage();
    private static final ImageIcon leaderboardImage = new ImageIcon(Objects.requireNonNull(Leaderboard.class.getResource("/Miscellaneous/leaderboard_bg.png")));

    public Leaderboard() throws IOException {
        LeaderBoardData leaderBoardData = new LeaderBoardData();
        LeaderBoardData.checkFormat();
        leaderBoardData.readAndSort();
        sortedScores = leaderBoardData.getSortedScores();
        leaderBoardData.writeTXTFile(sortedScores);

        jFrame = new JFrame("Leaderboard");
        jFrame.setSize(1280, 720);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setIconImage(gameIcon);

        jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setSize(1280, 720);

        JLabel score = new JLabel("");
        Font font1 = score.getFont();
        displayScore(0, font1, 805, 275, 75, 25);
        displayScore(1, font1, 805, 350, 75, 25);
        displayScore(2, font1, 805, 420, 75, 25);
        displayScore(3, font1, 805, 490, 75, 25);
        displayScore(4, font1, 805, 560, 75, 25);

        displayPlayer(0, font1, 460, 275, 350, 35);
        displayPlayer(1, font1, 460, 350, 350, 35);
        displayPlayer(2, font1, 460, 420, 350, 35);
        displayPlayer(3, font1, 460, 490, 350, 35);
        displayPlayer(4, font1, 460, 560, 350, 35);

        // when pressed clears the txt file
        JButton clearLeaderboard = getClearLeaderboardButton();
        jPanel.add(clearLeaderboard);

        // exits the window and goes back to main menu
        JButton returnToMainMenu = getMainMenuButton();
        jPanel.add(returnToMainMenu);

        JLabel leaderboardBackground = new JLabel(leaderboardImage);
        leaderboardBackground.setSize(1280, 720);
        jPanel.add(leaderboardBackground);

        jFrame.add(jPanel);
    }

    private JButton getMainMenuButton() {
        JButton returnToMainMenu = new JButton();
        returnToMainMenu.setText("Main Menu");
        returnToMainMenu.setOpaque(false);
        returnToMainMenu.setBorderPainted(true);
        returnToMainMenu.setFocusable(false);
        returnToMainMenu.setBounds(765, 657, 140, 30);
        // lambda expression to check when pressed, return to main menu.
        returnToMainMenu.addActionListener(e -> {
            jFrame.dispose();
            try { new StartScreen(); }
            catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error: Failed to open Start Screen.", "Start Screen Open Error", JOptionPane.ERROR_MESSAGE); System.exit(-1);}
        });
        return returnToMainMenu;
    }

    private static JButton getClearLeaderboardButton() {
        JButton clearLeaderboard = new JButton();
        clearLeaderboard.setText("Clear Leaderboard");
        clearLeaderboard.setOpaque(false);
        clearLeaderboard.setFocusable(false);
        clearLeaderboard.setBorderPainted(true);
        clearLeaderboard.setBounds(680, 627, 220, 30);
        // this lambda expression detects whether the clear button is pressed or not
        clearLeaderboard.addActionListener(e -> {
            try { LeaderBoardData.clearTheLeaderboard(); }
            catch (Exception ex) { JOptionPane.showMessageDialog(null, "Failed to clear leaderboard.", "Leaderboard Data Clear Error", JOptionPane.ERROR_MESSAGE);}
        });
        return clearLeaderboard;
    }

    public void displayScore(int index, Font font, int x, int y, int width, int height) {
        JLabel score = new JLabel();
        score.setText(String.valueOf(sortedScores.get(index).getValue()));
        score.setFont(new Font(font.getFontName(), font.getStyle(), 30));
        score.setVerticalAlignment(SwingConstants.CENTER);
        score.setHorizontalAlignment(SwingConstants.CENTER);
        score.setBounds(x, y, width, height);
        jPanel.add(score);
    }

    public void displayPlayer(int index, Font font, int x, int y, int width, int height) {
        JLabel score = new JLabel();
        score.setText(String.valueOf(sortedScores.get(index).getKey()));
        score.setFont(new Font(font.getFontName(), font.getStyle(), 30));
        score.setVerticalAlignment(SwingConstants.CENTER);
        score.setBounds(x, y, width, height);
        jPanel.add(score);
    }
}