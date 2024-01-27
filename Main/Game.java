package Main;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GameScreen gameScreen;
    private Thread gameThread;

    private final int FPS_LIMIT = 60;
    private final int UPS_LIMIT = 200;

    public Game() {
        gameScreen = new GameScreen(this);
        gameWindow = new GameWindow(gameScreen);
        gameScreen.setFocusable(true);
        gameScreen.requestFocus();

        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
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
