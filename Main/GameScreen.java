package Main;

import Inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class GameScreen extends JPanel {
    private Game game;
    private Image bgImage = null;
    private MouseInputs mouseInputs;
    private ArrayList<JButton> buttonsList;

    public GameScreen(Game game) {
        mouseInputs = new MouseInputs(this);
        this.game = game;
        setPreferredSize(new Dimension(1280,720)); // setting the resolution of the game
        bgImage = (new ImageIcon("./res/blackboxbgimagesmallver.jpg")).getImage(); // loads the bg image for the game
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);


        this.setLayout(null);
        buttonsList = new ArrayList<JButton>();
        addSpotButtons(this);
        for (JButton button : buttonsList) {
            this.add(button);
        }

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
                {410,15}, {595,5}, {637,5}, {842,39}, {911,172}, {958,219}, {1030,366}, {941,498}, {917,558},
                {826,682}, {658,690}, {596,689}, {438,675}, {356,549}, {338,515}, {245,352}, {316,214}, {352,174},
        };

        for (int i = 0; i < buttonPosArray.length; i++) {
            int xPos = buttonPosArray[i][0];
            int yPos = buttonPosArray[i][1];
            int buttonNo = i+1;

            JButton button = new JButton(String.valueOf(buttonNo));
            button.setBounds(xPos, yPos, 50, 20);
            button.setFocusable(false);
            button.addActionListener(e -> {
                System.out.println("Button "+buttonNo+" pressed");
            });


           // button.setText();
            buttonsList.add(button);
//            gameScreen.add(button);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null);
    }

    public Game getGame() { return game; } // accessor
}
