import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
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

    public GamePanel() {
        balls = new ArrayList<>();
        
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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