import java.awt.*;
import javax.swing.*;


public class Window extends JFrame {
    GamePanel gamePanel;
    public Window(GamePanel gamePanel) {
        
        setTitle("Bevegende Panel Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        this.gamePanel = gamePanel;
        add(gamePanel);
        pack(); // Adjusts the frame size to fit the preferred size of the panel
    }
}

