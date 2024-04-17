package main;

import inputs.HowToPlayWindow;
import inputs.LeaderBoardData;
import menus.StartScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Objects;

public class GameWindow {
    private final JFrame gameWindow;
    private final GameScreen gameScreen;
    private final Game game;
    private final JPanel buttonPanel;

    private JTextField arrowNumberInputField;
    private JLabel arrowNumberInputPrompt;
    private JLabel playerNameLabel;
    private JLabel scoreLabel;
    private JLabel resultLabel;
    private int shootFrom = 1;
    private JButton howToPlayButton;
    private JButton endGameButton;

    private final ArrayList<Integer> visitedBoxes = new ArrayList<>();

    private static String name = "hello";

    public GameWindow(GameScreen gameScreen, Game game) {
        this.game = game;
        gameWindow = new JFrame(); // creates a new window
        gameWindow.setSize(1280, 720); // sets the window dimensions
        gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // when you click the X to close to program, the program actually closes (by default it just hides the window)
        gameWindow.setLocationRelativeTo(null); // when opened, the window will open in the middle of the screen, instead of the top left
        gameWindow.setResizable(false); // disables the ability to resize the window
        gameWindow.setTitle("Blackbox+ - By Group 50"); // title of the window
        gameWindow.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Icons/new_icon.png"))).getImage());
        gameWindow.setLayout(new BorderLayout());

        // Game Panel (manages the rendering of images/rays/assets/etc.)
        this.gameScreen = gameScreen;
        getUserNameWindow();
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
            gameWindow.add(buttonPanel, BorderLayout.SOUTH);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        gameWindow.pack(); // creates the window with its components according to a specific resolution
        gameWindow.setVisible(true); // visibility option to actually see the window
    }

    private void createLabels(JPanel buttonPanel) {
        scoreLabel = new JLabel("| Score: " + game.getScore());
        scoreLabel.setForeground(Color.WHITE);

        arrowNumberInputField = new JTextField(2);
        arrowNumberInputField.setText("1");
        arrowNumberInputField.addActionListener(e -> {
            validateInput();
            gameScreen.repaint();
        });

        resultLabel = new JLabel("| Shoot ray from: " + shootFrom);
        resultLabel.setForeground(Color.WHITE);

        playerNameLabel = new JLabel("| Player Name: " + name);
        playerNameLabel.setForeground(Color.WHITE);

        arrowNumberInputPrompt = new JLabel("| Enter an integer 1-54: ");
        arrowNumberInputPrompt.setForeground(Color.WHITE);

        JLabel colorWindow = new JLabel();
        colorWindow.setBackground(Color.WHITE);
        colorWindow.setText("Sample Text");
        colorWindow.setFont(new Font("Comic Sans", Font.BOLD, 20));
        colorWindow.setOpaque(true);

        JButton button = new JButton("Pick a colour");
        button.addActionListener(e -> {
            if (e.getSource() == button) {
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
        howToPlayButton.addActionListener(e -> new HowToPlayWindow());

        endGameButton = new JButton("End Game");
        endGameButton.addActionListener(e -> {
            endGameButton.setEnabled(false);
//            guessAtomsWindow();
            game.toggleInternalBoardSetting();
            new GameOver();
        });

        buttonPanel.add(endGameButton);
        buttonPanel.add(howToPlayButton);
        buttonPanel.add(playerNameLabel);
        buttonPanel.add(arrowNumberInputPrompt);
        buttonPanel.add(arrowNumberInputField);
        buttonPanel.add(resultLabel);
        buttonPanel.add(scoreLabel);
    }

    private void guessAtomsWindow() {
        JFrame jFrame = new JFrame("Enter the box number you think the atom is located in");
        jFrame.setLayout(new FlowLayout());
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(250, 40));

        JButton submitButton = new JButton("Submit Guess (" + game.getNumAtoms() + " guesses left)");
        submitButton.addActionListener(e -> {
            if (e.getSource() == submitButton) {
                int guess;
                int num_of_guesses = game.getNumAtoms();

                while (num_of_guesses > 0) {
                    try {
                        guess = Integer.parseInt(textField.getText());
                        num_of_guesses--;
                        if (!game.isAtomLocationGuessCorrect(guess)) {
                            game.addIncorrectAtomGuess();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "That's not a valid input.", "Invalid Guess", JOptionPane.ERROR_MESSAGE);
                    }
                }

                jFrame.dispose();
            }
        });

        jFrame.add(submitButton);
        jFrame.add(textField);
        jFrame.pack();
        jFrame.setVisible(true);

    }

    public static void getUserNameWindow() {
        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon(new ImageIcon("src/java/resources/Icons/new_icon.png").getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH));
        name = (String) JOptionPane.showInputDialog(frame, "Please enter your name:", "Username", JOptionPane.PLAIN_MESSAGE, icon, null, "user12345");
        try {
            if (name.length() > 30) throw new IllegalArgumentException("Name must be less than 30 characters.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally {
            frame.dispose(); // Close the frame after getting the name
            frame.setVisible(false);
        }
    }

    private void endGameWindow() {
        JFrame jFrame = new JFrame("End Game");
        jFrame.setSize(426, 240);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gameWindow.dispose();
                jFrame.dispose();
                new StartScreen();
            }
        });

        JLabel finalScore = new JLabel();
        finalScore.setFont(new Font("Verdana", Font.BOLD, 20));
        finalScore.setText(name + " has scored: " + game.getScore() + " points!");

        finalScore.setHorizontalAlignment(SwingConstants.CENTER);

        jFrame.add(finalScore, BorderLayout.CENTER);
        jFrame.setVisible(true);

        LeaderBoardData.storeScore(name, game.getScore());
    }

    private void validateInput() {
        try {
            shootFrom = Integer.parseInt(arrowNumberInputField.getText()); // parse input
            if (shootFrom < 1 || shootFrom > 54) { // if input is not between specified values, display error message
                JOptionPane.showMessageDialog(null, "Enter a number between 1 and 54 (inclusive)", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                arrowNumberInputField.setText("1"); // Reset to default value
                shootFrom = 1; // reset value
                resultLabel.setText("Shoot ray from: " + shootFrom);
            } else {
                if (visitedBoxes.contains(shootFrom)) { // if already entered
                    JOptionPane.showMessageDialog(null, "This entry already has a ray generated from it", "Input Already Used", JOptionPane.ERROR_MESSAGE);
                    arrowNumberInputField.setText("1"); // Reset to default value
                    shootFrom = 1;
                } else { // not used input
                    visitedBoxes.add(shootFrom);
                    System.out.println("Value: " + shootFrom);
                    game.shootRay(shootFrom);
                    gameScreen.repaint();
                }
                resultLabel.setText("Shoot ray from: " + shootFrom);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid integer.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            arrowNumberInputField.setText("1"); // Reset to default value
            resultLabel.setText("Shoot ray from: " + shootFrom);
        }
    }

    public Color askMarkerColor() {
        return JColorChooser.showDialog(null, "Choose a colour for the Markers", Color.WHITE);
    }
}
