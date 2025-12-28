package Engine;

import java.util.ArrayList;
import java.util.TreeMap;
import java.awt.Graphics;
import Engine.GameObjects.*;

public class GameMap{
    public int width, height;
    private Player player;
    private int cameraOffsetX=50, cameraOffsetY=50;
    private int panelWidth, panelHeight;
    private TreeMap<Integer, ArrayList<GameObject>> layers;    // TreeMap to hold GameObjects by layer, 1 being lowest

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        layers = new TreeMap<>();
        GameObject.gamemap=this;    //sets a reference to itself from all gameobjects
    }

    public void setPanelSize(int panelWidth, int panelHeight){
        this.panelWidth=panelWidth;
        this.panelHeight=panelHeight;
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
        adjustCamera();
    }

    private void adjustCamera(){
        //horisontal checks:
        if (player.xpos-(panelWidth/2)<=0){  //checks if the camera should stop at the left border
            cameraOffsetX=0;
        } else if(player.xpos+(panelWidth/2)>=width){    //checks if the camera should stop at the right border
            cameraOffsetX=width-panelWidth;

        } else if (player.xpos-cameraOffsetX<panelWidth/3){     //keeps the player within the middle third of the camera
            cameraOffsetX-=player.speed;
        } else if (player.xpos-cameraOffsetX<2*panelWidth/3){   //keeps the player within the middle third of the camera
            cameraOffsetX+=player.speed;
        }


        //vertical checks:
        if (player.ypos-(panelHeight/2)<=0){    //checks if the camera should stop at the top border
            cameraOffsetY=0;
        } else if(player.ypos+(panelHeight/2)>=height){    //checks if the camera should stop at the bottom border
            cameraOffsetY=height-panelHeight;
            
        } else if (player.ypos-cameraOffsetY<panelHeight/3){     //keeps the player within the middle third of the camera
            cameraOffsetY-=player.speed;
        } else if (player.ypos-cameraOffsetY<2*panelHeight/3){     //keeps the player within the middle third of the camera
            cameraOffsetY+=player.speed;
        }
    }

    public void draw(Graphics g) {  //draws the new updated positions for all objects
        for (ArrayList<GameObject> objects : layers.values()) {
            for (GameObject obj : objects) {
                obj.draw(g, cameraOffsetX, cameraOffsetY);
            }
        }
    }


    public GameObject addGameObject(GameObject obj, int layer) {
        layers.putIfAbsent(layer, new ArrayList<>());   //creates new layer if it doesnt already exist
        layers.get(layer).add(obj);
        obj.setLayerObjects(layers.get(layer));  //creates a copy of the array whith all the objects in the same layer
        return obj;
    }

    public GameObject addPlayer(Player player, int layer){  //keeps track of the player object when added
        this.player=player;
        return addGameObject(player, layer);
    }
}