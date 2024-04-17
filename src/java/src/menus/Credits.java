package menus;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class Credits {
    private static JFrame jFrame; // credits window
    private static ImageIcon creditsGIF; // GIF image
    private static JLabel creditsJLabel; // Image cast as JLabel

//    @Override public void run() { jFrame.setVisible(true); } // overriding run method to set JFrame visibility to true

    public Credits() {
        jFrame = new JFrame("Credits"); // creates new JFrame object with "Credits" as title
        jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // if window is closed, program does not terminate
        jFrame.setResizable(false); // restricts resizing
        jFrame.setSize(1280, 720); // 720p window
        jFrame.setLocationRelativeTo(null); // centre window according to device screen size
        jFrame.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Icons/new_icon.png"))).getImage()); // sets icon image

        creditsGIF = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Miscellaneous/credits_720p.gif"))); // open gif image
        creditsJLabel = new JLabel(creditsGIF); // save image as JLabel
        creditsJLabel.setSize(1280, 720); // set size to 720p
        jFrame.add(creditsJLabel); // add JLabel to JFrame

        // code to check if key is pressed to exit
        jFrame.addKeyListener(new KeyListener() {
            @Override public void keyTyped(KeyEvent e) {} // checks key typed
            @Override public void keyReleased(KeyEvent e) {} // checks key released
            @Override public void keyPressed(KeyEvent e) { // if q/Q/ESC is pressed, go back to start screen
                if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') { jFrame.dispose(); new StartScreen(); }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { jFrame.dispose(); new StartScreen(); }
            }});

        jFrame.setVisible(true);
    }
}
