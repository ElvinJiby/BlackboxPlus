package inputs;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class HowToPlayWindow {
    private final JFrame jFrame; // frame
    private final JPanel jPanel; // area
    private final JLabel titleLabel; // title
    private final JPanel rulesPanel; // to put how to play labels

    public HowToPlayWindow() {
        jFrame = new JFrame("How to Play Blackbox+"); // title
        jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // operation when closed
//        frame.setLocationRelativeTo(null); // centre window
        jFrame.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Icons/new_icon.png"))).getImage()); // app icon
        jFrame.setSize(854, 480); // window size

        jPanel = new JPanel(); // area to hold labels
        jPanel.setBackground(Color.BLACK); // black background
        jPanel.setLayout(new BorderLayout()); // control layout of labels and panel
        jPanel.setBorder(BorderFactory.createEmptyBorder(60, 50, 0, 0)); // go down 15px and 20px across

        titleLabel = new JLabel("How to Play:"); // title
        titleLabel.setForeground(Color.RED); // title colour
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18)); // font formatting
        jPanel.add(titleLabel, BorderLayout.NORTH); // set panel to the very top

        rulesPanel = new JPanel(); // stores every line of how to play
        rulesPanel.setBackground(Color.BLACK); // black background
        rulesPanel.setLayout(new BoxLayout(rulesPanel, BoxLayout.Y_AXIS)); // set bounds to verticals
        jPanel.add(rulesPanel, BorderLayout.WEST); // set to left side

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

        jFrame.add(jPanel); // add panel to frame
        jFrame.setVisible(true); // visibility
    }

    private void generateHowToPlayLabel(String s) { // to efficiently add labels
        JLabel jLabel = new JLabel(s); // create new JLabel
        jLabel.setForeground(Color.CYAN); // text colour
        jLabel.setFont(new Font("Arial", Font.PLAIN, 12)); // font formatting
        rulesPanel.add(jLabel); // add to the labels panel
    }
}
