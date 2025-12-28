package Engine.GameObjects;

import java.awt.*;
import javax.swing.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Engine.*;


public class Player extends GameObject {
    public int speed; //hvor fort spilleren beveger seg når knappene blir trykket
    private boolean right = false;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;
   

    public Player(int xpos, int ypos, int height, int width, int speed, Color color, int layer) {
        super(xpos, ypos, height, width, 0, 0, color, 1, false /*player is not movable by default*/);
        this.speed = speed;
        setBounce(false);
    }

    //metodene blir kalt fra GamePanel når input registreres
    public void right(boolean right) { this.right = right; }
    public void left(boolean left)   { this.left = left; }
    public void up(boolean up)       { this.up = up; }
    public void down(boolean down)   { this.down = down; }

    @Override
    public void updatePosition() {    //sjekker også kollisjoner i denne metoden for å unngå å "dytte" objektet videre

        int nextX = xpos;
        int nextY = ypos;

        if (right){
            if (up || down){
                nextX += Math.sqrt((speed*speed)/2);
            } else{
                nextX += speed;
            }
            state="right";
        }

        if (left){
            if (up || down){
                nextX -= Math.sqrt((speed*speed)/2);
            } else{
                nextX -= speed;
            }
            state="left";
        }

        if (up){
            if (right || left){
                nextY -= Math.sqrt((speed*speed)/2);
            } else{
                nextY -= speed;
            }
            state="up";
        }

        if (down){
            if (right || left){
                nextY += Math.sqrt((speed*speed)/2);
            } else{
                nextY += speed;
            }
            state="down";
        }

        // Separate axis movement for smooth sliding along walls

        if (right || left) {
            if (!collisionAt(nextX, ypos)) xpos = nextX;
            else xpos += collisionDistance;
        }

        if (up || down) {
            if (!collisionAt(xpos, nextY)) ypos = nextY;
            else ypos += collisionDistance;
        }
    }
}
