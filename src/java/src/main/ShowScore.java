package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowScore {

    private static JFrame jFrame;
    private static JPanel jPanel;
    private static JLabel scoreLabel;
    private static String name = "myName123456";
    private static int score = 150;

    public ShowScore() {
        jFrame = new JFrame();
        jFrame.setTitle("Score");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(640, 200); // window size
        jFrame.setIconImage(new ImageIcon(getClass().getResource("/Icons/new_icon.png")).getImage());
        jFrame.setLocationRelativeTo(null); // centre window
        jFrame.setBackground(Color.BLACK); // set black background so window stays dark
        jFrame.setResizable(false); // cannot be resized

        jPanel = new JPanel() {
            @Override // override method with package access method
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // gets superclass method
                g.setColor(Color.BLACK); // sets black background
                g.fillRect(0, 0, jFrame.getWidth(), jFrame.getHeight()); // sets over region specified
            }
        };
        jPanel.setLayout(null); // centre

        scoreLabel = new JLabel(name + " scored " + score + " points");
        scoreLabel.setVerticalAlignment(SwingConstants.CENTER);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setForeground(Color.CYAN);
        scoreLabel.setFont(new Font("Ariel", Font.BOLD, 30));
        scoreLabel.setBounds(20, 50, 600, 50);
        jPanel.add(scoreLabel);

        jFrame.getContentPane().add(jPanel);
        jFrame.setVisible(true);

        Timer disposeTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
            }
        });
        disposeTimer.setRepeats(false); // Set to not repeat
        disposeTimer.start();
    }
}
