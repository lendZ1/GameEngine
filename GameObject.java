import java.awt.*;
import javax.swing.*;
import java.util.concurrent.BrokenBarrierException;



      
public class GameObject implements Runnable {
    private int xpos;
    private int ypos;
    private int xfart=0;
    private int yfart=0;


    public GameObject(int xpos, int ypos) {
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