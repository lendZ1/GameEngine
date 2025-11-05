import java.awt.*;
import javax.swing.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;




      
public class GameObject{
    public ArrayList<GameObject> layerObjects; // ArrayList to hold GameObjects in the same layer
    private int layer;
    public int xpos, ypos;
    private int xfart=0;
    private int yfart=0;
    private Color farge;
    private boolean bounce = true; //this variable determines if the objcet should bounce back when colliding
    private boolean movable;    //if the GO can be moved or not
    //bounce can be true while movable is false in case the object hits another immovable object
    public int height, width;
    public static GameMap gamemap; 
    public BufferedImage image;


    public GameObject(int xpos, int ypos, int height, int width, int xspeed, int yspeed, Color farge, int layer, boolean movable) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.height = height;
        this.width = width;
        this.farge = farge;
        this.layer = layer;
        this.xfart = xspeed;
        this.yfart = yspeed;
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
        g.fillRect(xpos, ypos, width, height);
    }


    public boolean KollisjonHorisontal(){
        if (xpos <=0) {
            registrerKollisjon();
            return true;
        } else if (xpos + width + this.xfart >= gamemap.bredde) {
            return true;
        }

        for (GameObject obj : layerObjects) {
            if (this != obj) { // Sjekker at det ikke er samme GameObject
                if (this.xpos + this.xfart < obj.xpos + obj.width &&
                    this.xpos + this.width + this.xfart > obj.xpos &&
                    this.ypos < obj.ypos + obj.height &&
                    this.ypos + this.height > obj.ypos) {
                    registrerKollisjon();   // Kall registrerKollisjon ved kollisjon
                    if (!obj.isMovable() && !isMovable()){  //if the other object is not movable and this object is neither
                        return true;                        //then collision will be registered
                    }  else if(isMovable()){    //Otherwise if this object is movable, collision works as usual
                        return true;        
                    }                                     
                }
            }
        }
        return false;
    }

    public boolean KollisjonVertikal(){
        if (ypos <= 0) {
            registrerKollisjon();
            return true;
        } else if (ypos + height + this.yfart >= gamemap.høyde) {
            return true;
        }
        for (GameObject obj : layerObjects) {
            if (this != obj) { // Sjekker at det ikke er samme GameObject
                if (this.ypos + this.yfart < obj.ypos + obj.height &&
                    this.ypos + this.height + this.yfart > obj.ypos &&
                    this.xpos < obj.xpos + obj.width &&
                    this.xpos + this.width > obj.xpos) {
                    registrerKollisjon();   //Kall registrerKollisjon ved kollisjon
                    if (!obj.isMovable() && !isMovable()){  //if the other object is not movable and this object is neither
                        return true;                        //then collision will be registered
                    } else if(isMovable()){    //Otherwise if this object is movable, collision works as usual
                        return true;        
                    }                                        
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