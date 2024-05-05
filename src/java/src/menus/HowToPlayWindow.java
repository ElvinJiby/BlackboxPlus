package menus;

import main.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class HowToPlayWindow {
    private final JPanel rulesPanel; // to put how to play labels
    private static final Image gameIcon = new ImageIcon(Objects.requireNonNull(GameWindow.class.getResource("/Icons/new_icon.png"))).getImage();


    public HowToPlayWindow() {
        JFrame jFrame = new JFrame("How to Play Blackbox+");
        jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setIconImage(gameIcon);
        jFrame.setSize(854, 480);

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.BLACK);
        jPanel.setLayout(new BorderLayout());
        jPanel.setBorder(BorderFactory.createEmptyBorder(60, 50, 0, 0));

        JLabel titleLabel = new JLabel("How to Play:");
        titleLabel.setForeground(Color.RED);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        jPanel.add(titleLabel, BorderLayout.NORTH);

        rulesPanel = new JPanel();
        rulesPanel.setBackground(Color.BLACK);
        rulesPanel.setLayout(new BoxLayout(rulesPanel, BoxLayout.Y_AXIS));
        jPanel.add(rulesPanel, BorderLayout.WEST);

        generateHowToPlayLabel("Black Box is a two-player game where the setter will place 6 red balls on the board.");
        generateHowToPlayLabel("Rays are sent from any edge of a hexagon and it is your job to find these invisible atoms");
        generateHowToPlayLabel("by observing the reflection patterns when rays are sent");
        generateHowToPlayLabel("\n");
        generateHowToPlayLabel("The atoms affect the path that a ray will take. More atoms = More complex path.");
        generateHowToPlayLabel("Your main objective is to get the atom's places using the least number of rays.");
        generateHowToPlayLabel("The atom’s outer border helps to deflect the rays at 90-degree angles.");
        generateHowToPlayLabel("If a ray collides straight on with the atom, it is reflected back.");
        generateHowToPlayLabel("\n");
        generateHowToPlayLabel("When you think the round is complete, Press the \"End Game\" button.");
        generateHowToPlayLabel("Every marker placed is 1 point to the score, and 5 more points are added for misplaced atoms.");
        generateHowToPlayLabel("After the round ends, the score is stored in the leaderboard.");
        generateHowToPlayLabel("The player with the least amount of points is the winner.");
        generateHowToPlayLabel("\n");
        generateHowToPlayLabel("To shoot a ray onto the board, use the input field at the bottom to input");
        generateHowToPlayLabel("a number, this number should be between 1 and 54 inclusive.");
        generateHowToPlayLabel("You will then be asked to choose a colour for the marker, if its ray is not absorbed or reflected.");
        generateHowToPlayLabel("Reflected rays will automatically place a white marker. Absorbed rays will automatically place a dark gray marker.");
        generateHowToPlayLabel("When you have decided that you have finished the round, press \"End game\"");
        generateHowToPlayLabel("\n");
        generateHowToPlayLabel("\n");
        generateHowToPlayLabel("\n");

        jFrame.add(jPanel);
        jFrame.setVisible(true);
    }

    private void generateHowToPlayLabel(String s) { // to efficiently add labels
        JLabel jLabel = new JLabel(s);
        jLabel.setForeground(Color.CYAN);
        jLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        rulesPanel.add(jLabel);
    }
}
