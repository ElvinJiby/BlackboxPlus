package main;
// java -jar out/artifacts/Blackbox___Software_Eng__II_Group50__jar/Blackbox---Software-Eng.-II-Group50-.jar
import menus.StartScreen;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new StartScreen(); // Starts the game at the main menu
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Failed to open the Start Screen.", "Start Screen Open Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(-1);
                }
            }
        });
    }
}
