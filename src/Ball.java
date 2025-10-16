import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {
    private int x, y;
    private int radius;
    private double dx, dy; // velocity
    private Color color;
    private static final double GRAVITY = 0.4;
    private static final double DAMPING = 0.80;
    private static final Random rand = new Random();

    public Ball(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        
        // Random initial horizontal velocity
        this.dx = rand.nextInt(7) - 3;
        this.dy = 1; // a little push to start
        
        // Random color
        this.color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
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