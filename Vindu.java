import java.awt.*;
import javax.swing.*;


public class Vindu extends JFrame {
    public Vindu() {
        
        setTitle("Bevegende Panel Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
        pack(); // Adjusts the frame size to fit the preferred size of the panel
    }
}

