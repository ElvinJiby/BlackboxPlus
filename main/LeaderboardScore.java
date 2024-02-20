package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LeaderboardScore {
    private final ArrayList<Integer> scores;

    public LeaderboardScore() throws FileNotFoundException {
        scores = new ArrayList<>();
        getScoresInFile();
    }

    private void getScoresInFile() throws FileNotFoundException {
        String line;
        int score;
        Scanner scanner = new Scanner(new File("leaderboard-scores.txt"));
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            score = Integer.parseInt(line);
            scores.add(score);
        }
    }

    public void addScore(int newScore) {
        scores.add(newScore);
        Collections.sort(scores);
        writeAllScoresToFile();
    }

    private void writeAllScoresToFile() {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter("leaderboard-scores.txt"))) {
            for (int score : scores) printWriter.println(score);
        } catch (IOException e) {
            System.err.println("Cant read leaderboard-scores file WOMP WOMP");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        LeaderboardScore leaderboardScore = new LeaderboardScore();
        leaderboardScore.addScore(100);
    }
}
