package computations;

import java.io.*;
import java.util.*;

public class LeaderBoardData {
    // linked hash map ensures there is order when writing to the txt file and map list holds the name and score
    private static final LinkedHashMap<String, Integer> scoresLinkedHashMap = new LinkedHashMap<>();
    private static List<Map.Entry<String, Integer>> sortedScores = new ArrayList<>();
    // getter methods
    public LinkedHashMap<String, Integer> getScoresLinkedHashMap() {
        return scoresLinkedHashMap;
    }
    public List<Map.Entry<String, Integer>> getSortedScores() {
        return sortedScores;
    }

    public void readAndSort() throws IOException {
        LeaderBoardData leaderBoardData = new LeaderBoardData(); // create new instance
        leaderBoardData.readTXTFile(); // read text file
        sortedScores = leaderBoardData.sortScores(); // sort scores and save to map
    }

    public void readTXTFile() throws IOException { // reads in txt file and saves it to the linked hash map
        File file = new File("scores.txt"); // open file
        if (!file.exists()) file.createNewFile(); // make new file if not there
        Scanner scanner = new Scanner(file); // scanner takes in file object
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
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(scoresLinkedHashMap.entrySet()); // Map.Entry ensures key-value pairs are still in the map
        // x and y are the 2 arguments that need to be compared
        sortedEntries.sort(Comparator.comparingInt(Map.Entry::getValue)); // getValue gets access to argument value and compares y to x to allow for descending order
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
        catch (IOException e) { System.out.println("Error writing the file!"); }
    }

    // write the scores to txt file
    public static void storeScore(String name, int score) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("scores.txt", true))) { // append score to file
            bufferedWriter.write(name + "," + score); // write name and score seperated by commas
            bufferedWriter.newLine(); // \n
        }
        catch (IOException e) {
            System.err.println("Error storing score: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void checkFormat() throws IOException {
        File file = new File("scores.txt"); // new file object
        if (!file.exists()) file.createNewFile(); // make new file if not there
        BufferedReader bufferedReader = new BufferedReader(new FileReader("scores.txt")); // reader object
        int numOfLines = 0;
        String line;
        // check if the line is not empty
        while ((line = bufferedReader.readLine()) != null) if (!line.isEmpty()) numOfLines++; // increment numOfLines until EOF
        bufferedReader.close(); // close object
        // if there are not 5 players, label has to be default values
        if (numOfLines < 5) {
            FileWriter fileWriter = new FileWriter("scores.txt", false); // do not append
            fileWriter.write("user1,100\nuser2,100\nuser3,100\nuser4,100\nuser5,100\n"); // defaults
            fileWriter.close();
        }
    }

    public static void clearTheLeaderboard() throws IOException {
        sortedScores.clear();
        FileWriter fileWriter = new FileWriter("scores.txt", false); // do not append
        // fill with default values
        fileWriter.write("user1,100\nuser2,100\nuser3,100\nuser4,100\nuser5,100\n");
        fileWriter.close();
    }
}