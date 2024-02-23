package main;

import entities.Atom;

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
    private Boolean performanceStatEnabled = false;

    // Game Variables
    private static Image bgImage = null;
    private Boolean seeAtomsandRays = false;
    private ArrayList<Atom> atomList = generateAtoms(); // function to generate the atoms (including their positions)
    private static Random rand = new Random();

    public Game() {
        gameScreen = new GameScreen(this); // creates a new screen
        gameWindow = new GameWindow(gameScreen); // creates a new window
        gameScreen.setFocusable(true); // used if we have input, so if we accidentally minimise, we can just click the window again to refocus
        gameScreen.requestFocus();

        bgImage = (new ImageIcon("./res/Board Layouts/transparent-hex-board.PNG")).getImage(); // loads the bg image for the game

        startGameLoop(); // starts rendering the screen
    }

    private ArrayList<Atom> generateAtoms() {
        ArrayList<Atom> atoms = new ArrayList<>();
        for (int i = 0; i<6; i++) {
            Atom atom = new Atom((rand.nextInt(340,940)),(rand.nextInt(100,620)));
            System.out.println(atom);
            atoms.add(atom);
        }
        return atoms;
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void render(Graphics g) {
        g.drawImage(bgImage, 0, 0,1280,720, null);

        if (seeAtomsandRays) {
            for (Atom atom : atomList) {
                g.drawImage(Atom.getAtomImage(), atom.getXPosition(), atom.getYPosition(), 50,50,null);
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
}
