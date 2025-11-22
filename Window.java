import java.awt.*;
import javax.swing.*;


public class Window extends JFrame {
    GamePanel gamePanel;
    public Window(GamePanel gamePanel) {
        
        setTitle("Game Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        ImageIcon img = new ImageIcon("synpehype.jpg");
        setIconImage(img.getImage());

    }
}

