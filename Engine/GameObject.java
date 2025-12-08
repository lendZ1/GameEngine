package Engine;

import java.awt.*;
import javax.swing.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.ArrayList;
import Engine.Tools.*;


public class GameObject{
    public ArrayList<GameObject> layerObjects; // ArrayList to hold GameObjects in the same layer
    private int layer;
    public int xpos, ypos;
    private int xspeed=0;
    private int yspeed=0;
    private Color color;
    public int height, width;
    public static GameMap gamemap;
    protected int collisionDistance;

    private boolean bounce = true; // For å sjekke om GameObject skal sprette tilbake når den treffer kanten av vinduet
    private boolean movable;    //if the GO can be moved or not
    //bounce can be true while movable is false in case the object hits another immovable object

    //variables that handles sprites and images:
    private HashMap<String, ArrayList<BufferedImage>> images =new HashMap<>(); 
    private int imageIndex=0;   //index to help cycle through the animations
    private int ticksPerImage=10; //how quickly the sprites cycle through the images
    private int ticksPerImageCounter=0;
    private boolean hasImage=false;
    public String state="idle"; //determines the state of the object: idle, up, down, left, right

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

        
        images.put("idleImages", new ArrayList<BufferedImage>());
        images.put("upImages", new ArrayList<BufferedImage>());
        images.put("downImages", new ArrayList<BufferedImage>());
        images.put("leftImages", new ArrayList<BufferedImage>());
        images.put("rightImages", new ArrayList<BufferedImage>());

    }

    public void addImage(String state, String filePath){
        BufferedImage image=ImageLoader.load(filePath);
        image=ImageLoader.scale(image, width, height);
        switch (state){
            case "idle":
                images.get("idleImages").add(image);
                break;
            case "up":
                images.get("upImages").add(image);
                break;
            case "down":
                images.get("downImages").add(image);
                break;
            case "left":
                images.get("leftImages").add(image);
                break;
            case "right":
                images.get("rightImages").add(image);
                break;
        }
        hasImage=true;
    }

    public void setLayerObjects(ArrayList<GameObject> layerObjects) {
        this.layerObjects = layerObjects;
    }

    public void increaseXSpeed(int xspeed) {
        this.xspeed += xspeed;

        if (this.xspeed>0){
            state="right";
        }
        else if (this.xspeed<0){
            state="left";
        }
    }

    public void increaseYSpeed(int yspeed) {
        this.yspeed += yspeed;

        if (this.yspeed>0){
            state="down";
        }
        else if (this.yspeed<0){
            state="up";
        }
    }
    public void setSpeed(int xspeed, int yspeed) {
        this.xspeed = xspeed;
        this.yspeed = yspeed;

        if (this.yspeed>0){
            state="down";
        }
        else if (this.yspeed<0){
            state="up";
        }
        else if (this.xspeed>0){
            state="right";
        }
        else if (this.xspeed<0){
            state="left";
        }
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
                setSpeed(-xspeed, yspeed);  
                xpos += collisionDistance;
            }

            if (!collisionAt(xpos, nextY)) ypos = nextY;    
            else {
                setSpeed(xspeed, -yspeed);
                ypos += collisionDistance;
            }
        }
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


    public void draw(Graphics g) { // Metode for å tegne GameObject på vinduet
        if (!hasImage){
            g.setColor(color);
            g.fillRect(xpos, ypos, width, height);
            return;
        } 

        switch(state){
            case "idle":
                getImage(g, "idleImages");
                break;
            
            case "up":
                getImage(g, "upImages");
                break;

            case "down":
                getImage(g, "downImages");
                break;

            case "left":
                getImage(g, "leftImages");
                break;

            case "right":
                getImage(g, "rightImages");
                break;
        }
    }

    private void getImage(Graphics g, String state){ //called in tegn() method to draw the correct image
        String imageList=state;
        if (images.get(imageList).size()==0){   //checks if there is an image for the correct animation, if not then gets another image from the list, checks idle first.
            for (String s: images.keySet()){
                if (images.get(s).size()!=0){
                    imageList=s;
                    break;
                }
            }
        }
        if (imageIndex>=images.get(imageList).size()){ //if the index is bigger than the array, then it is goes back to 0
            imageIndex=0;
        }
            g.drawImage(images.get(imageList).get(imageIndex), xpos, ypos, null);

            if (ticksPerImageCounter>=ticksPerImage){
                imageIndex++;
                ticksPerImageCounter=0;
            }
            ticksPerImageCounter++;
    }


    public void setBounce(boolean bounce) {
        this.bounce = bounce;
    }  

    public boolean isMovable(){
        return movable;
    }

}