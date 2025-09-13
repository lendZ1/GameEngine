import java.awt.*;
import javax.swing.*;


public class Vindu extends JFrame {

    public Vindu() {
        
        setTitle("Game Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        ImageIcon img = new ImageIcon("synpehype.jpg");
        setIconImage(img.getImage());

    }
}

