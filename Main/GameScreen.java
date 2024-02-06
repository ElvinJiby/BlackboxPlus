package Main;

import Inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameScreen extends JPanel {
    private Game game;
    private Image bgImage = null;
    private MouseInputs mouseInputs;
    private ArrayList<JButton> buttonsList;

    public GameScreen(Game game) {
        mouseInputs = new MouseInputs(this);
        this.game = game;
        setPreferredSize(new Dimension(1280,720)); // setting the resolution of the game
        bgImage = (new ImageIcon("./res/BBDE35F2-A057-46C0-9171-EA93FC0794D1.jpeg")).getImage(); // loads the bg image for the game
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

//        this.setLayout(null);
//        buttonsList = new ArrayList<JButton>();
//        addSpotButtons(this);
//        for (JButton button : buttonsList) {
//            this.add(button);
//        }

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
            g.drawImage(bgImage,0,0,1280,720,null);
    }

    public Game getGame() { return game; } // accessor
}
