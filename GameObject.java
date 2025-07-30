import java.awt.*;
import javax.swing.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.ArrayList;



      
public class GameObject{
    private ArrayList<GameObject> layerObjects; // ArrayList to hold GameObjects in the same layer
    private int layer;
    public int xpos, ypos;
    private int xfart=0;
    private int yfart=0;
    private Color farge;
    private boolean bounce = false; // For å sjekke om GameObject skal sprette tilbake når den treffer kanten av vinduet
    private int høyde, bredde;
    private GameMap gamemap; 


    public GameObject(GameMap gamemap, int xpos, int ypos, int høyde, int bredde, Color farge) {
        this.gamemap=gamemap;
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


    public boolean KollisjonHorisontal(){
        if (xpos <=0) {
            return true;
        } else if (xpos + bredde >= gamemap.bredde) {
            return true;
        }

        for (GameObject obj : layerObjects) {
            if (this != obj) { // Sjekker at det ikke er samme GameObject
                if (this.xpos < obj.xpos + obj.bredde &&
                    this.xpos + this.bredde > obj.xpos &&
                    this.ypos < obj.ypos + obj.høyde &&
                    this.ypos + this.høyde > obj.ypos) {
                    return true; // Kollisjon oppdaget
                }
            }
        }
        return false;
    }

    public boolean KollisjonVertikal(){
        if (ypos <= 0) {
            return true;
        } else if (ypos + høyde >= gamemap.høyde) {
            return true;
        }
        for (GameObject obj : layerObjects) {
            if (this != obj) { // Sjekker at det ikke er samme GameObject
                if (this.ypos < obj.ypos + obj.høyde &&
                    this.ypos + this.høyde > obj.ypos &&
                    this.xpos < obj.xpos + obj.bredde &&
                    this.xpos + this.bredde > obj.xpos) {
                    return true; // Kollisjon oppdaget
                }
            }
        }
        return false;
    }


}