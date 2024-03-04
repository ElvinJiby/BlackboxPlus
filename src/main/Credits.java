import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Credits implements Runnable{
    private static JFrame jFrame;

    public Credits() {
        jFrame = new JFrame("Credits");
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setSize(1280, 720);
        jFrame.setLocationRelativeTo(null);
        jFrame.setIconImage(new ImageIcon("./res/Icons/new_icon.png").getImage());

        ImageIcon creditsGIF = new ImageIcon("./res/Icons/credits_720p.gif");
        JLabel creditsJLabel = new JLabel(creditsGIF);
        creditsJLabel.setSize(1280, 720);

        jFrame.add(creditsJLabel);

        jFrame.addKeyListener(new KeyListener() {
            @Override public void keyTyped(KeyEvent e) {}
            @Override public void keyReleased(KeyEvent e) {}
            @Override public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') System.exit(0);
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);
            }});
    }

    public static void main(String[] args) {
        Credits credits = new Credits();
        credits.run();
    }

    @Override
    public void run() {
        jFrame.setVisible(true);
    }
}
