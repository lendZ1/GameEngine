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
}
