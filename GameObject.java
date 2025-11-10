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
    private boolean bounce = true; // this variable determines if the object should bounce back when colliding
    private boolean movable;    // if the GameObject can be moved or not
    // bounce can be true while movable is false in case the object hits another immovable object
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

    public void increaseXspeed(int xspeed) {
        this.xspeed += xspeed;
    }

    public void increaseYspeed(int yspeed) {
        this.yspeed += yspeed;
    }
    public void setSpeed(int xspeed, int yspeed) {
        this.xspeed = xspeed;
        this.yspeed = yspeed;
    }   

    public void updatePosition(){
            xpos += xspeed;
            ypos += yspeed;
    }

    public void checkCollission() {
        if (collissionHorisontal()) {
            if (bounce) {
                xspeed = -xspeed; // Bounces back
            } else {
                xspeed = 0; // Stops horizontal movement
            }

        }
        if (collissionVertical()) {
            if (bounce) {
                yspeed = -yspeed; // Bounces back
            } else {
                yspeed = 0; // Stops vertical movement
            }
        }
    }

    public void draw(Graphics g) { // Method to draw GameObject on the window
        g.setColor(color);
        g.fillRect(xpos, ypos, width, height);
    }

    private boolean collissionHorisontal(){
        if (xpos <=0) {
            registerCollision();
            return true;
        } else if (xpos + width + this.xspeed >= gamemap.bredde) {
            return true;
        }

        for (GameObject obj : layerObjects) {
            if (this != obj) { // Check that it is not the same GameObject
                if (this.xpos + this.xspeed < obj.xpos + obj.width &&
                    this.xpos + this.width + this.xspeed > obj.xpos &&
                    this.ypos < obj.ypos + obj.height &&
                    this.ypos + this.height > obj.ypos) {
                    registerCollision();   // Call registerCollision on collision
                    if (!obj.isMovable() && !isMovable()){  // if the other object is not movable and this object is neither
                        return true;                        // then collision will be registered
                    }  else if(isMovable()){    // Otherwise if this object is movable, collision works as usual
                        return true;        
                    }                                     
                }
            }
        }
        return false;
    }

    private boolean collissionVertical(){
        if (ypos <= 0) {
            registerCollision();
            return true;
        } else if (ypos + height + this.yspeed >= gamemap.høyde) {
            return true;
        }
        for (GameObject obj : layerObjects) {
            if (this != obj) { // Check that it is not the same GameObject
                if (this.ypos + this.yspeed < obj.ypos + obj.height &&
                    this.ypos + this.height + this.yspeed > obj.ypos &&
                    this.xpos < obj.xpos + obj.width &&
                    this.xpos + this.width > obj.xpos) {
                    registerCollision();   // Call registerCollision on collision
                    if (!obj.isMovable() && !isMovable()){  // if the other object is not movable and this object is neither
                        return true;                        // then collision will be registered
                    } else if(isMovable()){    // Otherwise if this object is movable, collision works as usual
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

    public void registerCollision(){   // method for collisions to have an effect e.g. points
        // change what happens for desired effect
    }

    public boolean isMovable(){
        return movable;
    }

}