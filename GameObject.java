import java.awt.*;
import javax.swing.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.ArrayList;



      
public class GameObject{
    public ArrayList<GameObject> layerObjects; // ArrayList to hold GameObjects in the same layer
    private int layer;
    public int xpos, ypos;
    private int xfart=0;
    private int yfart=0;
    private Color farge;
    private boolean bounce = true; // For å sjekke om GameObject skal sprette tilbake når den treffer kanten av vinduet
    private boolean movable;    //if the GO can be moved or not
    //bounce can be true while movable is false in case the object hits another immovable object
    public int høyde, bredde;
    public static GameMap gamemap; 


    public GameObject(int xpos, int ypos, int høyde, int bredde, int xfart, int yfart, Color farge, int layer, boolean movable) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.høyde = høyde;
        this.bredde = bredde;
        this.farge = farge;
        this.layer = layer;
        this.xfart = xfart;
        this.yfart = yfart;
        this.movable = movable;
    }

    public void setLayerObjects(ArrayList<GameObject> layerObjects) {
        this.layerObjects = layerObjects;
    }

    public void økXfart(int xfart) {
        this.xfart += xfart;
    }

    public void økYfart(int yfart) {
        this.yfart += yfart;
    }
    public void settFart(int xfart, int yfart) {
        this.xfart = xfart;
        this.yfart = yfart;
    }   

    public void oppdaterPosisjon(){
            xpos += xfart;
            ypos += yfart;
    }

    public void sjekkKollisjon() {
        if (KollisjonHorisontal()) {
            if (bounce) {
                xfart = -xfart; // Spretter tilbake
            } else {
                xfart = 0; // Stopper horisontal bevegelse
        }

        }
        if (KollisjonVertikal()) {
            if (bounce) {
                yfart = -yfart; // Spretter tilbake
            } else {
                yfart = 0; // Stopper vertikal bevegelse
            }
        }
    }

    public void tegn(Graphics g) { // Metode for å tegne GameObject på vinduet
        g.setColor(farge);
        g.fillRect(xpos, ypos, bredde, høyde);
    }


    public boolean KollisjonHorisontal(){
        if (xpos <=0) {
            registrerKollisjon();
            return true;
        } else if (xpos + bredde + this.xfart >= gamemap.bredde) {
            return true;
        }

        for (GameObject obj : layerObjects) {
            if (this != obj) { // Sjekker at det ikke er samme GameObject
                if (this.xpos + this.xfart < obj.xpos + obj.bredde &&
                    this.xpos + this.bredde + this.xfart > obj.xpos &&
                    this.ypos < obj.ypos + obj.høyde &&
                    this.ypos + this.høyde > obj.ypos) {
                    registrerKollisjon();   // Kall registrerKollisjon ved kollisjon
                    if (!obj.isMovable() && !isMovable()){  //if the other object is not movable and this object is neither
                        return true;                        //then collision will be registered
                    }                                       //Otherwise the function works as usual
                }
            }
        }
        return false;
    }

    public boolean KollisjonVertikal(){
        if (ypos <= 0) {
            registrerKollisjon();
            return true;
        } else if (ypos + høyde + this.yfart >= gamemap.høyde) {
            return true;
        }
        for (GameObject obj : layerObjects) {
            if (this != obj) { // Sjekker at det ikke er samme GameObject
                if (this.ypos + this.yfart < obj.ypos + obj.høyde &&
                    this.ypos + this.høyde + this.yfart > obj.ypos &&
                    this.xpos < obj.xpos + obj.bredde &&
                    this.xpos + this.bredde > obj.xpos) {
                    registrerKollisjon();   //Kall registrerKollisjon ved kollisjon
                    if (!obj.isMovable() && !isMovable()){  //if the other object is not movable and this object is neither
                        return true;                        //then collision will be registered
                    }                                       //Otherwise the function works as usual
                }
            }
        }
        return false;
    }

    public void setBounce(boolean bounce) {
        this.bounce = bounce;
    }  

    public void registrerKollisjon(){   //metode for at kollisjoner skal ha en effekt feks poeng
        //endre hva som skjer for ønsket effekt
    }

    public boolean isMovable(){
        return movable;
    }

}