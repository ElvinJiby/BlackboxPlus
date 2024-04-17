package main;

import main.ShowScore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver {

    private JFrame jFrame;

    public GameOver() {
        jFrame = new JFrame();
        jFrame.setTitle("Game Over");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(400, 200);
        jFrame.setLocationRelativeTo(null); // Center the window on the screen
        jFrame.setBackground(Color.BLACK);
        jFrame.setResizable(false);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(null);

        JLabel gameOverLabel = new JLabel("GAME OVER!!");
        gameOverLabel.setVerticalAlignment(SwingConstants.CENTER);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setForeground(Color.CYAN);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gameOverLabel.setBounds(80, 50, 240, 50);
        panel.add(gameOverLabel);

        jFrame.getContentPane().add(panel);
        jFrame.setVisible(true);

        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowScore();
                jFrame.dispose();
            }
        });
        timer.setRepeats(false); // Set to not repeat
        timer.start();
    }
}
