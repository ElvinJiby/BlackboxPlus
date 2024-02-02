package Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GameScreen extends JPanel {
    private Game game;
    private Image bgImage = null;

    public GameScreen(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(1280,720)); // setting the resolution of the game
        bgImage = (new ImageIcon("./res/blackboxbgimagesmallver.jpg")).getImage(); // loads the bg image for the game

        this.setLayout(null);
        addSpotButtons(this);

//        JButton button = new JButton(); // creates a new button
//        button.setBounds(640, 360, 50,50); // sets location and size of the button
//        button.setText("1"); // sets the text to be displayed on the button
//        button.setFocusable(false); // removes weird outline around text on button
//        button.setFont(new Font("Comic Sans", 0, 14)); // changes font
//        button.addActionListener(e -> { // the action to be performed once the button is clicked
//            System.out.println("Button was clicked");
//            button.setText("Button clicked.");
//            button.setEnabled(false); // disables the button once pressed
//        });
//        this.add(button); // adding the button to the screen
    }

    private void addSpotButtons(GameScreen gameScreen) {
        int[][] buttonPosArray = new int[][]{
                {425,39}, {603,18}, {665,25}, {842,39}, {911,172}, {958,219}, {1030,366}, {941,498}, {917,558},
                {826,682}, {658,690}, {596,689}, {438,675}, {356,549}, {338,515}, {245,352}, {316,214}, {352,174},
        };

        for (int i = 0; i < buttonPosArray.length; i++) {
            int xPos = buttonPosArray[i][0];
            int yPos = buttonPosArray[i][1];

            JButton button = new JButton();
            button.setBounds(xPos, yPos, 50, 50);
            button.setFocusable(false);

            int buttonNo = i+1;
            button.setText(String.valueOf(buttonNo));
            gameScreen.add(button);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null);
    }

    public Game getGame() { return game; } // accessor
}
