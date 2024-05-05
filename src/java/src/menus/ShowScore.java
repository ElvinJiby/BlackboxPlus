package menus;

import main.Game;
import javax.swing.*;
import java.awt.*;

public class ShowScore {
    public ShowScore() {
        Game game = new Game();
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Score");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(640, 200); // window size
        jFrame.setIconImage(new ImageIcon(getClass().getResource("/Icons/new_icon.png")).getImage());
        jFrame.setLocationRelativeTo(null); // centre window
        jFrame.setBackground(Color.BLACK); // set black background so window stays dark
        jFrame.setResizable(false); // cannot be resized

        // override method with package access method
        // gets superclass method
        // sets black background
        // sets over region specified
        JPanel jPanel = getScorePanel(jFrame, game);

        jFrame.getContentPane().add(jPanel);
        jFrame.setVisible(true);

        Timer disposeTimer = new Timer(5000, e -> {
            new StartScreen();
            jFrame.dispose();
        });
        disposeTimer.setRepeats(false); // Set to not repeat
        disposeTimer.start();
    }

    private static JPanel getScorePanel(JFrame jFrame, Game game) {
        JPanel jPanel = new JPanel() {
            @Override // override method with package access method
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // gets superclass method
                g.setColor(Color.BLACK); // sets black background
                g.fillRect(0, 0, jFrame.getWidth(), jFrame.getHeight()); // sets over region specified
            }
        };
        jPanel.setLayout(null); // centre

        JLabel scoreLabel = new JLabel(game.getPlayerName() + " scored " + game.getScore() + " points");
        scoreLabel.setVerticalAlignment(SwingConstants.CENTER);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setForeground(Color.CYAN);
        scoreLabel.setFont(new Font("Ariel", Font.BOLD, 30));
        scoreLabel.setBounds(20, 50, 600, 50);
        jPanel.add(scoreLabel);
        return jPanel;
    }
}
