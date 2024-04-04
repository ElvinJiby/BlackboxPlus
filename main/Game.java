package main;

import entities.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    // Application Variables
    private GameWindow gameWindow;
    private GameScreen gameScreen;

    // Game Variables
    private final int NUM_OF_ATOMS = 6;
    private static Image bgImage = (new ImageIcon(Game.class.getClassLoader().getResource("res/Board Layouts/transparent-numbered-all.PNG")).getImage());
    private static Image boardCover = (new ImageIcon(Game.class.getClassLoader().getResource("res/Board Layouts/transparent-numbered-background.PNG")).getImage());
    private final Boolean seeAtomsandRays = false; // debug setting to show internal atoms
    private final ArrayList<HexagonalBox> hexagonalBoxes; // Arraylist that contains all the hexagonal boxes
    private final ArrayList<Atom> atomList; // Arraylist that contains all the atoms
    private ArrayList<Color> markerColourList = new ArrayList<>();
    private ArrayList<ArrayList<Ray>> rayPathList = new ArrayList<>(); // Arraylist that contains a list of each Ray and their own paths
    private static final Random rand = new Random();

    // default constructor
    public Game() {
        gameScreen = new GameScreen(this); // creates a new screen
        gameWindow = new GameWindow(gameScreen, this); // creates a new window
        gameScreen.setFocusable(true); // used if we have input, so if we accidentally minimise, we can just click the window again to refocus
        gameScreen.requestFocus();

        hexagonalBoxes = loadHexagonalBoxes();
        atomList = generateAtoms();
        markerColourList = generateMarkerColourList();
    }

    /* Handles the visuals of the game */
    public void render(Graphics g) {
        // draw background board
        g.drawImage(bgImage, 0, 0,1280,720, null);

        // draw atoms
        for (Atom atom : atomList) {
            g.drawImage(Atom.getAtomImage(), atom.getX(), atom.getY(), 50,50,null);
        }

        // draw rays
        Graphics2D g2d = (Graphics2D) g;
        for (ArrayList<Ray> rayPath : rayPathList) {
            for (Ray ray : rayPath) {
                g2d.setStroke(new BasicStroke(5));
                g2d.setColor(Color.YELLOW);
                g2d.drawLine(ray.getX1(), ray.getY1(), ray.getX2(), ray.getY2());
            }
        }

        // hide internal atoms and rays if setting is false
        if (!seeAtomsandRays) {
            g.drawImage(boardCover, 0, 0,1280,720, null);
        }
    }

    private ArrayList<Color> generateMarkerColourList() {
        ArrayList<Color> colourList = new ArrayList<>();

        int rValue = rand.nextInt(255);
        int gValue = rand.nextInt(255);
        int bValue = rand.nextInt(255);

        // 54 "exit" points
        // assume each marker gets absorbed, so different colour each time
        for (int i = 0; i < 54; i++) {
            while (((rValue<100)&&(gValue<100)&&(bValue<100)) || ((rValue>100)&&(gValue>100)&&(bValue>100))) {
                rValue = rand.nextInt(255);
                gValue = rand.nextInt(255);
                bValue = rand.nextInt(255);
            } // this is done to make sure a colour close to white or black isn't generated (which are used for reflection/absorbed markers)
            colourList.add(new Color(rValue,gValue,bValue));
        }
        if (colourList.size() != 54) {
            JOptionPane.showMessageDialog(null,"Failed to initialise colourlist.", "ColourList Initialisation Error", JOptionPane.ERROR_MESSAGE);
        }
        return colourList;
    }

    public void loadPresetRayPath() {
        // 6 - 30 - 16 - 9 - 10 - 34 - 46 - 61
        // Loads a singular preset ray path

        ArrayList<Ray> newRay = new ArrayList<>();

        // starting
        newRay.add(new Ray(329,162,
                hexagonalBoxes.get(5).getX(),hexagonalBoxes.get(5).getY()));

        // 6 -> 30
        newRay.add(new Ray(hexagonalBoxes.get(5).getX(),hexagonalBoxes.get(5).getY(),
                hexagonalBoxes.get(29).getX(),hexagonalBoxes.get(29).getY()));

        // 30 -> 16
        newRay.add(new Ray(hexagonalBoxes.get(29).getX(),hexagonalBoxes.get(29).getY(),
                hexagonalBoxes.get(15).getX(),hexagonalBoxes.get(15).getY()));

        // 16 -> 9
        newRay.add(new Ray(hexagonalBoxes.get(15).getX(),hexagonalBoxes.get(15).getY(),
                hexagonalBoxes.get(8).getX(),hexagonalBoxes.get(8).getY()));

        // 9 -> 10
        newRay.add(new Ray(hexagonalBoxes.get(8).getX(),hexagonalBoxes.get(8).getY(),
                hexagonalBoxes.get(9).getX(),hexagonalBoxes.get(9).getY()));

        // 10 -> 34
        newRay.add(new Ray(hexagonalBoxes.get(9).getX(),hexagonalBoxes.get(9).getY(),
                hexagonalBoxes.get(33).getX(),hexagonalBoxes.get(33).getY()));

        // 34 -> 46
        newRay.add(new Ray(hexagonalBoxes.get(33).getX(),hexagonalBoxes.get(33).getY(),
                hexagonalBoxes.get(45).getX(),hexagonalBoxes.get(45).getY()));

        // 46 -> 61
        newRay.add(new Ray(hexagonalBoxes.get(45).getX(),hexagonalBoxes.get(45).getY(),
                hexagonalBoxes.get(60).getX(),hexagonalBoxes.get(60).getY()));

        // ending
        newRay.add(new Ray(hexagonalBoxes.get(60).getX(),hexagonalBoxes.get(60).getY(),
                973,620));

        rayPathList.add(newRay);
    }

    private ArrayList<Atom> generateAtoms() {
        if (hexagonalBoxes == null) {
            JOptionPane.showMessageDialog(null,"Error: HexagonalBoxes arraylist is null.", null, JOptionPane.ERROR_MESSAGE);
        } if (hexagonalBoxes.size() != 61) {
            JOptionPane.showMessageDialog(null,"Error: HexagonalBoxes arraylist length is not 61 as expected.", null, JOptionPane.ERROR_MESSAGE);
        }
        int hexagonalBoxesLength = hexagonalBoxes.size();
        int atomPosIndex = rand.nextInt(0,hexagonalBoxesLength);

        ArrayList<Atom> atoms = new ArrayList<>();
        for (int i = 0; i<NUM_OF_ATOMS; i++) {
            while (hexagonalBoxes.get(atomPosIndex).HasAtom()) { // ensures that atoms do not generate in the same box
                atomPosIndex = rand.nextInt(0,hexagonalBoxesLength);
            }

            Atom atom = new Atom(hexagonalBoxes.get(atomPosIndex).getX(), hexagonalBoxes.get(atomPosIndex).getY());
            hexagonalBoxes.get(atomPosIndex).setHasAtom(true); // that box now has an atom present. set respective boolean hasAtom to true.
            System.out.println(atom);
            atoms.add(atom);
        }
        return atoms;
    }

    private ArrayList<HexagonalBox> loadHexagonalBoxes() {
        /*
        This method creates an arraylist of all the hexagonal boxes on the board,
        constructed with their respective center point (in x,y coordinates).
         */
        ArrayList<HexagonalBox> hexagonalBoxArrayList = new ArrayList<>();

        //row 1
        constructBoxes_Row1and9(hexagonalBoxArrayList, 103);
        //row 2
        constructBoxes_Row2and8(hexagonalBoxArrayList, 166);
        //row 3
        constructBoxes_Row3and7(hexagonalBoxArrayList, 231);
        //row 4
        constructBoxes_Row4and6(hexagonalBoxArrayList, 295);
        //row 5
        constructBoxes_Row5(hexagonalBoxArrayList, 359);
        //row 6
        constructBoxes_Row4and6(hexagonalBoxArrayList, 424);
        //row 7
        constructBoxes_Row3and7(hexagonalBoxArrayList, 489);
        //row 8
        constructBoxes_Row2and8(hexagonalBoxArrayList, 552);
        //row 9
        constructBoxes_Row1and9(hexagonalBoxArrayList, 612);

        return hexagonalBoxArrayList;
    }

    private void constructBoxes_Row1and9(ArrayList<HexagonalBox> hexagonalBoxArrayList, int y) {
        hexagonalBoxArrayList.add(new HexagonalBox(491, y));
        hexagonalBoxArrayList.add(new HexagonalBox(565, y));
        hexagonalBoxArrayList.add(new HexagonalBox(642, y));
        hexagonalBoxArrayList.add(new HexagonalBox(712, y));
        hexagonalBoxArrayList.add(new HexagonalBox(787, y));
    }

    private void constructBoxes_Row2and8(ArrayList<HexagonalBox> hexagonalBoxArrayList, int y) {
        hexagonalBoxArrayList.add(new HexagonalBox(454, y));
        hexagonalBoxArrayList.add(new HexagonalBox(527, y));
        hexagonalBoxArrayList.add(new HexagonalBox(600, y));
        hexagonalBoxArrayList.add(new HexagonalBox(674, y));
        hexagonalBoxArrayList.add(new HexagonalBox(750, y));
        hexagonalBoxArrayList.add(new HexagonalBox(822, y));
    }

    private void constructBoxes_Row3and7(ArrayList<HexagonalBox> hexagonalBoxArrayList, int y) {
        hexagonalBoxArrayList.add(new HexagonalBox(417, y));
        hexagonalBoxArrayList.add(new HexagonalBox(490, y));
        hexagonalBoxArrayList.add(new HexagonalBox(564, y));
        hexagonalBoxArrayList.add(new HexagonalBox(639, y));
        hexagonalBoxArrayList.add(new HexagonalBox(708, y));
        hexagonalBoxArrayList.add(new HexagonalBox(780, y));
        hexagonalBoxArrayList.add(new HexagonalBox(860, y));
    }

    private void constructBoxes_Row4and6(ArrayList<HexagonalBox> hexagonalBoxArrayList, int y) {
        hexagonalBoxArrayList.add(new HexagonalBox(380, y));
        hexagonalBoxArrayList.add(new HexagonalBox(455, y));
        hexagonalBoxArrayList.add(new HexagonalBox(530, y));
        hexagonalBoxArrayList.add(new HexagonalBox(600, y));
        hexagonalBoxArrayList.add(new HexagonalBox(674, y));
        hexagonalBoxArrayList.add(new HexagonalBox(752, y));
        hexagonalBoxArrayList.add(new HexagonalBox(835, y));
        hexagonalBoxArrayList.add(new HexagonalBox(898, y));
    }

    private void constructBoxes_Row5(ArrayList<HexagonalBox> hexagonalBoxArrayList, int y) {
        hexagonalBoxArrayList.add(new HexagonalBox(345, y));
        hexagonalBoxArrayList.add(new HexagonalBox(416, y));
        hexagonalBoxArrayList.add(new HexagonalBox(490, y));
        hexagonalBoxArrayList.add(new HexagonalBox(565, y));
        hexagonalBoxArrayList.add(new HexagonalBox(638, y));
        hexagonalBoxArrayList.add(new HexagonalBox(715, y));
        hexagonalBoxArrayList.add(new HexagonalBox(785, y));
        hexagonalBoxArrayList.add(new HexagonalBox(860, y));
        hexagonalBoxArrayList.add(new HexagonalBox(933, y));
    }
}
