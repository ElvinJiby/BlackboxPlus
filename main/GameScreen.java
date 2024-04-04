package main;

import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    // Application Variables
    private final Game game;
    private final MouseInputs mouseInputs;

    public GameScreen(Game game) {
        this.game = game;

        mouseInputs = new MouseInputs(this); // add mouse input support
        addMouseListener(mouseInputs); // needed to perform mouse actions

        setPreferredSize(new Dimension(1280, 720)); // setting the resolution of the game
        setBackground(Color.BLACK);



        setVisible(true);
    }

    private void addSpotButtons(GameScreen gameScreen) {
        int[][] buttonPosArray = new int[][]{
                {425, 39}, {603, 18}, {665, 25}, {842, 39}, {911, 172}, {958, 219}, {1030, 366}, {941, 498}, {917, 558},
                {826, 682}, {658, 690}, {596, 689}, {438, 675}, {356, 549}, {338, 515}, {245, 352}, {316, 214}, {352, 174},
        };

        for (int i = 0; i < buttonPosArray.length; i++) {
            int xPos = buttonPosArray[i][0];
            int yPos = buttonPosArray[i][1];

            JButton button = new JButton();
            button.setBounds(xPos, yPos, 50, 50);
            button.setFocusable(false);

            int buttonNo = i + 1;
            button.setText(String.valueOf(buttonNo));
            gameScreen.add(button);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }



    public Game getGame() {
        return game;
    } // accessor
}
