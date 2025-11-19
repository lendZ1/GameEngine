package GameObjects;

import java.awt.*;
import javax.swing.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Player extends GameObject {
    private int speed; // the speed the player moves when it is moved
    private boolean forward = false;
    private boolean backward = false;
    private boolean up = false;
    private boolean down = false;
    private int collisionDistance; //prevent the player from overlapping wile also not leaving a gap
   

    public Player(int xpos, int ypos, int height, int width, int speed, Color color, int layer) {
        super(xpos, ypos, height, width, 0, 0, color, 1, false /* player is not movable by default */);
        this.speed = speed;
        setBounce(false);

         try {
            image = ImageIO.read(new File("PlaceHolder.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Methods called from GamePanel when input is detected
    public void forward(boolean forward) { this.forward = forward; }
    public void backward(boolean backward)   { this.backward = backward; }
    public void up(boolean up)           { this.up = up; }
    public void down(boolean down)       { this.down = down; }

    @Override
    public void updatePosition() {    // also checks collisions here to avoid pushing the object through colliders

        int nextX = xpos;
        int nextY = ypos;

        if (forward){
            if (up || down){
                nextX += Math.sqrt((speed*speed)/2);
            } else{
                nextX += speed;
            }
        }

        if (backward){
            if (up || down){
                nextX -= Math.sqrt((speed*speed)/2);
            } else{
                nextX -= speed;
            }
        }

        if (up){
            if (forward || backward){
                nextY -= Math.sqrt((speed*speed)/2);
            } else{
                nextY -= speed;
            }
        }
        if (down){
            if (forward || backward){
                nextY += Math.sqrt((speed*speed)/2);
            } else{
                nextY += speed;
            }
        }

        // Separate-axis movement for smooth sliding along walls

        if (forward || backward) {
            if (!collisionAt(nextX, ypos)) xpos = nextX;
            else xpos += collisionDistance;
        }

        if (up || down) {
            if (!collisionAt(xpos, nextY)) ypos = nextY;
            else ypos += collisionDistance;
        }
    }

    @Override
    public void draw(Graphics g){
        g.drawImage(image, xpos, ypos, width, height, null);
    }


    // Checks whether moving to (testX, testY) would cause a collision
    private boolean collisionAt(int testX, int testY) {
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
}
