package main;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private JFrame gameWindow;
    private JPanel buttonPanel;
    private JTextField arrowNumberInputField;
    private JTextField playerNameInputField;
    private JLabel resultLabel;
    private JLabel playerNameLabel;
    private JLabel scoreLabel;
    private JLabel arrowNumberInputPrompt;
    private final int score = 100;
    private int value = 1;
    private String playerName = "user34567";
    private ImageIcon imageIcon;

    public GameWindow(GameScreen gameScreen) {
        // Window Construction
        gameWindow = new JFrame(); // creates a new window
        gameWindow.setSize(1280,720); // sets the window dimensions
        gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // when you click the X to close to program, the program actually closes (by default it just hides the window)
        gameWindow.setLocationRelativeTo(null); // when opened, the window will open in the middle of the screen, instead of the top left
        gameWindow.setResizable(false); // disables the ability to resize the window
        gameWindow.setTitle("Blackbox+ - By Group 50"); // title of the window
        gameWindow.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("./res/Icons/new_icon.png")).getImage());
        gameWindow.setLayout(new BorderLayout());

        // Game Panel (manages the rendering of images/rays/assets/etc.)
        try {
            gameWindow.add(gameScreen, BorderLayout.CENTER); // adding the screen to the window (the screen pretty much contains the game/visuals)
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Button Panel (manages all the button/text input guis)
        buttonPanel = new JPanel();
        createLabels(buttonPanel); // creates all the buttons/clickable elements and adds it to buttonPanel
        try {
            gameWindow.add(buttonPanel, BorderLayout.NORTH);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        gameWindow.pack(); // creates the window with its components according to a specific resolution
        gameWindow.setVisible(true); // visibility option to actually see the window
    }

    private void createLabels(JPanel buttonPanel) {
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setForeground(Color.WHITE);
//        scoreLabel.setBounds(1100, 650, 150, 30);

        arrowNumberInputField = new JTextField(10);
        arrowNumberInputField.setText("1");
//        textField.setBounds(50, 50, 250, 250);
        arrowNumberInputField.addActionListener(e -> validateInput());

        resultLabel = new JLabel("Shoot ray from: " + value);
        resultLabel.setForeground(Color.WHITE);

        playerNameInputField = new JTextField(20);
        playerNameInputField.setText("user34567");
//        textField.setBounds(50, 50, 250, 250);
        playerNameInputField.addActionListener(e -> updateUsername());

        playerNameLabel = new JLabel("Player Name: " + playerName);
        playerNameLabel.setForeground(Color.WHITE);

        arrowNumberInputPrompt = new JLabel("Enter an integer 1-59: ");
        arrowNumberInputPrompt.setForeground(Color.WHITE);

        JLabel colorWindow = new JLabel();
        colorWindow.setBackground(Color.WHITE);
        colorWindow.setText("Sample Text");
        colorWindow.setFont(new Font("Comic Sans", Font.BOLD, 20));
        colorWindow.setOpaque(true);

        JButton button = new JButton("Pick a colour");
        button.addActionListener(e -> {
            if (e.getSource() == button) {
                JColorChooser colorChooser = new JColorChooser();

                Color color = JColorChooser.showDialog(null, "Choose a colour!", Color.BLACK);
                colorWindow.setForeground(color);
            }
        });

        buttonPanel.add(playerNameInputField);
        buttonPanel.add(playerNameLabel);
        buttonPanel.add(arrowNumberInputPrompt);
        buttonPanel.add(arrowNumberInputField);
        buttonPanel.add(resultLabel);
        buttonPanel.add(scoreLabel);
        buttonPanel.add(button);
        buttonPanel.add(colorWindow);
    }

    private void validateInput() {
        try {
            value = Integer.parseInt(arrowNumberInputField.getText());
            if (value < 1 || value > 59) {
                JOptionPane.showMessageDialog(null, "Enter a number between 1 and 59", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                arrowNumberInputField.setText("1"); // Reset to default value
                value = 1;
                resultLabel.setText("Shoot ray from: " + value);
            } else resultLabel.setText("Shoot ray from: " + value);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid integer.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            arrowNumberInputField.setText("1"); // Reset to default value
            resultLabel.setText("Shoot ray from: " + value);
        }
    }

    private void updateUsername() {
        playerName = playerNameInputField.getText();
        playerNameLabel.setText(playerName);
    }
}
