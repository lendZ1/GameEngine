import java.awt.*;
import javax.swing.*;


public class Window extends JFrame {
    GamePanel gamePanel;
    public Window() {
        
        setTitle("Game window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        ImageIcon img = new ImageIcon("synpehype.jpg");
        setIconImage(img.getImage());
    }
}

