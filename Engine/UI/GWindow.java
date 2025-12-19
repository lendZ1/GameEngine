package Engine.UI;

import java.awt.*;
import javax.swing.*;


public class GWindow extends JFrame {

    public GWindow() {
        
        setTitle("Game Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        ImageIcon img = new ImageIcon("resources/images/synpehype.jpg");
        setIconImage(img.getImage());

    }
}

