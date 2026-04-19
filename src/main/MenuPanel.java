package main;

// gemini start
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utils.Constants;

public class MenuPanel extends JPanel implements ActionListener {
    private GameWindow gameWindow;
    private JButton playButton;
    private JButton highscoreButton;
    private JButton exitButton;

    public MenuPanel(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setLayout(null); // Use absolute positioning for simplicity

        playButton = new JButton("Play (Level 1)");
        playButton.setBounds(300, 200, 200, 50);
        playButton.addActionListener(this);
        add(playButton);

        JButton level2Button = new JButton("Level 2");
        level2Button.setBounds(300, 270, 95, 50);
        level2Button.addActionListener(e -> gameWindow.startGame(2));
        add(level2Button);

        JButton level3Button = new JButton("Level 3");
        level3Button.setBounds(405, 270, 95, 50);
        level3Button.addActionListener(e -> gameWindow.startGame(3));
        add(level3Button);

        highscoreButton = new JButton("Highscore");
        highscoreButton.setBounds(300, 340, 200, 50);
        // gemini start
        highscoreButton.addActionListener(this);
        // gemini end
        add(highscoreButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(300, 410, 200, 50);
        exitButton.addActionListener(this);
        add(exitButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Simple background copied from GamePanel for a consistent look
        g.setColor(new Color(100, 200, 255));
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        // Draw Sun
        g.setColor(new Color(255, 230, 100)); // Bright yellow
        g.fillOval(Constants.SCREEN_WIDTH - 120, 40, 80, 80);

        // Draw simple Clouds
        g.setColor(Color.WHITE);
        g.fillOval(100, 80, 60, 40);
        g.fillOval(130, 60, 70, 50);
        g.fillOval(170, 80, 60, 40);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        String title = "Angry Birds Clone";
        int width = g.getFontMetrics().stringWidth(title);
        g.drawString(title, (Constants.SCREEN_WIDTH - width) / 2, 150);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            gameWindow.startGame(1); // Start at level 1
            // gemini start
        } else if (e.getSource() == highscoreButton) {
            gameWindow.showHighscore();
            // gemini end
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }
}
// gemini end