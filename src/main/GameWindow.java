package main;

import javax.swing.JFrame;
import utils.Constants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameWindow {

    public GameWindow() {

        JFrame frame = new JFrame("Angry Birds Clone - Java");
        GamePanel gamePanel = new GamePanel();
        
        frame.add(gamePanel);
        frame.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); 
        
        // Restart listener
        frame.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    gamePanel.resetGame();
                }
            }
        });
        
        frame.setVisible(true);
    }
}
