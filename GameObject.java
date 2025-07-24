import java.awt.*;
import javax.swing.*;
import java.util.concurrent.BrokenBarrierException;



      
public class GameObject{
    private int xpos;
    private int ypos;
    private int xfart=0;
    private int yfart=0;
    private Color farge;

    private int høyde, bredde;


    public GameObject(int xpos, int ypos, int høyde, int bredde, Color farge) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.høyde = høyde;
        this.bredde = bredde;
        this.farge = farge;
    } 

    public void oppdaterPosisjon(){
            xpos += xfart;
            ypos += yfart;
    }


    private void tegn(Graphics g) { // Metode for å tegne GameObject på vinduet
        g.setColor(farge);
        g.fillRect(xpos, ypos, bredde, høyde);
    }
}