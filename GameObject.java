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

    public void increaseXspeed(int xspeed) {
        this.xspeed += xspeed;

        if (this.xspeed>0){
            state="right";
        }
        else if (this.xspeed<0){
            state="left";
        }
    }

    public void increaseYspeed(int yspeed) {
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
            xpos += xspeed;
            ypos += yspeed;
    }


    public void draw(Graphics g) { //draw the image or shape in the window
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




    private boolean collissionHorisontal(){
        if (xpos <=0) {
            registerCollision();
            return true;
        } else if (xpos + width + this.xspeed >= gamemap.width()) {
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
        } else if (ypos + height + this.yspeed >= gamemap.height()) {
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