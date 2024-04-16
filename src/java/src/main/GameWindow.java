package main;

import menus.LeaderBoardData;
import menus.StartScreen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class GameWindow {
    private JFrame gameWindow;
    private GameScreen gameScreen;
    private Game game;
    private JPanel buttonPanel;

    private JTextField arrowNumberInputField;
    private JLabel arrowNumberInputPrompt;
//    private JTextField playerNameInputField;
    private JLabel playerNameLabel;
    private JLabel scoreLabel;
    private JLabel resultLabel;
    private int value = 1;
    private JButton howToPlayButton;
    private JButton endGameButton;
    private final ArrayList<Integer> visitedBoxes = new ArrayList<>();

    private static String name = "hello";

    public GameWindow(GameScreen gameScreen, Game game) {
        // Window Construction
        this.game = game;
        gameWindow = new JFrame(); // creates a new window
        gameWindow.setSize(1280, 720); // sets the window dimensions
        gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // when you click the X to close to program, the program actually closes (by default it just hides the window)
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
        scoreLabel = new JLabel("| Score: " + game.getScore());
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

//        playerNameInputField = new JTextField(15);
//        playerNameInputField.setText("Enter player name (Limit: 30)");
////        textField.setBounds(50, 50, 250, 250);
//        playerNameInputField.addActionListener(e -> {
//            updateUsername();
//            gameScreen.repaint();
//        });

//        playerNameLabel = new JLabel("| Player Name: " + game.getPlayerName());
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
        howToPlayButton.addActionListener(e -> howToPlayWindow());

        endGameButton = new JButton("End Game");
        endGameButton.addActionListener(e -> {
            endGameButton.setEnabled(false);
//            guessAtomsWindow();
            game.toggleInternalBoardSetting();
            endGameWindow();
        });

        buttonPanel.add(endGameButton);
        buttonPanel.add(howToPlayButton);
//        buttonPanel.add(playerNameInputField);
        buttonPanel.add(playerNameLabel);
        buttonPanel.add(arrowNumberInputPrompt);
        buttonPanel.add(arrowNumberInputField);
        buttonPanel.add(resultLabel);
        buttonPanel.add(scoreLabel);
//        buttonPanel.add(button);
//        buttonPanel.add(loadPresetRay);
//        buttonPanel.add(colorWindow);
    }

    private void guessAtomsWindow() {
        JFrame jFrame = new JFrame("Enter the box number you think the atom is located in");
        jFrame.setLayout(new FlowLayout());
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(250,40));

        JButton submitButton = new JButton("Submit Guess ("+game.getNumAtoms()+" guesses left)");
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
        ImageIcon icon = new ImageIcon(new ImageIcon("src/java/resources/Icons/new_icon.png").getImage().getScaledInstance(125,125, Image.SCALE_SMOOTH));
        name = (String) JOptionPane.showInputDialog(frame, "Please enter your name:", "Username", JOptionPane.PLAIN_MESSAGE, icon, null, "user12345");
        try {
            if (name.length() > 30) throw new IllegalArgumentException("Name must be less than 30 characters.");
        }
        catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        finally {
            frame.dispose(); // Close the frame after getting the name
            frame.setVisible(false);
        }
    }

    private void endGameWindow() {
        JFrame jFrame = new JFrame("End Game");
        jFrame.setSize(426, 240);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.addWindowListener(new WindowAdapter() { @Override public void windowClosing(WindowEvent e) {
            gameWindow.dispose();
            jFrame.dispose();
            new StartScreen(); } });

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
            value = Integer.parseInt(arrowNumberInputField.getText());
            if (value < 1 || value > 54) {
                JOptionPane.showMessageDialog(null, "Enter a number between 1 and 54 (inclusive)", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                arrowNumberInputField.setText("1"); // Reset to default value
                value = 1;
                resultLabel.setText("Shoot ray from: " + value);
            } else {
                if (visitedBoxes.contains(value)) {
                    JOptionPane.showMessageDialog(null, "This entry already has a ray generated from it", "Input Already Used", JOptionPane.ERROR_MESSAGE);
                    arrowNumberInputField.setText("1"); // Reset to default value
                    value = 1;
                } else {
                    visitedBoxes.add(value);
                    System.out.println("Value: "+value);
                    game.shootRay(value);
                    gameScreen.repaint();
                }
                resultLabel.setText("Shoot ray from: " + value);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid integer.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            arrowNumberInputField.setText("1"); // Reset to default value
            resultLabel.setText("Shoot ray from: " + value);
        }
    }

//    private void updateUsername() {
//        game.setPlayerName(playerNameInputField.getText());
//        if (game.getPlayerName().length() > 30) {
//            JOptionPane.showMessageDialog(null, "Username must be 30 characters or less.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
//        } else {
//            playerNameLabel.setText(game.getPlayerName());
//            playerNameInputField.setVisible(false);
//            gameScreen.repaint();
//        }
//    }


    public void howToPlayWindow() {
        JFrame jFrame = new JFrame();
        jFrame.setTitle("How to Play Black Box");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(854, 480));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setText("\tBlack Box Instructions:\n\n" +
                "\tBlack Box is a two-player game that consists of the setter and the experimenter. " +
                "For the hexagonal version of Black Box, the setter will place 5 to 6 red balls on the board. " +
                "Rays are sent from any outer edge of the entire hexagonal structure, and it is the experimenter's job " +
                "to find these atoms which are invisible to him by observing the reflection patterns when rays are sent.\n\n" +

                "\tThe atoms affect the path that a ray will take. Complex paths would be expected when using more balls. " +
                "This should be the main objective for the setter. The main objective for the experimenter is to get the " +
                "positions of the atoms on the board using the least number of rays.\n\n" +

                "\tWhen rays are projected onto the board, the atom’s outer border helps to deflect the rays at 90-degree angles. " +
                "If a ray collides straight on with the atom, it is reflected back.\n\n" +

                "\tWhen the experimenter thinks his round of the game is complete, it is announced. Every ray projected is 1 point, " +
                "and 5 more points are added for misplaced atoms. For every error made in reporting the result of a ray, " +
                "the experimenter's score is reduced by 5 points.\n\n" +

                "\tAfter the round ends, the roles are switched and played again. The player with the least points is the winner.");
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        mainPanel.add(textArea, BorderLayout.CENTER);
        jFrame.add(mainPanel);

        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public Color askMarkerColor() {
        return JColorChooser.showDialog(null, "Choose a colour for the Markers", Color.WHITE);
    }
}
