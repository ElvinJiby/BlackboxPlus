import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class LeaderboardNew implements Runnable {
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

    public static void main(String[] args) throws FileNotFoundException {
        LeaderboardNew leaderboardNew = new LeaderboardNew();
        leaderboardNew.run();
    }

    public LeaderboardNew() throws FileNotFoundException {
        LeaderBoardDataNew leaderBoardDataNew = new LeaderBoardDataNew();
        leaderBoardDataNew.readAndSort();
        sortedScores = leaderBoardDataNew.getSortedScores();
        leaderBoardDataNew.writeTXTFile(sortedScores);

        jFrame = new JFrame("Leaderboard"); // window title
        jFrame.setSize(1280, 720); // has a fixed 720p 16:9 size
        jFrame.setResizable(false); // ensures the app window will always stay at 1280x720 resolution
        jFrame.setLocationRelativeTo(null); // when window is opened initially it is centred

        jPanel = new JPanel(); // creates new JPanel object
        jPanel.setLayout(null); // setting null allows elements to be placed anywhere on the panel
        jPanel.setSize(1280, 720); // sets max region to add elements to panel

        Font font1 = score.getFont();
        displayScore(0, font1, 805, 275, 75, 25);
        displayScore(1, font1, 805, 350, 75, 25);
        displayScore(2, font1, 805, 420, 75, 25);
        displayScore(3, font1, 805, 490, 75, 25);
        displayScore(4, font1, 805, 560, 75, 25);

        displayPlayer(0, font1, 460, 275, 350, 25);
        displayPlayer(1, font1, 460, 350, 350, 25);
        displayPlayer(2, font1, 460, 420, 350, 25);
        displayPlayer(3, font1, 460, 490, 350, 25);
        displayPlayer(4, font1, 460, 560, 350, 25);

        clearLeaderboard = new JButton();
        // sets region where label should be placed **in pixels**
        clearLeaderboard.setOpaque(false);
        clearLeaderboard.setBorderPainted(false);
//        clearLeaderboard.setFocusable(false);
//        clearLeaderboard.setFocusPainted(false);
        clearLeaderboard.setBounds(680, 627, 220, 30);
        // this lambda expression detects whether the clear button is pressed or not
        clearLeaderboard.addActionListener(e -> {
            try { LeaderBoardData.clearTheDamnTXTFile(); }
            catch (IOException ex) { throw new RuntimeException(ex); }});
        jPanel.add(clearLeaderboard); // add the generate button to the JPanel

        goBackBruh = new JButton(); // Exit button to terminate program
        // sets region where label should be placed **in pixels**
        // this label is 100x50 with the top-left corner going across 377 and 290 down
        goBackBruh.setOpaque(false);
        goBackBruh.setBorderPainted(false);
//        goBackBruh.setFocusable(false);
//        goBackBruh.setFocusPainted(false);
        goBackBruh.setBounds(765, 657, 140, 30);
        // lambda expression to check when pressed, if-so, exit the program with status 0.
        goBackBruh.addActionListener(e -> System.exit(0));
        jPanel.add(goBackBruh);// add the button to the panel

        JButton button = new JButton("Test New Window");
        button.setBounds(150, 580, 150, 50);
        button.addActionListener(e -> {
            Credits leaderboard = new Credits();
            leaderboard.run();
        });
        jPanel.add(button);

        ImageIcon introScreen = new ImageIcon("./res/Miscellaneous/leaderboard_bg.png");
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