package org.example.LogicComponents;

import java.awt.*;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class GameObject {

    public int xpos, ypos;
    public int height, width;
    private int xspeed=1 , yspeed=1;
    private Color color;
    public static GameMap gameMap;
    private State state;
    private static java.util.ArrayList<GameObject> layerObjects;    //list of all objects on the same layer, used for collision detection

    //public static GameMap gameMap;

    //distance to the closest obstacle
    protected int collisionDistance;

    //how the object act when colliding
    protected boolean bounce=false;


     public GameObject(int x, int y, int height, int width, Color color){
        this.xpos = x;
        this.ypos = y;
        this.height= height;
        this.width=width;
        this.color = color;
      }

     public void draw(int cameraOffsetX, int cameraOffsetY){
         glColor3f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);
         glBegin(GL_QUADS);
         glVertex2f(xpos - cameraOffsetX, ypos - cameraOffsetY);
         glVertex2f(xpos + width - cameraOffsetX, ypos - cameraOffsetY);
         glVertex2f(xpos + width - cameraOffsetX, ypos + height - cameraOffsetY);
         glVertex2f(xpos - cameraOffsetX,     ypos + height - cameraOffsetY);
         glEnd();
     }


    public void updatePosition(){
        int nextX = xpos+xspeed;
        int nextY = ypos+yspeed;

        if (!bounce){
            if (!collisionAt(nextX, ypos)) xpos = nextX;    //checks horisontal collision
            else xpos += collisionDistance;

            if (!collisionAt(xpos, nextY)) ypos = nextY;    //checks vertical collision
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
        } else if (testX + width > gameMap.width()) {
            collisionDistance = gameMap.width() - (xpos + width); // move to right edge
            return true;
        } else if (testY < 0) {
            collisionDistance = -ypos; // move to top edge
            return true;
        } else if (testY + height > gameMap.height()) {
            collisionDistance = gameMap.height() - (ypos + height); // move to bottom edge
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
}
