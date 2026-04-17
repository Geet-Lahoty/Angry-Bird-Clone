package entities;

import physics.Vector2D;
import java.awt.Color;
import java.awt.Graphics;

public class Slingshot {
    public Vector2D position;
    public final int maxDragDistance = 100;
    
    public Slingshot(double x, double y) {
        this.position = new Vector2D(x, y);
    }
    
    public void draw(Graphics g, Bird bird, boolean isDragging) {
        int x = (int)position.x;
        int y = (int)position.y;
        
        // Rubber band back part (from right prong to bird)
        if (!bird.isFlying && isDragging) {
            g.setColor(new Color(48, 22, 8)); // Dark brown band
            g.drawLine(x + 10, y - 20, (int)bird.position.x, (int)bird.position.y);
        }
        
        // Draw Slingshot base (wooden pole)
        g.setColor(new Color(139, 69, 19)); // Brown
        g.fillRect(x - 5, y, 10, utils.Constants.GROUND_Y - y);
        
        // Draw left prong
        int[] leftProngX = {x - 5, x - 15, x - 25, x - 5};
        int[] leftProngY = {y + 5, y - 20, y - 20, y + 5};
        g.fillPolygon(leftProngX, leftProngY, 4);
        
        // Draw right prong
        int[] rightProngX = {x + 5, x + 15, x + 25, x + 5};
        int[] rightProngY = {y + 5, y - 20, y - 20, y + 5};
        g.fillPolygon(rightProngX, rightProngY, 4);
        
        // Rubber band front part (from left prong to bird)
        if (!bird.isFlying && isDragging) {
            g.setColor(new Color(48, 22, 8)); // Dark brown band
            g.drawLine(x - 15, y - 20, (int)bird.position.x, (int)bird.position.y);
        }
    }
}
