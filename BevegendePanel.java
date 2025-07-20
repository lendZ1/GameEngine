import java.awt.*;
import javax.swing.*;
import java.util.concurrent.BrokenBarrierException;


// Panel that can be moved around
// This class extends JPanel and can be used to create a panel that can be moved around the screen.
      
public class BevegendePanel extends JPanel implements Runnable {
    private int xpos;
    private int ypos;
    private int xfart=0;
    private int yfart=0;


    public BevegendePanel(int xpos, int ypos) {
        super();
        this.xpos = xpos;
        this.ypos = ypos;
    } 

    public void run(){  //må legge til en barrier latch her
        while (true){
            xpos += xfart;
            ypos += yfart;
        } 
    }
}