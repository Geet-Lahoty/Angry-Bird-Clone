package entities;

import physics.Vector2D;
import java.awt.Color;
import java.awt.Graphics;

public class Pig {

    public Vector2D position;
    public int radius = 20;
    public boolean isAlive = true;
    
    public Pig(double x, double y) {
        this.position = new Vector2D(x, y);
    }
    
    public void draw(Graphics g) {

        if (isAlive) {

            int x = (int)position.x;
            int y = (int)position.y;
            
            // Ears
            g.setColor(new Color(0, 180, 0)); 
            g.fillOval(x - 18, y - 20, 14, 14); 
            g.fillOval(x + 4, y - 20, 14, 14);  
            
            g.setColor(new Color(0, 100, 0)); 
            g.fillOval(x - 15, y - 17, 8, 8);
            g.fillOval(x + 7, y - 17, 8, 8);

            // Main Body
            g.setColor(new Color(0, 255, 0)); 
            g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
            
            // Snout (nose)
            g.setColor(new Color(153, 255, 153));
            g.fillOval(x - 10, y + 2, 20, 14);
            
            // Nostrils
            g.setColor(new Color(0, 153, 0));
            g.fillOval(x - 6, y + 6, 4, 6);
            g.fillOval(x + 2, y + 6, 4, 6);
            
            // White of the Eyes
            g.setColor(Color.WHITE);
            g.fillOval(x - 14, y - 8, 12, 12); 
            g.fillOval(x + 2, y - 8, 12, 12);  
            
            // Pupils 
            g.setColor(Color.BLACK);
            g.fillOval(x - 12, y - 4, 4, 4); 
            g.fillOval(x + 4, y - 4, 4, 4); 
        }
    }
}
