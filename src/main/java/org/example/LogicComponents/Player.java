package org.example.LogicComponents;

import java.awt.*;

public class Player extends GameObject{
    public int speed;
    public Player(int x, int y, int height, int width, Color color, int speed){
        super(x, y, height, width, color);
        this.speed=speed;
    }

    public void move(Direction direction) {
        switch (direction) {
            case UP:
                ypos -= speed;
                break;
            case DOWN:
                ypos += speed;
                break;
            case LEFT:
                xpos -= speed;
                break;
            case RIGHT:
                xpos += speed;
                break;
        }
    }
}

enum Direction {
    UP, DOWN, LEFT, RIGHT
}
