import java.awt.*;
import javax.swing.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.ArrayList;

public class GOBuilder {

    public ArrayList<GameObject> layerObjects; // ArrayList to hold GameObjects in the same layer
    private int layer;
    public int xpos, ypos;
    private int xfart, yfart;
    private Color farge;
    private boolean bounce; // For å sjekke om GameObject skal sprette tilbake når den treffer kanten av vinduet
    public int høyde, bredde;


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

    public GameObject build(){
        return new GameObject(xpos, ypos, høyde, bredde, xfart, yfart, farge, layer);
    }

    public PLayer bildPlayer(){
        return new PLayer()
    }
}