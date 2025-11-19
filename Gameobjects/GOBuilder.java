package GameObjects;

import java.awt.*;
import javax.swing.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.ArrayList;

public class GOBuilder {

    private int layer;
    private int xpos, ypos;
    private int xspeed, yspeed;
    private int speed;
    private Color color;
    private boolean bounce;
    private int height, width;
    private boolean movable;



    public GOBuilder layer(int layer){
        this.layer = layer;
        return this;
    }

    public GOBuilder speed(int xspeed, int yspeed){
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        return this;
    }

    public GOBuilder speed(int speed){  //sets the speed for player class, since it only needs one variable
        this.speed = speed;
        return this;
    }

    public GOBuilder position(int xpos, int ypos){
        this.xpos = xpos;
        this.ypos = ypos;
        return this;
    }

    public GOBuilder size(int height, int width){
        this.height = height;
        this.width = width;
        return this;
    }

    public GOBuilder color(Color color){
        this.color = color;
        return this;        
    }

    public GOBuilder movable(boolean movable){
        this.movable = movable;
        return this;
    }

    public GameObject build(){
        return new GameObject(xpos, ypos, height, width, xspeed, yspeed, color, layer, movable);
    }

    public Player buildPlayer(){
        return new Player(xpos, ypos, height, width, speed, color, layer);
    }
}