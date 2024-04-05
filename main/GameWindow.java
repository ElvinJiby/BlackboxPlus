package main;

import menus.LeaderBoardData;
import menus.StartScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow {
    private JFrame gameWindow;
    private GameScreen gameScreen;
    private Game game;

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
    private JButton howToPlayButton;
    private JButton endGameButton;

    public GameWindow(GameScreen gameScreen, Game game) {
        // Window Construction
        this.game = game;
        gameWindow = new JFrame(); // creates a new window
        gameWindow.setSize(1280, 720); // sets the window dimensions
        gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // when you click the X to close to program, the program actually closes (by default it just hides the window)
        gameWindow.setLocationRelativeTo(null); // when opened, the window will open in the middle of the screen, instead of the top left
        gameWindow.setResizable(false); // disables the ability to resize the window
        gameWindow.setTitle("Blackbox+ - By Group 50"); // title of the window
        gameWindow.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("./res/Icons/new_icon.png")).getImage());
        gameWindow.setLayout(new BorderLayout());

        // Game Panel (manages the rendering of images/rays/assets/etc.)
        this.gameScreen = gameScreen;
        try {
            gameWindow.add(gameScreen, BorderLayout.CENTER); // adding the screen to the window (the screen pretty much contains the game/visuals)
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Button Panel (manages all the button/text input guis)
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
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
        scoreLabel = new JLabel("| Score: " + score);
        scoreLabel.setForeground(Color.WHITE);
//        scoreLabel.setBounds(1100, 650, 150, 30);

        arrowNumberInputField = new JTextField(2);
        arrowNumberInputField.setText("1");
//        textField.setBounds(50, 50, 250, 250);
        arrowNumberInputField.addActionListener(e -> {
            validateInput();
            gameScreen.repaint();
        });

        resultLabel = new JLabel("| Shoot ray from: " + value);
        resultLabel.setForeground(Color.WHITE);

        playerNameInputField = new JTextField(15);
        playerNameInputField.setText("Enter player name (Limit: 30)");
//        textField.setBounds(50, 50, 250, 250);
        playerNameInputField.addActionListener(e -> {
            updateUsername();
            gameScreen.repaint();
        });

        playerNameLabel = new JLabel("| Player Name: " + playerName);
        playerNameLabel.setForeground(Color.WHITE);

        arrowNumberInputPrompt = new JLabel("| Enter an integer 1-59: ");
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
                Color color = JColorChooser.showDialog(null, "Choose a colour!", Color.WHITE);
                colorWindow.setForeground(color);
                gameScreen.repaint();
            }
        });

        JButton loadPresetRay = new JButton("Load Preset Ray");
        loadPresetRay.addActionListener(e -> {
            if (e.getSource() == loadPresetRay) {
                game.loadPresetRayPath();
                gameScreen.repaint();
            }
        });

        howToPlayButton = new JButton("How To Play");
        howToPlayButton.setBounds(50, 50, 100, 30);
        howToPlayButton.addActionListener(e -> howToPlayWindow());

        endGameButton = new JButton("End Game");
        endGameButton.addActionListener(e -> endGameWindow());

        buttonPanel.add(endGameButton);
        buttonPanel.add(howToPlayButton);
        buttonPanel.add(playerNameInputField);
        buttonPanel.add(playerNameLabel);
        buttonPanel.add(arrowNumberInputPrompt);
        buttonPanel.add(arrowNumberInputField);
        buttonPanel.add(resultLabel);
        buttonPanel.add(scoreLabel);
        buttonPanel.add(button);
        buttonPanel.add(loadPresetRay);
        buttonPanel.add(colorWindow);
    }

    private void endGameWindow() {
        JFrame jFrame = new JFrame("End Game");
        jFrame.setSize(854, 480);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.addWindowListener(new WindowAdapter() { @Override public void windowClosing(WindowEvent e) { new StartScreen(); } });

        JLabel finalScore = new JLabel("User " + playerName + " has scored: " + score + " points");

        jFrame.add(finalScore);
        jFrame.setVisible(true);

        LeaderBoardData.storeScore(playerName, score);
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
        if (playerName.length() > 30) {
            JOptionPane.showMessageDialog(null, "Username must be 30 characters or less.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } else {
            playerNameLabel.setText(playerName);
            playerNameInputField.setVisible(false);
            gameScreen.repaint();
        }
    }

    public static void howToPlayWindow() {
        JFrame jFrame = new JFrame("New Window");
        jFrame.setSize(854, 480);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
            }
        });
        jFrame.setVisible(true);
    }
}
