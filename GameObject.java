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
    private int xspeed = 0;
    private int yspeed = 0;
    private Color color;
    private boolean bounce = true; //this variable determines if the objcet should bounce back when colliding
    private boolean movable;    //if the GO can be moved or not
    //bounce can be true while movable is false in case the object hits another immovable object
    public int height, width;
    public static GameMap gamemap; 
    public BufferedImage image;


    public GameObject(int xpos, int ypos, int height, int width, int xspeed, int yspeed, Color color, int layer, boolean movable) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.height = height;
        this.width = width;
        this.color = color;
        this.layer = layer;
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        this.movable = movable;


    }

    public void setLayerObjects(ArrayList<GameObject> layerObjects) {
        this.layerObjects = layerObjects;
    }

    public void økXfart(int xspeed) {
        this.xspeed += xspeed;
    }

    public void økYfart(int yspeed) {
        this.yspeed += yspeed;
    }
    public void settFart(int xspeed, int yspeed) {
        this.xspeed = xspeed;
        this.yspeed = yspeed;
    }   

    public void oppdaterPosisjon(){
            xpos += xspeed;
            ypos += yspeed;
    }

    public void sjekkKollisjon() {
        if (KollisjonHorisontal()) {
            if (bounce) {
                xspeed = -xspeed; // Spretter tilbake
            } else {
                xspeed = 0; // Stopper horisontal bevegelse
        }

        }
        if (KollisjonVertikal()) {
            if (bounce) {
                yspeed = -yspeed; // Spretter tilbake
            } else {
                yspeed = 0; // Stopper vertikal bevegelse
            }
        }
    }

    public void draw(Graphics g) { // Metode for å tegne GameObject på vinduet
        g.setColor(color);
        g.fillRect(xpos, ypos, width, height);
    }


    public boolean KollisjonHorisontal(){
        if (xpos <=0) {
            registerCollision();
            return true;
        } else if (xpos + width + this.xspeed >= gamemap.bredde) {
            return true;
        }

        for (GameObject obj : layerObjects) {
            if (this != obj) { // Sjekker at det ikke er samme GameObject
                if (this.xpos + this.xspeed < obj.xpos + obj.width &&
                    this.xpos + this.width + this.xspeed > obj.xpos &&
                    this.ypos < obj.ypos + obj.height &&
                    this.ypos + this.height > obj.ypos) {
                    registerCollision();   // Kall registrerKollisjon ved kollisjon
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
            registerCollision();
            return true;
        } else if (ypos + height + this.yspeed >= gamemap.høyde) {
            return true;
        }
        for (GameObject obj : layerObjects) {
            if (this != obj) { // Sjekker at det ikke er samme GameObject
                if (this.ypos + this.yspeed < obj.ypos + obj.height &&
                    this.ypos + this.height + this.yspeed > obj.ypos &&
                    this.xpos < obj.xpos + obj.width &&
                    this.xpos + this.width > obj.xpos) {
                    registerCollision();   //Kall registrerKollisjon ved kollisjon
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

    public void registerCollision(){   //metode for at kollisjoner skal ha en effekt feks poeng
        //endre hva som skjer for ønsket effekt
    }

    public boolean isMovable(){
        return movable;
    }

}