package entities;

import physics.Vector2D;
import java.awt.Color;
import java.awt.Graphics;

public class Bird {
    public Vector2D position;
    public Vector2D velocity;
    public int radius = 15;

    public boolean isFlying = false;

    public Bird(double x, double y) {
        this.position = new Vector2D(x, y);
        this.velocity = new Vector2D(0, 0);
    }

    public void update() {
        if (isFlying) {
            velocity.y += utils.Constants.GRAVITY; // Apply gravity
            position.add(velocity); // Update position

            // Basic ground collision
            if (position.y + radius >= utils.Constants.GROUND_Y) {
                position.y = utils.Constants.GROUND_Y - radius;
                velocity.y *= -0.5; // Bounce and lose energy
                velocity.x *= 0.8; // Friction on ground

                // Stop completely if moving very slow
                if (Math.abs(velocity.y) < 1 && Math.abs(velocity.x) < 1) {
                    isFlying = false;
                    velocity.x = 0;
                    velocity.y = 0;
                }
            }
        }
    }

    public void draw(Graphics g) {
        int x = (int) position.x;
        int y = (int) position.y;

        // Main Body
        g.setColor(Color.RED);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);

        // White of the Eyes
        g.setColor(Color.WHITE);
        g.fillOval(x + 1, y - 8, 10, 10); // Left eye
        g.fillOval(x + 11, y - 8, 10, 10); // Right eye

        // Angry Eyebrows
        g.setColor(Color.BLACK);
        g.fillPolygon(new int[] { x - 2, x + 10, x + 11, x - 1 }, new int[] { y - 13, y - 7, y - 5, y - 10 }, 4); // left
                                                                                                                  // brow
        g.fillPolygon(new int[] { x + 10, x + 23, x + 24, x + 12 }, new int[] { y - 7, y - 11, y - 8, y - 5 }, 4); // right
                                                                                                                   // brow

        // Pupils
        g.fillOval(x + 5, y - 5, 4, 4);
        g.fillOval(x + 13, y - 5, 4, 4);

        // Beak (Yellow Triangle)
        g.setColor(Color.YELLOW);
        int[] beakX = { x + 10, x + 24, x + 10 };
        int[] beakY = { y - 1, y + 4, y + 9 };
        g.fillPolygon(beakX, beakY, 3);

        // Beak line
        g.setColor(Color.BLACK);
        g.drawLine(x + 10, y + 4, x + 22, y + 4);
    }
}
