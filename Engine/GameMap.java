package Engine;

import java.util.ArrayList;
import java.util.TreeMap;
import java.awt.Graphics;
import Engine.GameObjects.*;

public class GameMap{
    public int width, height;
    private TreeMap<Integer, ArrayList<GameObject>> layers;    // TreeMap to hold GameObjects by layer, 1 being lowest

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        layers = new TreeMap<>();
        GameObject.gamemap=this;    //sets a reference to itself from all gameobjects
    }


    public int height(){
        return height;
    }

    public int width(){
        return width;
    }


    public void update() {  //updates position of all objects 
        for (ArrayList<GameObject> objects : layers.values()) {
            for (GameObject obj : objects) {
                obj.updatePosition();
            }
        }
    }

    public void draw(Graphics g) {  //draws the new updated positions for all objects
        for (ArrayList<GameObject> objects : layers.values()) {
            for (GameObject obj : objects) {
                obj.draw(g ,0,0);
            }
        }
    }


    public GameObject addGameObject(GameObject obj, int layer) {
        layers.putIfAbsent(layer, new ArrayList<>());   //creates new layer if it doesnt already exist
        layers.get(layer).add(obj);
        obj.setLayerObjects(layers.get(layer));  //creates a copy of the array whith all the objects in the same layer
        return obj;
    }
}