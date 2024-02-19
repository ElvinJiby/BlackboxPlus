package main;

import java.io.*;
import java.util.*;

public class LeaderboardName {
    private final ArrayList<String> names;

    public LeaderboardName() throws FileNotFoundException {
        names = new ArrayList<>();
        getNamesInFile();
    }

    private void getNamesInFile() throws FileNotFoundException {
        String line;
        Scanner scanner = new Scanner(new File("leaderboard-names.txt"));
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            names.add(line);
        }
    }

    public void addName(String newName) {
        names.add(newName);
        Collections.sort(names);
        writeAllNamesToFile();
    }

    private void writeAllNamesToFile() {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter("leaderboard-names.txt"))) {
            for (String name : names) printWriter.println(name);
        } catch (IOException e) {
            System.err.println("Cant read leaderboard-names file WOMP WOMP");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        LeaderboardName leaderboardName = new LeaderboardName();
        leaderboardName.addName("Cornelius");
    }
}
