package main;

import computations.Board;
import computations.Lists;
import entities.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    // Application Variables
    private GameWindow gameWindow;
    private GameScreen gameScreen;
    private static final Random rand = new Random();

    // Game Variables
    private static Image bgImage = (new ImageIcon(Game.class.getResource("/Board Layouts/transparent-numbered-all.PNG")).getImage());
    private static Image boardCover = (new ImageIcon(Game.class.getResource("/Board Layouts/transparent-numbered-background.PNG")).getImage());

    private final int NUM_OF_ATOMS = 6;
    private Boolean seeAtomsandRays = true; // debug setting to show internal atoms (default: false)
    private int score = 0;
    private String playerName = "user34567";

    private final ArrayList<HexagonalBox> hexagonalBoxes; // Arraylist that contains all the hexagonal boxes
    private final ArrayList<Atom> atomList; // Arraylist that contains all the atoms
    private final ArrayList<Marker> markersList = new ArrayList<>(); // Arraylist that contains all the markers and their colour and coords
    private final ArrayList<ArrayList<Ray>> rayPathList = new ArrayList<>(); // Arraylist that contains a list of each Ray and their own paths
    private ArrayList<ExitPoint> exitPointsList = new ArrayList<>(); // Arraylist that contains the coordinates of each exit point
    private final ArrayList<Integer> atomBoxNumbers = new ArrayList<>();
    private Board boardp = (new Lists()).createboard();
    ;


    // default constructor
    public Game() {
        gameScreen = new GameScreen(this); // creates a new screen
        gameWindow = new GameWindow(gameScreen, this); // creates a new window
        gameScreen.setFocusable(true); // used if we have input, so if we accidentally minimise, we can just click the window again to refocus
        gameScreen.requestFocus();

        hexagonalBoxes = loadHexagonalBoxes();
        atomList = generateAtoms();
        exitPointsList = loadExitPointCoords();
    }

    /* Handles the visuals of the game */
    public void render(Graphics g) {
        // draw background board
        g.drawImage(bgImage, 0, 0,1280,720, null);

        // draw atoms
        if (atomList != null) {
            for (Atom atom : atomList) {
                g.drawImage(Atom.getAtomImage(), atom.getX(), atom.getY(), 50, 50, null);
            }
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

        // draw markers
        for (Marker marker : markersList) {
            g2d.setColor(marker.getMarkerColour());
            g2d.fillOval(marker.getX(), marker.getY(), 10,10);
        }

        // hide internal atoms and rays if setting is false
        if (!seeAtomsandRays) {
            g.drawImage(boardCover, 0, 0,1280,720, null);
        }
    }

    public void shootRay(int entry) {
//        boardp.getnode(4,4).setatom(true);
        ArrayList<Integer> boxNumList = boardp.iterate(entry);
        ArrayList<Ray> newRayPath = new ArrayList<>();

        System.out.println(boxNumList);

        // Absorbed Ray Case
        boolean isRayAbsorbed = boxNumList.getLast() == -1;
        if (isRayAbsorbed) boxNumList.remove(boxNumList.getLast());
        int pathLength = boxNumList.size();

        // Reflected Ray Case
        boolean isRayReflected = boxNumList.getFirst().equals(boxNumList.getLast());

        ExitPoint startPoint = exitPointsList.get(boxNumList.getFirst()-1);
        newRayPath.add(new Ray(
                startPoint.getX(),
                startPoint.getY(),
                hexagonalBoxes.get(boxNumList.get(1)-1).getX(),
                hexagonalBoxes.get(boxNumList.get(1)-1).getY()));

        for (int i = 1; i < pathLength-2; i++) {
            newRayPath.add(new Ray(
                    hexagonalBoxes.get(boxNumList.get(i)-1).getX(),
                    hexagonalBoxes.get(boxNumList.get(i)-1).getY(),
                    hexagonalBoxes.get(boxNumList.get(i+1)-1).getX(),
                    hexagonalBoxes.get(boxNumList.get(i+1)-1).getY()));
        }

        ExitPoint endPoint = exitPointsList.get(boxNumList.getLast()-1);
        newRayPath.add(new Ray(
                hexagonalBoxes.get(boxNumList.getLast()-1).getX(),
                hexagonalBoxes.get(boxNumList.getLast()-1).getY(),
                endPoint.getX(),
                endPoint.getY()));


        /* Marker Cases */
        // Normal case - Ray goes straight through with no reflection of absorption
        if (!isRayAbsorbed && !isRayReflected) {
            // User can choose the colour of the marker
            Color colorChoice = gameWindow.askMarkerColor();

            // Marker at first entry point of ray path
            int startX = exitPointsList.get(boxNumList.getFirst()-1).getX();
            int startY = exitPointsList.get(boxNumList.getFirst()-1).getY();
            markersList.add(new Marker(startX,startY,colorChoice));

            // Marker at last entry point of ray path
            int endX = exitPointsList.get(boxNumList.getLast()-1).getX();
            int endY = exitPointsList.get(boxNumList.getLast()-1).getY();
            markersList.add(new Marker(endX,endY,colorChoice));

            score += 2; // 2 point for a pair of markers
            System.out.println("Normal");
        }
        // Absorbed case - Ray absorbed by atom
        else if (isRayAbsorbed) {
            // Black (Gray) marker at first entry point of ray path
            int startX = exitPointsList.get(boxNumList.getFirst()-1).getX();
            int startY = exitPointsList.get(boxNumList.getFirst()-1).getY();
            markersList.add(new Marker(startX,startY,Color.GRAY));

            score++; // 1 point for marker
            System.out.println("Absorbed!");
        }
        // Reflected case = Ray deflects and exits at the same point of entry
        else {
            // White marker at first entry point of ray path
            int startX = exitPointsList.get(boxNumList.getFirst()-1).getX();
            int startY = exitPointsList.get(boxNumList.getFirst()-1).getY();
            markersList.add(new Marker(startX,startY,Color.WHITE));

            score++; // 1 point for marker
            System.out.println("Reflected!");
        }

        // Add the newly created ray path to a pathlist
        rayPathList.add(newRayPath);
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

    private ArrayList<ExitPoint> loadExitPointCoords() {
        ArrayList<ExitPoint> exitPoints = new ArrayList<>();

        // Hardcoded coordinates for each exit point (where the number is)
        // 1 - 10
        exitPoints.add(new ExitPoint(458,50));
        exitPoints.add(new ExitPoint(429,100));
        exitPoints.add(new ExitPoint(420,112));
        exitPoints.add(new ExitPoint(389,165));
        exitPoints.add(new ExitPoint(383,174));

        exitPoints.add(new ExitPoint(358,230));
        exitPoints.add(new ExitPoint(348,240));
        exitPoints.add(new ExitPoint(318,295));
        exitPoints.add(new ExitPoint(308,308));
        exitPoints.add(new ExitPoint(280,361));

        // 11 - 20
        exitPoints.add(new ExitPoint(309,415));
        exitPoints.add(new ExitPoint(321,428));
        exitPoints.add(new ExitPoint(346,480));
        exitPoints.add(new ExitPoint(355,490));
        exitPoints.add(new ExitPoint(383,541));

        exitPoints.add(new ExitPoint(392,552));
        exitPoints.add(new ExitPoint(420,605));
        exitPoints.add(new ExitPoint(430,620));
        exitPoints.add(new ExitPoint(461,671));
        exitPoints.add(new ExitPoint(523,670));

        // 21 - 30
        exitPoints.add(new ExitPoint(533,671));
        exitPoints.add(new ExitPoint(598,671));
        exitPoints.add(new ExitPoint(605,672));
        exitPoints.add(new ExitPoint(670,671));
        exitPoints.add(new ExitPoint(682,672));

        exitPoints.add(new ExitPoint(746,672));
        exitPoints.add(new ExitPoint(755,672));
        exitPoints.add(new ExitPoint(819,670));
        exitPoints.add(new ExitPoint(854,620));
        exitPoints.add(new ExitPoint(858,608));

        // 31 - 40
        exitPoints.add(new ExitPoint(893,554));
        exitPoints.add(new ExitPoint(898,543));
        exitPoints.add(new ExitPoint(927,491));
        exitPoints.add(new ExitPoint(933,479));
        exitPoints.add(new ExitPoint(962,428));

        exitPoints.add(new ExitPoint(970,415));
        exitPoints.add(new ExitPoint(999,360));
        exitPoints.add(new ExitPoint(969,306));
        exitPoints.add(new ExitPoint(959,294));
        exitPoints.add(new ExitPoint(933,240));

        // 41 - 50
        exitPoints.add(new ExitPoint(927,230));
        exitPoints.add(new ExitPoint(895,177));
        exitPoints.add(new ExitPoint(890,167));
        exitPoints.add(new ExitPoint(858,113));
        exitPoints.add(new ExitPoint(852,100));

        exitPoints.add(new ExitPoint(822,47));
        exitPoints.add(new ExitPoint(755,47));
        exitPoints.add(new ExitPoint(747,46));
        exitPoints.add(new ExitPoint(683,48));
        exitPoints.add(new ExitPoint(672,46));

        // 51 - 54
        exitPoints.add(new ExitPoint(606,48));
        exitPoints.add(new ExitPoint(598,47));
        exitPoints.add(new ExitPoint(533,48));
        exitPoints.add(new ExitPoint(526,46));

        return exitPoints;
    }

    private ArrayList<Atom> generateAtoms() {
        if (hexagonalBoxes == null) {
            JOptionPane.showMessageDialog(null,"Error: HexagonalBoxes arraylist is null.", null, JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
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

            atomBoxNumbers.add(atomPosIndex+1);
            Atom atom = new Atom(hexagonalBoxes.get(atomPosIndex).getX(), hexagonalBoxes.get(atomPosIndex).getY());
            hexagonalBoxes.get(atomPosIndex).setHasAtom(true); // that box now has an atom present. set respective boolean hasAtom to true.
            System.out.println(atom);
            atoms.add(atom);
        }
        return atoms;
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

    public void toggleInternalBoardSetting() {
        seeAtomsandRays = !seeAtomsandRays;
        gameScreen.repaint();
    }

    public int getScore() {
        return score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Boolean isAtomLocationGuessCorrect(int boxNumber) {
        return atomBoxNumbers.contains(boxNumber);
    }

    public void addIncorrectAtomGuess() {
        score += 5;
    }

    public int getNumAtoms() {
        return NUM_OF_ATOMS;
    }
}
