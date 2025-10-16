import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    private Ball ball;
    private Timer timer;

    public GamePanel() {
        // Create a ball instance, starting in the middle top
        ball = new Ball(400, 50, 25, Color.RED);
        
        // Set up the panel
        setBackground(Color.DARK_GRAY);

        // Start the animation timer
        timer = new Timer(16, this); // ~60 FPS
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ball.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ball.update(getWidth(), getHeight());
        repaint();
    }
}