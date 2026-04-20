package main;

import javax.swing.JFrame;
//harshil
import javax.swing.JPanel;
import java.awt.CardLayout;
// mistry
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import utils.Constants;

public class GameWindow {
    // harshil
    private JFrame frame;
    private GamePanel gamePanel;
    private MenuPanel menuPanel;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    // mistry

    public GameWindow() {
        // harshil
        frame = new JFrame("Angry Birds  - Java");

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Pass 'this' so panels can call back to GameWindow to switch views
        menuPanel = new MenuPanel(this);
        gamePanel = new GamePanel(this); // Pass GameWindow to GamePanel

        mainPanel.add(menuPanel, "MENU");
        mainPanel.add(gamePanel, "GAME");

        frame.add(mainPanel);
        // mistry
        frame.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // Center on screen

        // harshil
        // Key listener moved to GamePanel to be active only during the game.
        showMenu(); // Start by showing the menu
        // mistry

        frame.setVisible(true);
    }

    // harshil
    public void showMenu() {
        cardLayout.show(mainPanel, "MENU");
        menuPanel.requestFocusInWindow();
    }

    public void startGame() {
        gamePanel.resetGame(); // Reset the game state every time we start
        cardLayout.show(mainPanel, "GAME");
        gamePanel.requestFocusInWindow();
    }
    // mistry
}
