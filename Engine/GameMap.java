package Engine;

import java.util.ArrayList;
import java.util.TreeMap;
import java.awt.Graphics;

public class GameMap{
    public int width, height;
    public GamePanel gamepanel;
    private TreeMap<Integer, ArrayList<GameObject>> layers;    // TreeMap to hold GameObjects by layer
        //1 er det nederste laget

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        layers = new TreeMap<>();
        GameObject.gamemap=this; //lager en referanse til GameMap i GameObject
    }


    public int height(){
        return height;
    }

    public int width(){
        return width;
    }
    
    public void setGamePanel(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
    }

    public void update() {  //oppdaterer alle GameObject og så sjekker etter kollisjoner   
        for (ArrayList<GameObject> objects : layers.values()) {
            for (GameObject obj : objects) {
                obj.updatePosition();
            }
        }
    }

    public void draw(Graphics g) {  //tegner det nedeste laget først
        for (ArrayList<GameObject> objects : layers.values()) {
            for (GameObject obj : objects) {
                obj.draw(g);
            }
        }
    }


    public GameObject addGameObject(GameObject obj, int layer) {  //legger til GameObject i riktig lag og lager ny ArrayList hvis det ikke allerde finnes
        layers.putIfAbsent(layer, new ArrayList<>());
        layers.get(layer).add(obj);
        obj.setLayerObjects(layers.get(layer));  // Setter layerObjects for GameObject
        return obj;
    }

    public Player addPlayer(Player player, int layer) {
        layers.putIfAbsent(layer, new ArrayList<>());
        layers.get(layer).add(player);
        player.setLayerObjects(layers.get(layer));  // Setter layerObjects for Player
        gamepanel.setPlayer(player, layer);
        return player;
    }

    public ArrayList<GameObject> getGameObjects(int layer) { 
        return layers.get(layer);  
    }
}