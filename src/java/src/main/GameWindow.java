package main;

import computations.LeaderBoardData;
import menus.HowToPlayWindow;
import menus.StartScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class GameWindow {
    // Application Variables
    private final JFrame gameWindow;
    private final GameScreen gameScreen;
    private final Game game;
    private final JPanel buttonPanel;

    // JLabels and JButton variables
    private JTextField arrowNumberInputField;

    private JLabel arrowNumberInputPrompt;
    private JLabel playerNameLabel;
    private JLabel scoreLabel;
    private JLabel rayStatusLabel;

    private JButton howToPlayButton;
    private JButton endGameButton;

    private final ArrayList<Integer> visitedBoxes = new ArrayList<>();
    private static final Random rand = new Random();
    private int value = 1;
    private static String name = "hello";
    private String lastRayStatus = "normal/deflected";

    public GameWindow(GameScreen gameScreen, Game game) {
        // Window Construction
        this.game = game;
        gameWindow = new JFrame(); // creates a new window
        gameWindow.setSize(1280, 720); // sets the window dimensions
        gameWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // when you click the X to close to program, the program actually closes (by default it just hides the window)
        gameWindow.setLocationRelativeTo(null); // when opened, the window will open in the middle of the screen, instead of the top left
        gameWindow.setResizable(false); // disables the ability to resize the window
        gameWindow.setTitle("Blackbox+ - By Group 50"); // title of the window
        gameWindow.setIconImage(new ImageIcon(getClass().getResource("/Icons/new_icon.png")).getImage());
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
        scoreLabel = new JLabel(" | Score: " + game.getScore() + " | ");
        scoreLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        scoreLabel.setForeground(Color.WHITE);

        arrowNumberInputField = new JTextField(2);
        arrowNumberInputField.setBackground(Color.BLACK);
        arrowNumberInputField.setText("1");
        arrowNumberInputField.setForeground(Color.WHITE);

        arrowNumberInputField.addActionListener(e -> {
            validateInput();
            gameScreen.repaint();
        });

        rayStatusLabel = new JLabel("| No ray has been shot yet.");
        rayStatusLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        rayStatusLabel.setForeground(Color.WHITE);

        playerNameLabel = new JLabel("  | Player Name: " + name);
        playerNameLabel.setForeground(Color.WHITE);
        playerNameLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));

        arrowNumberInputPrompt = new JLabel("| Enter an integer 1-54: ");
        arrowNumberInputPrompt.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        arrowNumberInputPrompt.setForeground(Color.WHITE);

        howToPlayButton = new JButton("How To Play");
        howToPlayButton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        howToPlayButton.setBounds(50, 50, 100, 30);
        howToPlayButton.setFocusable(false);
        howToPlayButton.addActionListener(e -> new HowToPlayWindow());

        endGameButton = new JButton("End Game");
        endGameButton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        endGameButton.setFocusable(false);
        endGameButton.addActionListener(e -> {
            endGameButton.setEnabled(false);
            guessAtomsWindow();
        });

        buttonPanel.add(endGameButton);
        buttonPanel.add(playerNameLabel);
        buttonPanel.add(arrowNumberInputPrompt);
        buttonPanel.add(arrowNumberInputField);
        buttonPanel.add(rayStatusLabel);
        buttonPanel.add(scoreLabel);
        buttonPanel.add(howToPlayButton);
    }

    private void guessAtomsWindow() {
        JFrame jFrame = new JFrame("Enter the box number you think the atom is located in");
        jFrame.setLayout(new FlowLayout());
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(250, 40));

        game.setEnableNumberedBoard(true);
        gameScreen.repaint();

        ArrayList<Integer> guessList = new ArrayList<>();
        AtomicInteger num_of_guesses = new AtomicInteger(game.getNumAtoms());

        JButton submitButton = new JButton("Submit Guess (" + game.getNumAtoms() + " guesses left)");
        submitButton.addActionListener(e -> {
            if (e.getSource() == submitButton) {
                try {
                    int guess = Integer.parseInt(textField.getText());
                    if (guess < 1 || guess > 61) { // validation check
                        throw new IllegalArgumentException("Invalid guess. The boxes are numbered from 1-61");
                    } if (guessList.contains(guess)) { // validation check 2
                        throw new IllegalArgumentException("You have already guessed that box number.");
                    }

                    if (!game.isAtomLocationGuessCorrect(guess)) { // if it's incorrect, increment the amount of wrong guesses
                        game.addIncorrectAtomGuess();
                        JOptionPane.showMessageDialog(null, "Unfortunately your guess was wrong.", "Incorrect Guess", JOptionPane.INFORMATION_MESSAGE);
                    } else { // guess was correct
                        JOptionPane.showMessageDialog(null, "Your guess was correct!.", "Correct Guess", JOptionPane.INFORMATION_MESSAGE);
                    }

                    num_of_guesses.decrementAndGet(); // decrease the amount of guesses the player has
                    guessList.add(guess); // add guess to a list to ensure the same guess isn't made again (especially so that they can't guess the same correct box over and over to reduce points)

                    // Check if there's no more guesses left
                    if (num_of_guesses.get() <= 0) {
                        jFrame.dispose();
                        game.setEnableNumberedBoard(false);
                        game.toggleInternalBoardSetting(); // game is over at this point, allow user to see atoms and rays
                        endGameWindow(); // score window
                    }

                    // Otherwise reset the text and continue
                    submitButton.setText("Submit Guess (" + num_of_guesses + " guesses left)");

                } catch (Exception ex) { // invalid input exception handling
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jFrame.add(submitButton);
        jFrame.add(textField);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public static void getUserNameWindow() {
        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon(new ImageIcon(Game.class.getResource("/Icons/new_icon.png")).getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH));

        while (true) {
            try {
                name = (String) JOptionPane.showInputDialog(frame, "Please enter your name:", "Username", JOptionPane.PLAIN_MESSAGE, icon, null, ("user" + rand.nextInt(99999)));
                if (name.length() > 30) throw new IllegalArgumentException("Name must be within 30 characters.");
                break;
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
        frame.dispose(); // Close the frame after getting the name
    }

    private void endGameWindow() {
        JFrame jFrame = new JFrame("End Game");
        jFrame.setSize(640, 360);
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

        game.setPlayerName(name);
        JTextArea finalScore = getScoreTextArea();

        jFrame.add(finalScore, BorderLayout.CENTER);
        jFrame.setVisible(true);

        LeaderBoardData.storeScore(name, game.getScore());
    }

    private JTextArea getScoreTextArea() {
        JTextArea finalScore = new JTextArea();
        finalScore.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        finalScore.setText(name + " has scored: " + game.getScore() + " points!");

        String scoreMessage = "Number of Markers Used: " + game.getNumMarkersUsed() + " x 1 point = " + game.getNumMarkersUsed() + " points\n"
                + "Number of Incorrect Guesses: " + game.getNumIncorrectGuesses() + " x 5 points = " + (game.getNumIncorrectGuesses()*5) + " points\n"
                + "----------------------------------------------------\n"
                + game.getPlayerName() + " has scored a total of " + game.getScore() + " points!";
        finalScore.setText(scoreMessage);
        finalScore.setEditable(false);
        return finalScore;
    }

    private void validateInput() {
        try {
            value = Integer.parseInt(arrowNumberInputField.getText());
            if (value < 1 || value > 54) {
                JOptionPane.showMessageDialog(null, "Enter a number between 1 and 54 (inclusive)", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                arrowNumberInputField.setText("1"); // Reset to default value
                value = 1;
                rayStatusLabel.setText("Shoot ray from: " + value);
            } else {
                if (visitedBoxes.contains(value)) {
                    JOptionPane.showMessageDialog(null, "This entry already has a ray generated from it", "Input Already Used", JOptionPane.ERROR_MESSAGE);
                    arrowNumberInputField.setText("1"); // Reset to default value
                    value = 1;
                } else {
                    visitedBoxes.add(value);
                    System.out.println("Value: " + value);
                    game.shootRay(value);
                    scoreLabel.setText("| Score: " + game.getScore() + " |  ");
                    rayStatusLabel.setText("| Last ray was " + getLastRayStatus());
                    gameScreen.repaint();
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid integer.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            arrowNumberInputField.setText("1"); // Reset to default value
            rayStatusLabel.setText("Shoot ray from: " + value);
        }
    }


    public Color askMarkerColor() {
        return JColorChooser.showDialog(null, "Choose a colour for the Markers", Color.MAGENTA);
    }

    public void addVisitedBox(int value) {
        if (!visitedBoxes.contains(value)) {
            visitedBoxes.add(value);
        }
    }

    public String getLastRayStatus() {
        return lastRayStatus;
    }

    public void setLastRayStatus(String lastRayStatus) {
        this.lastRayStatus = lastRayStatus;
    }
}
