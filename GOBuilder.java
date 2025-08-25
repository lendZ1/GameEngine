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

    public GOBuilder() {
    }

    public void layer(){
        this.layer = layer;
    }

    public void fart(int xfart, int yfart){
        this.xfart = xfart;
        this.yfart = yfart;
    }

    public void position(int xpos, int ypos){
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public void størrelse(int høyde, int bredde){
        this.høyde = høyde;
        this.bredde = bredde;
    }

    public void color(Color farge){
        this.farge = farge;
    }

    public GameObject build(){
        return new GameObject(xpos, ypos, høyde, bredde, farge, layer);
    }
}