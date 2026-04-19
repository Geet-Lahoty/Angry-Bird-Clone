package main;

// gemini start
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import utils.Constants;

public class HighscorePanel extends JPanel {
    private GameWindow gameWindow;

    public HighscorePanel(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setLayout(null);

        JButton backButton = new JButton("Back");
        backButton.setBounds(300, 410, 200, 50);
        backButton.addActionListener(e -> gameWindow.showMenu());
        add(backButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Simple background copied from MenuPanel
        g.setColor(new Color(100, 200, 255));
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        // Title
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        String title = "High Scores";
        int width = g.getFontMetrics().stringWidth(title);
        g.drawString(title, (Constants.SCREEN_WIDTH - width) / 2, 150);

        // Placeholder scores
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("(Not Implemented Yet)", 310, 280);
    }
}
// gemini end