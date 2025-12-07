import java.awt.*;
import javax.swing.*;


public class Window extends JFrame {

    public Window() {
        
        setTitle("Game Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        ImageIcon img = new ImageIcon("resources/images/synpehype.jpg");
        setIconImage(img.getImage());

    }
}

