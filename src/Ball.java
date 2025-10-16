import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {
    private double x, y; // Changed to double for precision
    private int radius;
    private double dx, dy; // velocity
    private Color color;
    private static final double GRAVITY = 0.4;
    private static final double DAMPING = 0.80;
    private static final double MAX_SPEED = 15.0;
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

        // Cap the speed to prevent crazy velocities
        capSpeed();

        // Move the ball
        x += dx;
        y += dy;

        // Floor collision (bouncing)
        if (y + radius > panelHeight) {
            y = panelHeight - radius;
            dy = -dy * DAMPING; // Reverse and dampen velocity
        }

        // Wall collisions
        if (x + radius > panelWidth) {
            x = panelWidth - radius;
            dx = -dx * DAMPING;
        } else if (x - radius < 0) {
            x = 0 + radius;
            dx = -dx * DAMPING;
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int)(x - radius), (int)(y - radius), radius * 2, radius * 2);
    }

    public void resolveCollision(Ball other) {
        double xDist = other.x - this.x;
        double yDist = other.y - this.y;
        double distSquared = xDist * xDist + yDist * yDist;

        // Check if balls are overlapping
        if (distSquared < (this.radius + other.radius) * (this.radius + other.radius)) {
            double distance = Math.sqrt(distSquared);
            double overlap = 0.5 * (this.radius + other.radius - distance + 1);

            // Displace balls to prevent sticking
            this.x -= overlap * (xDist / distance);
            this.y -= overlap * (yDist / distance);
            other.x += overlap * (xDist / distance);
            other.y += overlap * (yDist / distance);

            // Collision physics (elastic collision)
            double nx = (other.x - this.x) / distance;
            double ny = (other.y - this.y) / distance;
            double tx = -ny;
            double ty = nx;

            double dpTan1 = this.dx * tx + this.dy * ty;
            double dpTan2 = other.dx * tx + other.dy * ty;
            double dpNorm1 = this.dx * nx + this.dy * ny;
            double dpNorm2 = other.dx * nx + other.dy * ny;

            // Conservation of momentum (assuming equal mass)
            double m1 = dpNorm2;
            double m2 = dpNorm1;

            this.dx = tx * dpTan1 + nx * m2;
            this.dy = ty * dpTan1 + ny * m2;
            other.dx = tx * dpTan2 + nx * m1;
            other.dy = ty * dpTan2 + ny * m1;
        }
    }

    private void capSpeed() {
        double speed = Math.sqrt(dx * dx + dy * dy);
        if (speed > MAX_SPEED) {
            dx = (dx / speed) * MAX_SPEED;
            dy = (dy / speed) * MAX_SPEED;
        }
    }
}