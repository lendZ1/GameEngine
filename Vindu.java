import java.awt.*;
import javax.swing.*;

public class Vindu {

    int høyde;
    int bredde;
    public Vindu() {
        JFrame frame = new JFrame("Simple Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        try {   //prøver å få utseende til å matche OS sitt
            UIManager.setLookAndFeel(
            UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) { System.exit(1); }
        
        frame.setVisible(true);

        // Get the screen device
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        // Set fullscreen
        gd.setFullScreenWindow(frame);      

        // Get screen size using Toolkit
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        bredde = screenSize.width;
        høyde = screenSize.height; 

        System.out.println("Screen width: " + bredde);
    }
}