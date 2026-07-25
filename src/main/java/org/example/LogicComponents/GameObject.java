package org.example.LogicComponents;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class GameObject {

    public int xpos, ypos;
    public int height, width;
    private int xspeed=0 , yspeed=0;
    private Color color;
    public static GameMap gameMap;
    private State state;
    private static java.util.ArrayList<GameObject> layerObjects;    //list of all objects on the same layer, used for collision detection

    private int sprite; //image containing all the sprites
    private HashMap<State, ArrayList<List<Integer>>> images;    //list of list, where the inner list is the 4 corners of the sprite in the image, and the outer list is a list of all the sprites for a given state
    private int spriteIndex=0;


    //distance to the closest obstacle
    protected int collisionDistanceX, collisionDistanceY;

    //how the object act when colliding
    protected boolean bounce=false;


     public GameObject(int x, int y, int height, int width, Color color){
        this.xpos = x;
        this.ypos = y;
        this.height= height;
        this.width=width;
        this.color = color;
       this.images = new HashMap<>();
     }

     public void draw(int cameraOffsetX, int cameraOffsetY){
         if (sprite != 0 && images.containsKey(state)) {
             // Draw with texture
             List<Integer> imageCoords = images.get(state).get((int) spriteIndex % images.get(state).size());
             int textureID = imageCoords.get(0);
             glEnable(GL_TEXTURE_2D);
             glBindTexture(GL_TEXTURE_2D, textureID);
             glBegin(GL_QUADS);
             // Draw quad with texture coordinates (0,0) to (1,1)
             glTexCoord2f(0, 0);
             glVertex2f(xpos - cameraOffsetX, ypos - cameraOffsetY);
             glTexCoord2f(1, 0);
             glVertex2f(xpos + width - cameraOffsetX, ypos - cameraOffsetY);
             glTexCoord2f(1, 1);
             glVertex2f(xpos + width - cameraOffsetX, ypos + height - cameraOffsetY);
             glTexCoord2f(0, 1);
             glVertex2f(xpos - cameraOffsetX, ypos + height - cameraOffsetY);
             glEnd();
             glDisable(GL_TEXTURE_2D);
         } else {
             // Draw with color fallback
             glColor3f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);
             glBegin(GL_QUADS);
             glVertex2f(xpos - cameraOffsetX, ypos - cameraOffsetY);
             glVertex2f(xpos + width - cameraOffsetX, ypos - cameraOffsetY);
             glVertex2f(xpos + width - cameraOffsetX, ypos + height - cameraOffsetY);
             glVertex2f(xpos - cameraOffsetX, ypos + height - cameraOffsetY);
             glEnd();
         }
     }


    public void updatePosition(){

        //simulates position of next tick to check for collision
        int nextX = xpos+xspeed;
        int nextY = ypos+yspeed;

        if (!bounce){
            if (!collisionAt(nextX, ypos)) xpos = nextX;    //checks horizontal collision
            else xpos += collisionDistanceX;

            if (!collisionAt(xpos, nextY)) ypos = nextY;    //checks vertical collision
            else ypos += collisionDistanceY;
        }

        else{
            if (!collisionAt(nextX, ypos)) xpos = nextX;    //checks horizontal collision
            else {
                setSpeed(-xspeed, yspeed);
                xpos += collisionDistanceX;
            }

            if (!collisionAt(xpos, nextY)) ypos = nextY;    //checks vertical collision
            else {
                setSpeed(xspeed, -yspeed);
                ypos += collisionDistanceY;
            }
        }
    }

    protected boolean collisionAt(int testX, int testY) {
        collisionDistanceX = 0;
        collisionDistanceY = 0;

        // Checks collision with map bounds:
        if (testX < 0) {
            collisionDistanceX = -xpos; // move to left edge
            return true;
        } else if (testX + width > gameMap.width()) {
            collisionDistanceX = gameMap.width() - (xpos + width); // move to right edge
            return true;
        } else if (testY < 0) {
            collisionDistanceY = -ypos; // move to top edge
            return true;
        } else if (testY + height > gameMap.height()) {
            collisionDistanceY = gameMap.height() - (ypos + height); // move to bottom edge
            return true;
        }


        //checks collision for all objects in the same layer
        for (GameObject obj : layerObjects) {
            if (obj != this) {
                // Check overlap both horizontally and vertically
                boolean overlapX = testX < obj.xpos + obj.width && testX + width > obj.xpos;
                boolean overlapY = testY < obj.ypos + obj.height && testY + height > obj.ypos;

                if (overlapX && overlapY) {
                    //calculates the distances of collision from each side of the object
                    int distLeft   = Math.abs(testX + width - obj.xpos);           // distance from left collision
                    int distRight  = Math.abs(testX - (obj.xpos + obj.width));     // distance from right collision
                    int distTop    = Math.abs(testY + height - obj.ypos);          // distance from top collision
                    int distBottom = Math.abs(testY - (obj.ypos + obj.height));    // distance from bottom collision

                    // Pick the smallest distance — that's the collision side
                    int minDist = Math.min(Math.min(distLeft, distRight), Math.min(distTop, distBottom));

                    if (minDist == distLeft) {
                        collisionDistanceX = obj.xpos - (xpos + width);
                    } else if (minDist == distRight) {
                        collisionDistanceX = (obj.xpos + obj.width) - xpos;
                    } else if (minDist == distTop) {
                        collisionDistanceY = obj.ypos - (ypos + height);
                    } else {
                        collisionDistanceY = (obj.ypos + obj.height) - ypos;
                    }

                    return true;
                }
            }
        }
        return false;
    }


    public void setSpeed(int xspeed, int yspeed) {
        this.xspeed = xspeed;
        this.yspeed = yspeed;

        if (this.yspeed>0){
            state=State.MOVING_DOWN;
        }
        else if (this.yspeed<0){
            state=State.MOVING_UP;
        }
        else if (this.xspeed>0){
            state=State.MOVING_RIGHT;
        }
        else if (this.xspeed<0){
            state=State.MOVING_LEFT;
        }
        else if (this.xspeed==0 && this.yspeed==0){
            state=State.IDLE;
        }
    }


    public void setLayerObjects(ArrayList<GameObject> layerObjects) {
        this.layerObjects = layerObjects;
    }

    public void addSprite(String loc){
         sprite=Tools.loadTexture(Tools.loadImage(loc));
    }

    public void defineSpriteImages(State state, ArrayList<List<Integer>> spriteImages) {
        images.put(state, spriteImages);
    }
}
