package org.example.LogicComponents;

import org.example.Enums.Direction;
import org.example.Enums.State;

import java.awt.*;


public class Player extends GameObject{
    
    public int speed;

    //indicates the direction the player is moving
    private boolean up=false;
    private boolean down=false;
    private boolean left=false;
    private boolean right=false;


    private State state=State.IDLE;


    public Player(int x, int y, int height, int width, Color color, int speed){
        super(x, y, height, width, color);
        this.speed=speed;
    }

    public void move(Direction direction, boolean moving) {
        switch (direction) {
            case UP:
                up=moving;
                break;
            case DOWN:
                down=moving;
                break;
            case LEFT:
                left=moving;
                break;
            case RIGHT:
                right=moving;
                break;
        }
    }

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
            state=State.MOVING_RIGHT;
        }

        if (left){
            if (up || down){
                nextX -= Math.sqrt((speed*speed)/2);
            } else{
                nextX -= speed;
            }
            state=State.MOVING_LEFT;
        }

        if (up){
            if (right || left){
                nextY -= Math.sqrt((speed*speed)/2);
            } else{
                nextY -= speed;
            }
            state=State.MOVING_UP;
        }

        if (down){
            if (right || left){
                nextY += Math.sqrt((speed*speed)/2);
            } else{
                nextY += speed;
            }
            state=State.MOVING_DOWN;
        }

        // Separate axis movement for smooth sliding along walls

        if (right || left) {
            if (!collisionAt(nextX, ypos)) xpos = nextX;
            else xpos += collisionDistanceX;
        }

        if (up || down) {
            if (!collisionAt(xpos, nextY)) ypos = nextY;
            else ypos += collisionDistanceY;
        }
    }

}

