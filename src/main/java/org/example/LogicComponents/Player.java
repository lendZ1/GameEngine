package org.example.LogicComponents;

import java.awt.*;

public class Player extends GameObject{
    public int speed;
    public Player(int x, int y, int height, int width, Color color, int speed){
        super(x, y, height, width, color);
        this.speed=speed;
    }
}
