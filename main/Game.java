package main;

import entities.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game implements Runnable {
    // Application Variables
    private GameWindow gameWindow;
    private GameScreen gameScreen;
    private Thread gameThread;
    private final int FPS_LIMIT = 60; // setting the max fps for the game
    private final int UPS_LIMIT = 200; // setting the max ups (frame updates per second)
    private final Boolean performanceStatEnabled = false;

    // Game Variables
    private final int NUM_OF_ATOMS = 6;
    private static Image bgImage = (new ImageIcon(Game.class.getClassLoader().getResource("res/Board Layouts/transparent-hex-board.PNG")).getImage());
    private final Boolean seeAtomsandRays = true;
    private final ArrayList<HexagonalBox> hexagonalBoxes; // function to construct all the hexagonal boxes
    private final ArrayList<Atom> atomList; // function to generate the atoms (including their positions)
    private static final Random rand = new Random();
    private final ArrayList<Ray> rayPathList = new ArrayList<>();

    // default constructor
    public Game() {
        gameScreen = new GameScreen(this); // creates a new screen
        gameWindow = new GameWindow(gameScreen); // creates a new window
        gameScreen.setFocusable(true); // used if we have input, so if we accidentally minimise, we can just click the window again to refocus
        gameScreen.requestFocus();

        hexagonalBoxes = loadHexagonalBoxes();
        atomList = generateAtoms();
        loadPresetRayPath();
        startGameLoop(); // starts rendering the screen
    }

    private void loadPresetRayPath() {
        // 6 - 30 - 16 - 9 - 10 - 34 - 46 - 61

        // starting
        rayPathList.add(new Ray(329,162,
                hexagonalBoxes.get(5).getX(),hexagonalBoxes.get(5).getY()));

        // 6 -> 30
        rayPathList.add(new Ray(hexagonalBoxes.get(5).getX(),hexagonalBoxes.get(5).getY(),
                hexagonalBoxes.get(29).getX(),hexagonalBoxes.get(29).getY()));

        // 30 -> 16
        rayPathList.add(new Ray(hexagonalBoxes.get(29).getX(),hexagonalBoxes.get(29).getY(),
                hexagonalBoxes.get(15).getX(),hexagonalBoxes.get(15).getY()));

        // 16 -> 9
        rayPathList.add(new Ray(hexagonalBoxes.get(15).getX(),hexagonalBoxes.get(15).getY(),
                hexagonalBoxes.get(8).getX(),hexagonalBoxes.get(8).getY()));

        // 9 -> 10
        rayPathList.add(new Ray(hexagonalBoxes.get(8).getX(),hexagonalBoxes.get(8).getY(),
                hexagonalBoxes.get(9).getX(),hexagonalBoxes.get(9).getY()));

        // 10 -> 34
        rayPathList.add(new Ray(hexagonalBoxes.get(9).getX(),hexagonalBoxes.get(9).getY(),
                hexagonalBoxes.get(33).getX(),hexagonalBoxes.get(33).getY()));

        // 34 -> 46
        rayPathList.add(new Ray(hexagonalBoxes.get(33).getX(),hexagonalBoxes.get(33).getY(),
                hexagonalBoxes.get(45).getX(),hexagonalBoxes.get(45).getY()));

        // 46 -> 61
        rayPathList.add(new Ray(hexagonalBoxes.get(45).getX(),hexagonalBoxes.get(45).getY(),
                hexagonalBoxes.get(60).getX(),hexagonalBoxes.get(60).getY()));

        // ending
        rayPathList.add(new Ray(hexagonalBoxes.get(60).getX(),hexagonalBoxes.get(60).getY(),
                973,620));
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
        addforRow1and9(hexagonalBoxArrayList, 103);
        //row 2
        addforRow2and8(hexagonalBoxArrayList, 166);
        //row 3
        addforRow3and7(hexagonalBoxArrayList, 231);
        //row 4
        addforRow4and6(hexagonalBoxArrayList, 295);
        //row 5
        addforRow5(hexagonalBoxArrayList, 359);
        //row 6
        addforRow4and6(hexagonalBoxArrayList, 424);
        //row 7
        addforRow3and7(hexagonalBoxArrayList, 489);
        //row 8
        addforRow2and8(hexagonalBoxArrayList, 552);
        //row 9
        addforRow1and9(hexagonalBoxArrayList, 612);

        return hexagonalBoxArrayList;
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void render(Graphics g) {
        g.drawImage(bgImage, 0, 0,1280,720, null);

        if (seeAtomsandRays) {
            Graphics2D g2d = (Graphics2D) g;
            for (Ray ray : rayPathList) {
                g2d.setStroke(new BasicStroke(5));
                g2d.setColor(Color.YELLOW);
                g2d.drawLine(ray.getX1(), ray.getY1(), ray.getX2(), ray.getY2());
            }
            for (Atom atom : atomList) {
                g.drawImage(Atom.getAtomImage(), atom.getX(), atom.getY(), 50,50,null);
            }
        }
    }

    @Override
    public void run() {
        /*
        Basically this whole section updates the game every 1/60th of a second
        Keeps the game running at the preferred FPS limit and updates aren't done irregularly within that second
         */
        double timePerFrame = 1000000000.0 / FPS_LIMIT;
        double timePerUpdate = 1000000000.0 / UPS_LIMIT;

        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();

        int frames = 0;
        int updates = 0;
        double deltaTime = 0;
        double deltaFrame = 0;

        while(true) {
            long currentTime = System.nanoTime();
            deltaTime += (currentTime - previousTime) / timePerUpdate;
            deltaFrame += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaTime >= 1) { // condition to check when it's time to update (accounts for lost time)
                updates++;
                deltaTime--;
            }

            if (deltaFrame >= 1) {
                gameScreen.repaint();
                frames++;
                deltaFrame--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                if (performanceStatEnabled) System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    private void addforRow1and9(ArrayList<HexagonalBox> hexagonalBoxArrayList, int y) {
        hexagonalBoxArrayList.add(new HexagonalBox(491, y));
        hexagonalBoxArrayList.add(new HexagonalBox(565, y));
        hexagonalBoxArrayList.add(new HexagonalBox(642, y));
        hexagonalBoxArrayList.add(new HexagonalBox(712, y));
        hexagonalBoxArrayList.add(new HexagonalBox(787, y));
    }

    private void addforRow2and8(ArrayList<HexagonalBox> hexagonalBoxArrayList, int y) {
        hexagonalBoxArrayList.add(new HexagonalBox(454, y));
        hexagonalBoxArrayList.add(new HexagonalBox(527, y));
        hexagonalBoxArrayList.add(new HexagonalBox(600, y));
        hexagonalBoxArrayList.add(new HexagonalBox(674, y));
        hexagonalBoxArrayList.add(new HexagonalBox(750, y));
        hexagonalBoxArrayList.add(new HexagonalBox(822, y));
    }

    private void addforRow3and7(ArrayList<HexagonalBox> hexagonalBoxArrayList, int y) {
        hexagonalBoxArrayList.add(new HexagonalBox(417, y));
        hexagonalBoxArrayList.add(new HexagonalBox(490, y));
        hexagonalBoxArrayList.add(new HexagonalBox(564, y));
        hexagonalBoxArrayList.add(new HexagonalBox(639, y));
        hexagonalBoxArrayList.add(new HexagonalBox(708, y));
        hexagonalBoxArrayList.add(new HexagonalBox(780, y));
        hexagonalBoxArrayList.add(new HexagonalBox(860, y));
    }

    private void addforRow4and6(ArrayList<HexagonalBox> hexagonalBoxArrayList, int y) {
        hexagonalBoxArrayList.add(new HexagonalBox(380, y));
        hexagonalBoxArrayList.add(new HexagonalBox(455, y));
        hexagonalBoxArrayList.add(new HexagonalBox(530, y));
        hexagonalBoxArrayList.add(new HexagonalBox(600, y));
        hexagonalBoxArrayList.add(new HexagonalBox(674, y));
        hexagonalBoxArrayList.add(new HexagonalBox(752, y));
        hexagonalBoxArrayList.add(new HexagonalBox(835, y));
        hexagonalBoxArrayList.add(new HexagonalBox(898, y));
    }

    private void addforRow5(ArrayList<HexagonalBox> hexagonalBoxArrayList, int y) {
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
