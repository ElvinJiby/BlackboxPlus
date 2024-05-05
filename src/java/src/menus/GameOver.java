package menus;

import main.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameOver {
    private final JFrame jFrame;
    private static final Image gameIcon = new ImageIcon(Objects.requireNonNull(GameWindow.class.getResource("/Icons/new_icon.png"))).getImage();

    public GameOver() {
        jFrame = new JFrame();
        jFrame.setTitle("Game Over");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setIconImage(gameIcon);
        jFrame.setSize(400, 200);
        jFrame.setLocationRelativeTo(null);
        jFrame.getContentPane().setBackground(Color.BLACK);
        jFrame.setResizable(false);

        JPanel jPanel = getGameOverPanel();

        jFrame.getContentPane().add(jPanel);
        jFrame.setVisible(true);

        // show score window for 3000 milliseconds and close current frame
        // timer object
        Timer timer = new Timer(3000, e -> {
            jFrame.dispose();
            new ShowScore();
            jFrame.dispose();
        });
        timer.setRepeats(false);
        timer.start();
    }

    private static JPanel getGameOverPanel() {
        JPanel jPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        jPanel.setLayout(null);

        // text
        JLabel jLabel = new JLabel("GAME OVER!!");
        jLabel.setVerticalAlignment(SwingConstants.CENTER);
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setForeground(Color.CYAN);
        jLabel.setFont(new Font("Arial", Font.BOLD, 30));
        jLabel.setBounds(80, 50, 240, 50);
        jPanel.add(jLabel);
        return jPanel;
    }

    public static void main(String[] args) {
        new GameOver();
    }
}
