package menus;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class LeaderboardNew implements Runnable {
    private static List<Map.Entry<String, Integer>> sortedScores = new ArrayList<>();

    @Override
    public void run() {
        jFrame.setVisible(true);
    }

    // my instance variables, which are later constructed
    // all variables are private to allow for class accessibility
    // final variables allow for blocking further modification to my variables
    private final JFrame jFrame; // app window
    private final JPanel jPanel; // layout within the app window


    private final JLabel leaderboardBackground;
    private final JLabel score1;
    private final JLabel score2;
    private final JLabel score3;
    private final JLabel score4;
    private final JLabel score5;
    private final JLabel player1;
    private final JLabel player2;
    private final JLabel player3;
    private final JLabel player4;
    private final JLabel player5;

    private final JButton clearLeaderboard; // when pressed clears the txt file
    private final JButton goBackBruh; // exits the window and goes back to main menu

    public static void main(String[] args) throws FileNotFoundException {
        LeaderboardNew leaderboardNew = new LeaderboardNew();
        leaderboardNew.run();
    }

    public LeaderboardNew() throws FileNotFoundException {
//        Border border = BorderFactory.createLineBorder(Color.BLACK);
        LeaderBoardDataNew leaderBoardDataNew = new LeaderBoardDataNew();
        leaderBoardDataNew.readAndSort();
        sortedScores = leaderBoardDataNew.getSortedScores();
        leaderBoardDataNew.writeTXTFile(sortedScores);
//        if (sortedScores.get(0) == null)
//        System.out.println(scoresLinkedHashMap);
//        System.out.println(sortedScores);

        jFrame = new JFrame("Leaderboard"); // window title
        jFrame.setSize(1280, 720); // has a fixed 720p 16:9 size
        jFrame.setResizable(false); // ensures the app window will always stay at 1280x720 resolution
        jFrame.setLocationRelativeTo(null); // when window is opened initially it is centred

        jPanel = new JPanel(); // creates new JPanel object
        jPanel.setLayout(null); // setting null allows elements to be placed anywhere on the panel
        jPanel.setSize(1280, 720); // sets max region to add elements to panel

        score1 = new JLabel();
        if (sortedScores.get(0).getValue() != null) score1.setText(String.valueOf(sortedScores.get(0).getValue()));
//        score1.setBorder(border);
        Font font1 = score1.getFont();
        score1.setFont(new Font(font1.getFontName(), font1.getStyle(), 30));
        score1.setVerticalAlignment(SwingConstants.CENTER); // centres label according to the height of the label
        score1.setHorizontalAlignment(SwingConstants.CENTER); // centres label according to the width of the label
        // sets region where label should be placed **in pixels**
        score1.setBounds(805, 275, 75, 25);
        jPanel.add(score1); // adds this label to the panel

        score2 = new JLabel();
        score2.setText(String.valueOf(sortedScores.get(1).getValue()));
        score2.setFont(new Font(font1.getFontName(), font1.getStyle(), 30));
        score2.setVerticalAlignment(SwingConstants.CENTER); // centres label according to the height of the label
        score2.setHorizontalAlignment(SwingConstants.CENTER); // centres label according to the width of the label
        // sets region where label should be placed **in pixels**
        score2.setBounds(805, 350, 75, 25);
        jPanel.add(score2); // adds this label to the panel

        score3 = new JLabel();
        score3.setText(String.valueOf(sortedScores.get(2).getValue()));
        score3.setFont(new Font(font1.getFontName(), font1.getStyle(), 30));
        score3.setVerticalAlignment(SwingConstants.CENTER); // centres label according to the height of the label
        score3.setHorizontalAlignment(SwingConstants.CENTER); // centres label according to the width of the label
        // sets region where label should be placed **in pixels**
        score3.setBounds(805, 420, 75, 25);
        jPanel.add(score3); // adds this label to the panel

        score4 = new JLabel();
        score4.setText(String.valueOf(sortedScores.get(3).getValue()));
        score4.setFont(new Font(font1.getFontName(), font1.getStyle(), 30));
        score4.setVerticalAlignment(SwingConstants.CENTER); // centres label according to the height of the label
        score4.setHorizontalAlignment(SwingConstants.CENTER); // centres label according to the width of the label
        // sets region where label should be placed **in pixels**
        score4.setBounds(805, 490, 75, 25);
        jPanel.add(score4); // adds this label to the panel

        score5 = new JLabel();
        score5.setText(String.valueOf(sortedScores.get(4).getValue()));
        score5.setFont(new Font(font1.getFontName(), font1.getStyle(), 30));
        score5.setVerticalAlignment(SwingConstants.CENTER); // centres label according to the height of the label
        score5.setHorizontalAlignment(SwingConstants.CENTER); // centres label according to the width of the label
        // sets region where label should be placed **in pixels**
        score5.setBounds(805, 560, 75, 25);
        jPanel.add(score5); // adds this label to the panel
//
        player1 = new JLabel();
        if (sortedScores.get(0).getKey() == null) player1.setText("");
        else player1.setText(sortedScores.get(0).getKey());


        player1.setFont(new Font(font1.getFontName(), font1.getStyle(), 30));
        player1.setVerticalAlignment(SwingConstants.CENTER); // centres label according to the height of the label
        // sets region where label should be placed **in pixels**
        player1.setBounds(460, 275, 350, 25);
        jPanel.add(player1); // adds this label to the panel

        player2 = new JLabel();
        player2.setText(sortedScores.get(1).getKey());
        player2.setFont(new Font(font1.getFontName(), font1.getStyle(), 30));
        player2.setVerticalAlignment(SwingConstants.CENTER); // centres label according to the height of the label
        // sets region where label should be placed **in pixels**
        player2.setBounds(460, 350, 350, 25);
        jPanel.add(player2); // adds this label to the panel

        player3 = new JLabel();
        player3.setText(sortedScores.get(2).getKey());
        player3.setFont(new Font(font1.getFontName(), font1.getStyle(), 30));
        player3.setVerticalAlignment(SwingConstants.CENTER); // centres label according to the height of the label
        // sets region where label should be placed **in pixels**
        player3.setBounds(460, 420, 350, 25);
        jPanel.add(player3); // adds this label to the panel

        player4 = new JLabel();
        player4.setText(sortedScores.get(3).getKey());
        player4.setFont(new Font(font1.getFontName(), font1.getStyle(), 30));
        player4.setVerticalAlignment(SwingConstants.CENTER); // centres label according to the height of the label
        // sets region where label should be placed **in pixels**
        player4.setBounds(460, 490, 350, 25);
        jPanel.add(player4); // adds this label to the panel

        player5 = new JLabel();
        player5.setText(sortedScores.get(4).getKey());
        player5.setFont(new Font(font1.getFontName(), font1.getStyle(), 30));
        player5.setVerticalAlignment(SwingConstants.CENTER); // centres label according to the height of the label
        // sets region where label should be placed **in pixels**
        player5.setBounds(460, 560, 350, 25);
        jPanel.add(player5); // adds this label to the panel

        clearLeaderboard = new JButton();
        // sets region where label should be placed **in pixels**
        clearLeaderboard.setOpaque(false);
        clearLeaderboard.setBorderPainted(false);
//        clearLeaderboard.setFocusable(false);
//        clearLeaderboard.setFocusPainted(false);
        clearLeaderboard.setBounds(680, 627, 220, 30);
        // this lambda expression detects whether the clear button is pressed or not
        clearLeaderboard.addActionListener(e -> {
            try {LeaderBoardData.clearTheDamnTXTFile();}
            catch (IOException ex) {throw new RuntimeException(ex);}});
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
//        button.addActionListener(e -> {Credits leaderboard = new Credits(); new leaderboard();});
        jPanel.add(button);

        ImageIcon introScreen = new ImageIcon("./res/Miscellaneous/leaderboard_bg.png");
        leaderboardBackground = new JLabel(introScreen);
        leaderboardBackground.setSize(1280,720);
        jPanel.add(leaderboardBackground);

        jFrame.add(jPanel); // add the JPanel to the JFrame to display its contents
    }
}