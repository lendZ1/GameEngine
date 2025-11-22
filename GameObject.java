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
    private boolean onGround=false;     //variable to stop gravity acceleration if the object is alrady on the ground
    private Color color;
    private boolean bounce = true; // this variable determines if the object should bounce back when colliding
    private boolean movable;    // if the GameObject can be moved or not
    // bounce can be true while movable is false in case the object hits another immovable object


    public int height, width;
    protected int collisionDistance;
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
        int nextX = xpos+xspeed;
        int nextY = ypos+yspeed;

        if (!bounce){
            if (!collisionAt(nextX, ypos)) xpos = nextX;    ///checks horisontal collision
            else xpos += collisionDistance;

            if (!collisionAt(xpos, nextY)) ypos = nextY;    ///checks vertical collision
            else ypos += collisionDistance;
        }

        else{
            if (!collisionAt(nextX, ypos)) xpos = nextX;    
            else {
                xspeed=-xspeed;
                xpos += collisionDistance;
            }

            if (!collisionAt(xpos, nextY)) ypos = nextY;    
            else {
                yspeed=-yspeed;
                ypos += collisionDistance;
            }
        }
    }


    public void draw(Graphics g) { // Method to draw GameObject on the window
        g.setColor(color);
        g.fillRect(xpos, ypos, width, height);
    }

    protected boolean collisionAt(int testX, int testY) {
        collisionDistance = 0;

        // Check map bounds:
        if (testX < 0) {
            collisionDistance = -xpos; // move to left edge
            return true;
        } else if (testX + width > gamemap.width()) {
            collisionDistance = gamemap.width() - (xpos + width); // move to right edge
            return true;
        } else if (testY < 0) {
            collisionDistance = -ypos; // move to top edge
            return true;
        } else if (testY + height > gamemap.height()) {
            collisionDistance = gamemap.height() - (ypos + height); // move to bottom edge
            return true;
        }


        for (GameObject obj : layerObjects) {
            if (obj != this) {
                    // Check overlap in both axes
                    boolean overlapX = testX < obj.xpos + obj.width && testX + width > obj.xpos;
                    boolean overlapY = testY < obj.ypos + obj.height && testY + height > obj.ypos;

                    if (overlapX && overlapY) {
                        // Determine if it's a horizontal or vertical collision
                        int distLeft   = Math.abs(testX + width - obj.xpos);           // distance from left collission
                        int distRight  = Math.abs(testX - (obj.xpos + obj.width));     // distance from right collission
                        int distTop    = Math.abs(testY + height - obj.ypos);          // distance from top collission
                        int distBottom = Math.abs(testY - (obj.ypos + obj.height));    // distance from bottom collission

                        // Pick the smallest distance — that's the collision side
                        int minDist = Math.min(Math.min(distLeft, distRight), Math.min(distTop, distBottom));

                        if (minDist == distLeft) {
                            collisionDistance = obj.xpos - (xpos + width);
                        } else if (minDist == distRight) {
                            collisionDistance = (obj.xpos + obj.width) - xpos;
                        } else if (minDist == distTop) {
                            collisionDistance = obj.ypos - (ypos + height);
                        } else {
                            collisionDistance = (obj.ypos + obj.height) - ypos;
                        }

                        return true;
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


    public int adjustGravity(int gravity){   //handles gravity acceleration
        if (!onGround){
            yspeed=yspeed+gravity;
        }
        return yspeed;
    }

}