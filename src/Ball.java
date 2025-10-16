import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    private int x, y;
    private int radius;
    private double dx, dy; // velocity
    private Color color;
    private static final double GRAVITY = 0.4;
    private static final double DAMPING = 0.80;

    public Ball(int x, int y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.dx = 0; // initially no horizontal velocity
        this.dy = 1; // initially small downward velocity
    }

    public void update(int panelWidth, int panelHeight) {
        // Apply gravity
        dy += GRAVITY;

        // Move the ball
        y += dy;

        // Floor collision (bouncing)
        if (y + radius > panelHeight) {
            y = panelHeight - radius;
            dy = -dy * DAMPING; // Reverse and dampen velocity
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}