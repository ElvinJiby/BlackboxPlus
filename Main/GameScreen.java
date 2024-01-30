package Main;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    private Game game;
    private Image bgImage = null;

    public GameScreen(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(1280,720)); // setting the resolution of the game
        bgImage = (new ImageIcon("./res/blackboxbgimagesmallver.jpg")).getImage(); // loads the bg image for the game

        JButton button = new JButton(); // creates a new button
        button.setBounds(640, 100, 500,500); // sets location and size of the button
        button.setText("This is a button"); // sets the text to be displayed on the button
        button.setFocusable(false); // removes weird outline around text on button
        button.setFont(new Font("Comic Sans", 0, 14)); // changes font
        button.addActionListener(e -> { // the action to be performed once the button is clicked
            System.out.println("Button was clicked");
            button.setText("Button clicked.");
            button.setEnabled(false); // disables the button once pressed
        });
        this.add(button); // adding the button to the screen
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null);
    }

    public Game getGame() { return game; } // accessor
}
