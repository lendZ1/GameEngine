import java.awt.*;
import javax.swing.*;


// Panel that can be moved around
// This class extends JPanel and can be used to create a panel that can be moved around the screen.
      

public class BevegendePanel extends JPanel implemnts Runnable {
    private int xpos;
    private int ypos;
    private int xfart=0;
    private int yfart=0;


    public BevegendePanel(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
        super();
    } 

    public void run(){
        while (true){
            xpos += xfart;
            ypos += yfart;
            
            try {
                barriere.await();
            } catch (InterruptedException ex) {
                return;
            }
        }
    }
}