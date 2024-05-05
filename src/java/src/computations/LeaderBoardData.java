package computations;

import java.io.*;
import java.util.*;

public class LeaderBoardData {
    // linked hash map ensures there is order when writing to the txt file and map list holds the name and score
    private static final LinkedHashMap<String, Integer> scoresLinkedHashMap = new LinkedHashMap<>();
    private static List<Map.Entry<String, Integer>> sortedScores = new ArrayList<>();
    public LinkedHashMap<String, Integer> getScoresLinkedHashMap() {
        return scoresLinkedHashMap;
    }
    public List<Map.Entry<String, Integer>> getSortedScores() {
        return sortedScores;
    }

    public void readAndSort() throws IOException {
        LeaderBoardData leaderBoardData = new LeaderBoardData();
        leaderBoardData.readTXTFile();
        sortedScores = leaderBoardData.sortScores();
    }

    public void readTXTFile() throws IOException { // reads in txt file and saves it to the linked hash map
        File file = new File("scores.txt");
        if (!file.exists()) file.createNewFile();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] column = line.split(",");
            String name = column[0]; // name will always be the 1st column
            int score = Integer.parseInt(column[1]); // score will always be the 2nd column parsed
            scoresLinkedHashMap.put(name, score);
        }
    }

    // I used lists, as they maintain order as they're inserted.
    // lists allow for sorting based on the integer part of the linked-hashmap
    public List<Map.Entry<String, Integer>> sortScores() {
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(scoresLinkedHashMap.entrySet());
        sortedEntries.sort(Comparator.comparingInt(Map.Entry::getValue));
        return sortedEntries;
    }

    public void writeTXTFile(List<Map.Entry<String, Integer>> sortedEntry) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter("scores.txt"))) {
            for (Map.Entry<String, Integer> entry : sortedEntry) printWriter.println(entry.getKey() + "," + entry.getValue());
        }
        catch (IOException e) { System.out.println("Error writing the file!"); }
    }

    public static void storeScore(String name, int score) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("scores.txt", true))) {
            bufferedWriter.write(name + "," + score);
            bufferedWriter.newLine();
        }
        catch (IOException e) {
            System.err.println("Error storing score: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void checkFormat() throws IOException {
        File file = new File("scores.txt");
        if (!file.exists()) file.createNewFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("scores.txt"));
        int numOfLines = 0;
        String line;
        while ((line = bufferedReader.readLine()) != null) if (!line.isEmpty()) numOfLines++;
        bufferedReader.close();
        if (numOfLines < 5) {
            FileWriter fileWriter = new FileWriter("scores.txt", false);
            fileWriter.write("user1,100\nuser2,100\nuser3,100\nuser4,100\nuser5,100\n");
            fileWriter.close();
        }
    }

    public static void clearTheLeaderboard() throws IOException {
        sortedScores.clear();
        FileWriter fileWriter = new FileWriter("scores.txt", false);
        fileWriter.write("user1,100\nuser2,100\nuser3,100\nuser4,100\nuser5,100\n");
        fileWriter.close();
    }
}