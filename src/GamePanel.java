import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener, MouseListener {
    private List<Ball> balls;
    private Timer timer;
    private int frameCount = 0;
    private long lastFPSTime = 0;
    private String fpsDisplay = "FPS: 0";

    public GamePanel() {
        balls = new ArrayList<>();
        lastFPSTime = System.currentTimeMillis();
        
        // Set up the panel
        setBackground(Color.DARK_GRAY);
        addMouseListener(this);

        // Start the animation timer
        timer = new Timer(16, this); // ~60 FPS
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Ball ball : balls) {
            ball.draw(g);
        }

        // Draw HUD
        g.setColor(Color.WHITE);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        g.drawString(fpsDisplay, 10, 20);
        g.drawString("Toplar: " + balls.size(), 10, 35);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // FPS calculation
        frameCount++;
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFPSTime >= 1000) {
            fpsDisplay = "FPS: " + frameCount;
            frameCount = 0;
            lastFPSTime = currentTime;
        }

        for (int i = 0; i < balls.size(); i++) {
            Ball ball1 = balls.get(i);
            ball1.update(getWidth(), getHeight());
            for (int j = i + 1; j < balls.size(); j++) {
                Ball ball2 = balls.get(j);
                ball1.resolveCollision(ball2);
            }
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        balls.add(new Ball(e.getX(), e.getY(), 25));
    }

    // Unused mouse listener methods
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}