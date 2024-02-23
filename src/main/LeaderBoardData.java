package main;

import java.io.*;
import java.util.*;

public class LeaderBoardData {
    // linked hash map ensures there is order when writing to the txt file
    // String holds the name and Integer holds the score
    private static final LinkedHashMap<String, Integer> scoresLinkedHashMap = new LinkedHashMap<>();

//    public static void main(String[] args) throws IOException {
//        LeaderBoardData leaderBoardData = new LeaderBoardData();
////        clearTheDamnTXTFile();
//        leaderBoardData.readTXTFile(scoresLinkedHashMap);
//        scoresLinkedHashMap.put("shane", 11);
//        scoresLinkedHashMap.put("elvin", 9);
//        scoresLinkedHashMap.put("ayush", 15);
//        scoresLinkedHashMap.put("alex",17);
//
//        List<Map.Entry<String, Integer>> sortedScores = leaderBoardData.sortScores();
//        System.out.println(sortedScores);
//        leaderBoardData.writeTXTFile(sortedScores);
//    }

    public void readTXTFile(LinkedHashMap<String, Integer> scoresLinkedHashMap) throws FileNotFoundException { // reads in txt file and saves it to the linked hash map
        Scanner scanner = new Scanner(new File("./scores.txt")); // scanner takes in file object
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
        sortedEntries.sort((x, y) -> Integer.compare(y.getValue(), x.getValue())); // getValue gets access to argument value and compares y to x to allow for descending order
        return sortedEntries; // return the list as a sorted list
    }

    // this sorted list is then over-written to the txt file
    public void writeTXTFile(List<Map.Entry<String, Integer>> sortedEntry) {// takes in the sorted list as a map
        // FileWriter opens the file and PrintWriter writes to the file
        try (PrintWriter printWriter = new PrintWriter(new FileWriter("./scores.txt"))) {
            // the loop goes through each entry in the list
            // it accesses the name and score using entry.getKey and entry.getValue
            // using PrintWriter, it writes to the file, seperated by a comma, for formatting
            for (Map.Entry<String, Integer> entry : sortedEntry) printWriter.println(entry.getKey() + "," + entry.getValue());
        }
        catch (IOException e) {System.out.println("Error writing the file!");}
    }

    public static void clearTheDamnTXTFile() throws IOException {new PrintWriter(new FileWriter("./scores.txt"));}
}
