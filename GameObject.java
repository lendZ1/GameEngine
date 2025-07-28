import java.awt.*;
import javax.swing.*;
import java.util.concurrent.BrokenBarrierException;



      
public class GameObject{
    private int xpos, ypos;
    private int xfart=0;
    private int yfart=0;
    private Color farge;
    private boolean bounce = false; // For å sjekke om GameObject skal sprette tilbake når den treffer kanten av vinduet
    private int høyde, bredde;


    public GameObject(int xpos, int ypos, int høyde, int bredde, Color farge) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.høyde = høyde;
        this.bredde = bredde;
        this.farge = farge;
    } 

    public void settFart(int xfart, int yfart) {
        this.xfart = xfart;
        this.yfart = yfart;
    }

    public void oppdaterPosisjon(){
            xpos += xfart;
            ypos += yfart;
    }


    public void tegn(Graphics g) { // Metode for å tegne GameObject på vinduet
        g.setColor(farge);
        g.fillRect(xpos, ypos, bredde, høyde);
    }


}