package org.example.LogicComponents;

import java.util.ArrayList;
import java.util.TreeMap;

public class GameMap{
    public int width, height;
    private Player player;
    private int cameraOffsetX=0, cameraOffsetY=0;
    private int windowWidth, windowHeight;
    private TreeMap<Integer, ArrayList<GameObject>> layers;    // TreeMap to hold GameObjects by layer, 1 being lowest

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        layers = new TreeMap<>();
        GameObject.gameMap=this;    //sets a reference to itself from all gameobjects
    }

    public void setWindowSize(int windowWidth, int windowHeight){
        this.windowWidth=windowWidth;
        this.windowHeight=windowHeight;
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

    public void draw() {  //draws the new updated positions for all objects
        for (ArrayList<GameObject> objects : layers.values()) {
            for (GameObject obj : objects) {
                obj.draw(cameraOffsetX, cameraOffsetY);
            }
        }
    }

    private void adjustCamera(){
        // Calculate target camera position to center player
        int targetCameraX = player.xpos + player.width / 2 - windowWidth / 2;
        int targetCameraY = player.ypos + player.height / 2 - windowHeight / 2;

        // Clamp to map boundaries
        targetCameraX = Math.clamp(targetCameraX, 0, width - windowWidth);
        targetCameraY = Math.clamp(targetCameraY, 0, height - windowHeight);


        // Smoothly move camera toward target instead of jumping
        if (cameraOffsetX < targetCameraX) {
            cameraOffsetX = Math.min(cameraOffsetX + player.speed, targetCameraX);
        } else if (cameraOffsetX > targetCameraX) {
            cameraOffsetX = Math.max(cameraOffsetX - player.speed, targetCameraX);
        }

        if (cameraOffsetY < targetCameraY) {
            cameraOffsetY = Math.min(cameraOffsetY + player.speed, targetCameraY);
        } else if (cameraOffsetY > targetCameraY) {
            cameraOffsetY = Math.max(cameraOffsetY - player.speed, targetCameraY);
        }
    }


    public GameObject addGameObject(GameObject obj, int layer) {
        layers.putIfAbsent(layer, new ArrayList<>());   //creates new layer if it doesn't already exist
        layers.get(layer).add(obj);
        obj.setLayerObjects(layers.get(layer));  //creates a copy of the array with all the objects in the same layer
        return obj;
    }

    public GameObject addPlayer(Player player, int layer){  //keeps track of the player object when added
        this.player=player;
        return addGameObject(player, layer);
    }
}