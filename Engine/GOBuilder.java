package Engine;

import java.awt.*;
import javax.swing.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.ArrayList;

public class GOBuilder {

    private int layer;
    private int xpos, ypos;
    private int xfart, yfart;
    private int hastighet;
    private Color farge;
    private boolean bounce;
    private int høyde, bredde;
    private boolean movable;



    public GOBuilder layer(int layer){
        this.layer = layer;
        return this;
    }

    public GOBuilder fart(int xfart, int yfart){
        this.xfart = xfart;
        this.yfart = yfart;
        return this;
    }

    public GOBuilder position(int xpos, int ypos){
        this.xpos = xpos;
        this.ypos = ypos;
        return this;
    }

    public GOBuilder størrelse(int høyde, int bredde){
        this.høyde = høyde;
        this.bredde = bredde;
        return this;
    }

    public GOBuilder color(Color farge){
        this.farge = farge;
        return this;        
    }

    public GOBuilder movable(boolean movable){
        this.movable = movable;
        return this;
    }

    public GOBuilder hastighet(int hastighet){
        this.hastighet = hastighet;
        return this;
    }

    public GameObject build(){
        return new GameObject(xpos, ypos, høyde, bredde, xfart, yfart, farge, layer, movable);
    }

    public Player buildPlayer(){
        return new Player(xpos, ypos, høyde, bredde, hastighet, farge, layer);
    }
}