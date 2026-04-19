package main;

import javax.swing.JFrame;
// gemini start
import javax.swing.JPanel;
import java.awt.CardLayout;
// gemini end
import utils.Constants;

public class GameWindow {
    // gemini start
    private JFrame frame;
    private GamePanel gamePanel;
    private MenuPanel menuPanel;
    // gemini start
    private HighscorePanel highscorePanel;
    // gemini end
    private JPanel mainPanel;
    private CardLayout cardLayout;
    // gemini end

    public GameWindow() {
        // gemini start
        frame = new JFrame("Angry Birds Clone - Java");

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Pass 'this' so panels can call back to GameWindow to switch views
        menuPanel = new MenuPanel(this);
        gamePanel = new GamePanel(this);

        mainPanel.add(menuPanel, "MENU");
        mainPanel.add(gamePanel, "GAME");

        frame.add(mainPanel);
        // gemini end
        frame.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // Center on screen

        // gemini start
        // The Key Listener for 'R' has been moved to GamePanel to be context-specific.
        // gemini end
        frame.setVisible(true);
        // gemini start
        showMenu(); // Start by showing the menu
        // gemini end
    }

    // gemini start
    public void showMenu() {
        cardLayout.show(mainPanel, "MENU");
        menuPanel.requestFocusInWindow();
    }

    // gemini start
    public void showHighscore() {
        cardLayout.show(mainPanel, "HIGHSCORE");
        highscorePanel.requestFocusInWindow();
    }
    // gemini end

    public void startGame(int level) {
        gamePanel.loadLevel(level);
        cardLayout.show(mainPanel, "GAME");
        gamePanel.requestFocusInWindow();
    }
    // gemini end
}
