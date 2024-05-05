package menus;

import main.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class Credits {
    private static JFrame jFrame;
    private static final Image gameIcon = new ImageIcon(Objects.requireNonNull(GameWindow.class.getResource("/Icons/new_icon.png"))).getImage();
    private static final ImageIcon creditsGIF = new ImageIcon(Objects.requireNonNull(Credits.class.getResource("/Miscellaneous/credits_720p.gif")));

    public Credits() {
        jFrame = new JFrame("Credits");
        jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setSize(1280, 720);
        jFrame.setLocationRelativeTo(null);
        jFrame.setIconImage(gameIcon);

        JLabel creditsJLabel = new JLabel(creditsGIF);
        creditsJLabel.setSize(1280, 720);
        jFrame.add(creditsJLabel);

        // code to check if key is pressed to exit
        jFrame.addKeyListener(new KeyListener() {
            @Override public void keyTyped(KeyEvent e) {}
            @Override public void keyReleased(KeyEvent e) {}
            @Override public void keyPressed(KeyEvent e) { // if q/Q/ESC is pressed, go back to start screen
                if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') { jFrame.dispose(); new StartScreen(); }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { jFrame.dispose(); new StartScreen(); }
            }});

        jFrame.setVisible(true);
    }
}
