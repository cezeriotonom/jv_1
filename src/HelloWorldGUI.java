import javax.swing.*;

public class HelloWorldGUI {
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("HelloWorldGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add the game panel.
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);

        // Size and center the window.
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // this method display the JFrame to center position of a screen[[1](https://www.google.com/url?sa=E&q=https%3A%2F%2Fvertexaisearch.cloud.google.com%2Fgrounding-api-redirect%2FAUZIYQF9HreOQh030mYv4dOZdYk0YCeOrGn-h4p4sOTQT56w4guGOF0M3jYN39ylKJur4e0U2PPAGrMFvRpv-2QFxrYA_9Rj-74BlDZAF0RR46zWyXnCZoboozuFP08hLy8LE4BdcdqyZIMfI0Tis31sgDr5mJcEStl8Mec2os57G2Tf_DOx0QSB21Zbud3gp1XSPT-lVtE%3D)]

        // Display the window.
        frame.setVisible(true);
    }
}