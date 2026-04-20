package main;

import entities.Bird;
import entities.Pig;
import entities.Slingshot;
import physics.Vector2D;
import utils.Constants;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener {
    // harshil
    private GameWindow gameWindow;
    // mistry
    private Timer gameLoop;
    private Slingshot slingshot;
    private Bird bird;
    private ArrayList<Pig> pigs;

    private boolean isDragging = false;
    private boolean birdFired = false;

    // Game State
    private int birdsRemaining = 3;
    private boolean gameOver = false;
    private boolean won = false;

    // harshil
    public GamePanel(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        initGame(); // Initialize game state
        // mistry
        // Setup Mouse Listeners for drag and drop
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!birdFired && !gameOver) {
                    Vector2D mousePos = new Vector2D(e.getX(), e.getY());
                    if (mousePos.distance(bird.position) <= bird.radius * 2) {
                        isDragging = true;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isDragging && !gameOver) {
                    isDragging = false;
                    birdFired = true;
                    bird.isFlying = true;

                    // Calculate launch velocity based on pull distance and angle
                    double dx = slingshot.position.x - bird.position.x;
                    double dy = slingshot.position.y - bird.position.y;

                    // 0.15 is the launch power multiplier
                    bird.velocity.x = dx * 0.30;
                    bird.velocity.y = dy * 0.30;
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging && !gameOver) {
                    Vector2D dragPos = new Vector2D(e.getX(), e.getY());

                    // Constrain drag distance to rubber band max length
                    if (dragPos.distance(slingshot.position) > slingshot.maxDragDistance) {
                        double angle = Math.atan2(dragPos.y - slingshot.position.y, dragPos.x - slingshot.position.x);
                        dragPos.x = slingshot.position.x + Math.cos(angle) * slingshot.maxDragDistance;
                        dragPos.y = slingshot.position.y + Math.sin(angle) * slingshot.maxDragDistance;
                    }

                    bird.position.x = dragPos.x;
                    bird.position.y = dragPos.y;
                    repaint();
                }
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);

        // harshil
        // Key listener for reset and returning to menu
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    resetGame();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    gameWindow.showMenu();
                }
            }
        });
        setFocusable(true); // Important for KeyListener to work on JPanel
        // mistry

        // Start Game loop timer
        gameLoop = new Timer(1000 / Constants.FPS, this);
        gameLoop.start();
    }

    /**
     * Sets up the game for a specific level.
     * 
     */
    private void initGame() {
        // Initialize objects
        slingshot = new Slingshot(150, Constants.GROUND_Y - 100);
        bird = new Bird(slingshot.position.x, slingshot.position.y);
        birdFired = false;

        birdsRemaining = 3;
        gameOver = false;
        won = false;

        // Add target Pigs
        pigs = new ArrayList<>();
        // A vertical stack of three pigs
        pigs.add(new Pig(600, Constants.GROUND_Y - 20));
        pigs.add(new Pig(600, Constants.GROUND_Y - 60));
        pigs.add(new Pig(600, Constants.GROUND_Y - 100));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 1. Draw Sky (Gradient-like Background)
        g.setColor(new Color(100, 200, 255));
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        // Draw Sun
        g.setColor(new Color(255, 230, 100)); // Bright yellow
        g.fillOval(Constants.SCREEN_WIDTH - 120, 40, 80, 80);

        // Draw simple Clouds
        g.setColor(Color.WHITE);
        // Cloud 1
        g.fillOval(100, 80, 60, 40);
        g.fillOval(130, 60, 70, 50);
        g.fillOval(170, 80, 60, 40);
        // Cloud 2
        g.fillOval(400, 120, 50, 30);
        g.fillOval(430, 100, 60, 40);
        g.fillOval(470, 120, 50, 30);

        // 2. Draw Ground
        // Dark dirt base
        g.setColor(new Color(101, 67, 33)); // Dark Brown
        g.fillRect(0, Constants.GROUND_Y, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT - Constants.GROUND_Y);
        // Top grassy layer
        g.setColor(new Color(50, 200, 50)); // Bright green
        g.fillRect(0, Constants.GROUND_Y, Constants.SCREEN_WIDTH, 20);

        // 3. Draw Instructions & HUD
        g.setColor(Color.BLACK);
        // harshil
        g.drawString("Drag bird to shoot. 'R' to reset. 'ESC' for menu.", 10, 20);
        // mistry
        g.drawString("Birds Remaining: " + birdsRemaining, 10, 45);

        // 4. Draw Game Entities
        slingshot.draw(g, bird, isDragging);
        bird.draw(g);

        for (Pig pig : pigs) {
            pig.draw(g);
        }

        // 5. Draw Game Over / Win Screen Overlay
        if (gameOver) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

            if (won) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.RED);
            }
            g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 48));
            String msg = won ? "YOU WIN!" : "GAME OVER";
            int msgWidth = g.getFontMetrics().stringWidth(msg);
            g.drawString(msg, (Constants.SCREEN_WIDTH - msgWidth) / 2, Constants.SCREEN_HEIGHT / 2 - 20);

            g.setColor(Color.WHITE);
            g.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 24));
            // harshil
            String subMsg = "Press 'R' to Restart Level";
            int subWidth = g.getFontMetrics().stringWidth(subMsg);
            g.drawString(subMsg, (Constants.SCREEN_WIDTH - subWidth) / 2, Constants.SCREEN_HEIGHT / 2 + 30);

            String menuMsg = "Press 'ESC' for Menu";
            int menuWidth = g.getFontMetrics().stringWidth(menuMsg);
            g.drawString(menuMsg, (Constants.SCREEN_WIDTH - menuWidth) / 2, Constants.SCREEN_HEIGHT / 2 + 60);
            // mistry
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver && birdFired) {
            bird.update();
            checkCollisions();
            checkGameState();
        }
        repaint(); // Force re-render every frame
    }

    private void checkGameState() {
        // 1. Did we win?
        boolean allPigsDead = true;
        for (Pig pig : pigs) {
            if (pig.isAlive) {
                allPigsDead = false;
                break;
            }
        }

        if (allPigsDead) {
            gameOver = true;
            won = true;
            return;
        }

        // 2. Did the bird fully stop?
        if (!bird.isFlying) {
            birdsRemaining--; // Expend a bird

            if (birdsRemaining > 0) {
                // Spawn next bird
                bird = new Bird(slingshot.position.x, slingshot.position.y);
                birdFired = false;
            } else {
                // Ran out of birds
                gameOver = true;
                won = false;
            }
        }
    }

    private void checkCollisions() {
        for (Pig pig : pigs) {
            if (pig.isAlive) {
                // simple circle collision
                if (bird.position.distance(pig.position) < bird.radius + pig.radius) {
                    pig.isAlive = false; // Pig destroyed
                    bird.velocity.x *= 0.6; // Slow down bird on impact
                    bird.velocity.y *= 0.6;
                }
            }
        }
    }

    public void resetGame() {
        initGame();
        repaint();
    }
}
