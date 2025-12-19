package Engine.Tools;

import java.awt.*;
import javax.swing.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.ArrayList;
import Engine.GameObjects.*;

public class GOBuilder {

    private int layer;
    private int xpos, ypos;
    private int xSpeed, ySpeed;
    private int hastighet;
    private Color color;
    private boolean bounce;
    private int heigth, width;
    private boolean movable;



    public GOBuilder layer(int layer){
        this.layer = layer;
        return this;
    }

    public GOBuilder speed(int xSpeed, int ySpeed){
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        return this;
    }

    public GOBuilder speed(int hastighet){
        this.hastighet = hastighet;
        return this;
    }

    public GOBuilder position(int xpos, int ypos){
        this.xpos = xpos;
        this.ypos = ypos;
        return this;
    }

    public GOBuilder dimensions(int heigth, int width){
        this.heigth = heigth;
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
        return new GameObject(xpos, ypos, heigth, width, xSpeed, ySpeed, color, layer, movable);
    }

    public Player buildPlayer(){
        return new Player(xpos, ypos, heigth, width, hastighet, color, layer);
    }
}