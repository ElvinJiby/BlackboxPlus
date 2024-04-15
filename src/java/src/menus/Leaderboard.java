package menus;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Leaderboard implements Runnable {
    private static List<Map.Entry<String, Integer>> sortedScores = new ArrayList<>();

    @Override
    public void run() {jFrame.setVisible(true);}

    // my instance variables, which are later constructed
    // all variables are private to allow for class accessibility
    // final variables allow for blocking further modification to my variables
    private final JFrame jFrame; // app window
    private final JPanel jPanel; // layout within the app window


    private final JLabel leaderboardBackground;
    private final JLabel score = new JLabel("");


    private final JButton clearLeaderboard; // when pressed clears the txt file
    private final JButton goBackBruh; // exits the window and goes back to main menu

    public static void main(String[] args) throws IOException {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.run();
    }

    public Leaderboard() throws IOException {
        LeaderBoardData leaderBoardData = new LeaderBoardData();
        leaderBoardData.processFile();
        leaderBoardData.readAndSort();
        sortedScores = leaderBoardData.getSortedScores();
        leaderBoardData.writeTXTFile(sortedScores);

        jFrame = new JFrame("Leaderboard"); // window title
        jFrame.setSize(1280, 720); // has a fixed 720p 16:9 size
        jFrame.setResizable(false); // ensures the app window will always stay at 1280x720 resolution
        jFrame.setLocationRelativeTo(null); // when window is opened initially it is centred
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setIconImage(new ImageIcon(getClass().getResource("/Icons/new_icon.png")).getImage());

        jPanel = new JPanel(); // creates new JPanel object
        jPanel.setLayout(null); // setting null allows elements to be placed anywhere on the panel
        jPanel.setSize(1280, 720); // sets max region to add elements to panel

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

        clearLeaderboard = new JButton();
        clearLeaderboard.setText("Clear Leaderboard");
        // sets region where label should be placed **in pixels**
        clearLeaderboard.setOpaque(false);
        clearLeaderboard.setFocusable(false);
        clearLeaderboard.setBorderPainted(true);
//        clearLeaderboard.setFocusable(false);
//        clearLeaderboard.setFocusPainted(false);
        clearLeaderboard.setBounds(680, 627, 220, 30);
        // this lambda expression detects whether the clear button is pressed or not
        clearLeaderboard.addActionListener(e -> {
            try { LeaderBoardData.clearTheLeaderboard(); }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Failed to clear leaderboard.", "Leaderboard Data Clear Error", JOptionPane.ERROR_MESSAGE);
//                throw new RuntimeException(ex);
            }});
        jPanel.add(clearLeaderboard); // add the generate button to the JPanel

        goBackBruh = new JButton(); // Exit button to terminate program
        // sets region where label should be placed **in pixels**
        // this label is 100x50 with the top-left corner going across 377 and 290 down
        goBackBruh.setText("Main Menu");
        goBackBruh.setOpaque(false);
        goBackBruh.setBorderPainted(true);
        goBackBruh.setFocusable(false);
//        goBackBruh.setFocusable(false);
//        goBackBruh.setFocusPainted(false);
        goBackBruh.setBounds(765, 657, 140, 30);
        // lambda expression to check when pressed, if-so, return to main menu.
        goBackBruh.addActionListener(e -> {
            jFrame.dispose();
            try {
                new StartScreen();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Error: Failed to open Start Screen.", "Start Screen Open Error", JOptionPane.ERROR_MESSAGE);
                System.exit(-1);
            }
        });

        jPanel.add(goBackBruh);// add the button to the panel


        ImageIcon introScreen = new ImageIcon(getClass().getResource("/Miscellaneous/leaderboard_bg.png"));
        leaderboardBackground = new JLabel(introScreen);
        leaderboardBackground.setSize(1280, 720);
        jPanel.add(leaderboardBackground);

        jFrame.add(jPanel); // add the JPanel to the JFrame to display its contents
    }

    public void displayScore(int index, Font font, int x, int y, int width, int height) {
        JLabel score = new JLabel();
        score.setText(String.valueOf(sortedScores.get(index).getValue()));
        score.setFont(new Font(font.getFontName(), font.getStyle(), 30));
        score.setVerticalAlignment(SwingConstants.CENTER); // centres label according to the height of the label
        score.setHorizontalAlignment(SwingConstants.CENTER); // centres label according to the width of the label
        // sets region where label should be placed **in pixels**
        score.setBounds(x, y, width, height);
        jPanel.add(score); // adds this label to the panel
    }

    public void displayPlayer(int index, Font font, int x, int y, int width, int height) {
        JLabel score = new JLabel();
        score.setText(String.valueOf(sortedScores.get(index).getKey()));
        score.setFont(new Font(font.getFontName(), font.getStyle(), 30));
        score.setVerticalAlignment(SwingConstants.CENTER); // centres label according to the height of the label
        // sets region where label should be placed **in pixels**
        score.setBounds(x, y, width, height);
        jPanel.add(score); // adds this label to the panel
    }
}