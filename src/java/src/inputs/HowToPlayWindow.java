package inputs;

import javax.swing.*;
import java.awt.*;

public class HowToPlayWindow {
    private final JFrame frame;
    private final JPanel panel;
    private final JPanel labelsPanel;
    private final JLabel titleLabel;

    public HowToPlayWindow() {
        frame = new JFrame("My Window");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.BLACK);
        frame.setSize(854, 480);

        panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BorderLayout()); // control layout of labels and panel
        panel.setBorder(BorderFactory.createEmptyBorder(60, 50, 0, 0)); // go down 15px and 20px across

        titleLabel = new JLabel("How to Play:");
        titleLabel.setForeground(Color.RED);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18)); // font formatting
        panel.add(titleLabel, BorderLayout.NORTH); // set panel to the very top

        labelsPanel = new JPanel();
        labelsPanel.setBackground(Color.BLACK);
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS)); // set bounds to verticals
        panel.add(labelsPanel, BorderLayout.WEST); // set to left side

        // instructions
        generateHowToPlayLabel("Black Box is a two-player game where the setter will place 5 to 6 red balls on the board.");
        generateHowToPlayLabel("Rays are sent from any edge of a hexagon and it is your job to find these invisible atoms");
        generateHowToPlayLabel("by observing the reflection patterns when rays are sent");
        generateHowToPlayLabel("\n");
        generateHowToPlayLabel("The atoms affect the path that a ray will take. More atoms = More complex path.");
        generateHowToPlayLabel("Your main objective is to get the atom's places using the least number of rays.");
        generateHowToPlayLabel("The atom’s outer border helps to deflect the rays at 90-degree angles.");
        generateHowToPlayLabel("If a ray collides straight on with the atom, it is reflected back.");
        generateHowToPlayLabel("\n");
        generateHowToPlayLabel("When you think the round is complete, Press the \"End Game\" button.");
        generateHowToPlayLabel("Every ray projected is 1 point, and 5 more points are added for misplaced atoms.");
        generateHowToPlayLabel("For every error made in reporting the result of a ray, your score is reduced by 5 points.");
        generateHowToPlayLabel("After the round ends, the roles are switched with the other player and played again.");
        generateHowToPlayLabel("The player with the least amount of points is the winner.");
        generateHowToPlayLabel("\n");
        generateHowToPlayLabel("To shoot a ray onto the board, use the input field at the bottom to input");
        generateHowToPlayLabel("a number, this number should be between 1 and 54.");
        generateHowToPlayLabel("You will then be asked to choose a colour for the emitted ray.");
        generateHowToPlayLabel("When you have decided that you have finished the round, press \"End game\"");
        generateHowToPlayLabel("\n");
        generateHowToPlayLabel("\n");
        generateHowToPlayLabel("\n");

        frame.add(panel);
        frame.setVisible(true);
    }

    // to efficiently add labels
    private void generateHowToPlayLabel(String s) {
        JLabel jLabel = new JLabel(s); // create new JLabel
        jLabel.setForeground(Color.CYAN);
        jLabel.setFont(new Font("Arial", Font.PLAIN, 12)); // font formatting
        labelsPanel.add(jLabel); // add to the labels panel
    }


    public static void main(String[] args) {
        new HowToPlayWindow();
    }
}
