package main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class LeaderBoard implements Runnable {
    private static LinkedHashMap<String, Integer> scores = new LinkedHashMap<>();
    private static List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>();

    public List<Map.Entry<String, Integer>> getSortedEntries() {
        return sortedEntries;
    }

    public void setSortedEntries(List<Map.Entry<String, Integer>> sortedEntries) {
        LeaderBoard.sortedEntries = sortedEntries;
    }

    public void setScores(LinkedHashMap<String, Integer> scores) {
        LeaderBoard.scores = scores;
    }

    public LinkedHashMap<String, Integer> getScores() {
        return scores;
    }

    @Override public void run() { jFrame.setVisible(true); }

    // my instance variables, which are later constructed
    // all variables are private to allow for class accessibility
    // final variables allow for blocking further modification to my variables
    private final JFrame jFrame; // app window
    private final JPanel jPanel; // layout within the app window

    private final JLabel leaderboardLabel;
    private final JLabel score1;
    private final JLabel score2;
    private final JLabel score3;
    private final JLabel score4;
    private final JLabel score5;

    private final JButton clearLeaderboard; // when pressed clears the txt file
    private final JButton goBackBruh; // exits the window and goes back to main menu

    public LeaderBoard() throws FileNotFoundException {


//        Leaderboard leaderboard = new Leaderboard();
        LinkedHashMap<String, Integer> linkedHashMap = getScores();
        readTXTFile(linkedHashMap);
        System.out.println(linkedHashMap);
        List<Map.Entry<String, Integer>> sortedScores = getSortedEntries();
        sortedScores = sortScores();
        System.out.println(sortedScores);
        writeTXTFile(sortedScores);

        jFrame = new JFrame("Leaderboard"); // window title
        jFrame.setSize(1280, 720); // has a fixed 720p 16:9 size
        jFrame.setResizable(false); // ensures the app window will always stay at 1280x720 resolution
        jFrame.setLocationRelativeTo(null); // when window is opened initially it is centred

        jPanel = new JPanel(); // creates new JPanel object
        jPanel.setLayout(null); // setting null allows elements to be placed anywhere on the panel
        jPanel.setSize(1280, 720); // sets max region to add elements to panel

        leaderboardLabel = new JLabel("Leaderboard");
        Font font = leaderboardLabel.getFont();
        leaderboardLabel.setFont(new Font(font.getFontName(), font.getStyle(), 40));
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        leaderboardLabel.setBorder(border);
        leaderboardLabel.setVerticalAlignment(SwingConstants.CENTER); // centres label according to the height of the label
        leaderboardLabel.setHorizontalAlignment(SwingConstants.CENTER); // centres label according to the width of the label
        // sets region where label should be placed **in pixels**
        leaderboardLabel.setBounds(490, 40, 300, 50);
        jPanel.add(leaderboardLabel);

        score1 = new JLabel();
        score1.setText("1st : " + sortedEntries);
        score1.setBorder(border);
        score1.setVerticalAlignment(SwingConstants.CENTER); // centres label according to the height of the label
        score1.setHorizontalAlignment(SwingConstants.CENTER); // centres label according to the width of the label
        // sets region where label should be placed **in pixels**
        score1.setBounds(440, 100, 400, 25);
        jPanel.add(score1); // adds this label to the panel

        score2 = new JLabel();
        score2.setText("1st : " + "Player 1" + "100");
        score2.setBorder(border);
        score2.setVerticalAlignment(SwingConstants.CENTER); // centres label according to the height of the label
        score2.setHorizontalAlignment(SwingConstants.CENTER); // centres label according to the width of the label
        // sets region where label should be placed **in pixels**
        score2.setBounds(440, 125, 400, 25);
        jPanel.add(score2); // adds this label to the panel

        score3 = new JLabel();
        score3.setText("1st : " + "Player 1" + "100");
        score3.setBorder(border);
        score3.setVerticalAlignment(SwingConstants.CENTER); // centres label according to the height of the label
        score3.setHorizontalAlignment(SwingConstants.CENTER); // centres label according to the width of the label
        // sets region where label should be placed **in pixels**
        score3.setBounds(440, 150, 400, 25);
        jPanel.add(score3); // adds this label to the panel

        score4 = new JLabel();
        score4.setText("1st : " + "Player 1" + "100");
        score4.setBorder(border);
        score4.setVerticalAlignment(SwingConstants.CENTER); // centres label according to the height of the label
        score4.setHorizontalAlignment(SwingConstants.CENTER); // centres label according to the width of the label
        // sets region where label should be placed **in pixels**
        score4.setBounds(440, 175, 400, 25);
        jPanel.add(score4); // adds this label to the panel

        score5 = new JLabel();
        score5.setText("1st : " + "Player 1" + "100");
        score5.setBorder(border);
        score5.setVerticalAlignment(SwingConstants.CENTER); // centres label according to the height of the label
        score5.setHorizontalAlignment(SwingConstants.CENTER); // centres label according to the width of the label
        // sets region where label should be placed **in pixels**
        score5.setBounds(440, 200, 400, 25);
        jPanel.add(score5); // adds this label to the panel


        clearLeaderboard = new JButton("Clear Leaderboard");
        // sets region where label should be placed **in pixels**
        clearLeaderboard.setBounds(1080, 600, 150, 50);
        // this lambda expression detects whether the clear button is pressed or not
        clearLeaderboard.addActionListener(e -> {
            try {LeaderBoardData.clearTheDamnTXTFile();} // if pressed, clear the txt file
            catch (IOException ex) {throw new RuntimeException(ex);}
        });
        jPanel.add(clearLeaderboard); // add the generate button to the JPanel

        goBackBruh = new JButton("Main Menu"); // Exit button to terminate program
        // sets region where label should be placed **in pixels**
        // this label is 100x50 with the top-left corner going across 377 and 290 down
        goBackBruh.setBounds(100, 600, 100, 50);
        // lambda expression to check when pressed, if-so, exit the program with status 0.
        goBackBruh.addActionListener(e -> System.exit(0));
        jPanel.add(goBackBruh);// add the button to the panel

        jFrame.add(jPanel); // add the JPanel to the JFrame to display its contents

    }

    public void readTXTFile(LinkedHashMap<String, Integer> scoresLinkedHashMap) throws FileNotFoundException { // reads in txt file and saves it to the linked hash map
        Scanner scanner = new Scanner(new File("scores.txt")); // scanner takes in file object
        while (scanner.hasNextLine()) {// keep reading in each string line until the end of the file
            String line = scanner.nextLine(); // save each line in the line variable
            String[] column = line.split(","); // divide this line by comma to get name and score
            String name = column[0]; // name will always be the 1st column
            int score = Integer.parseInt(column[1]); // score will always be the 2nd column parsed
            scoresLinkedHashMap.put(name, score); // add this name and score to the linked hash map
        }
    }

    // I used lists, as they maintain order as they're inserted.
    // lists allow for sorting based on the integer part of the linked-hashmap
    public List<Map.Entry<String, Integer>> sortScores() {// sorts LinkedHashMap by returning a List
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(scores.entrySet()); // Map.Entry ensures key-value pairs are still in the map
        // x and y are the 2 arguments that need to be compared
        sortedEntries.sort((x, y) -> Integer.compare(y.getValue(), x.getValue())); // getValue gets access to argument value and compares y to x to allow for descending order
        return sortedEntries; // return the list as a sorted list
    }

    // this sorted list is then over-written to the txt file
    public void writeTXTFile(List<Map.Entry<String, Integer>> sortedEntry) {// takes in the sorted list as a map
        // FileWriter opens the file and PrintWriter writes to the file
        try (PrintWriter printWriter = new PrintWriter(new FileWriter("scores.txt"))) {
            // the loop goes through each entry in the list
            // it accesses the name and score using entry.getKey and entry.getValue
            // using PrintWriter, it writes to the file, seperated by a comma, for formatting
            for (Map.Entry<String, Integer> entry : sortedEntry) printWriter.println(entry.getKey() + "," + entry.getValue());
        }
        catch (IOException e) {System.out.println("Error writing the file!");}
    }

    public static void clearTheDamnTXTFile() throws IOException {new PrintWriter(new FileWriter("scores.txt"));}

    public static void main(String[] args) throws FileNotFoundException {
        LeaderBoard leaderboard = new LeaderBoard();

        leaderboard.run();
    }
}