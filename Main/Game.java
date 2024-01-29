package Main;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GameScreen gameScreen;
    private Thread gameThread;

    private final int FPS_LIMIT = 60; // setting the max fps for the game
    private final int UPS_LIMIT = 200; // setting the max ups (frame updates per second)

    public Game() {
        gameScreen = new GameScreen(this); // creates a new screen
        gameWindow = new GameWindow(gameScreen); // creates a new window
        gameScreen.setFocusable(true); // used if we have input, so if we accidentally minimise, we can just click the window again to refocus
        gameScreen.requestFocus();

        startGameLoop(); // starts rendering the screen
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
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
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
}
