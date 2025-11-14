import java.util.ArrayList;
import java.util.TreeMap;
import java.awt.Graphics;

public class GameMap{
    private int width, height;
    public GamePanel gamepanel;
    private TreeMap<Integer, ArrayList<GameObject>> layers;    // TreeMap to hold GameObjects by layer
        // 1 beeing the bottom layer

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        layers = new TreeMap<>();
        GameObject.gamemap=this; // create a reference to this GameMap in GameObject
    }

    public int width(){
        return width;
    }

    public int height(){
        return height;
    }

    public void setGamePanel(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
    }

    public void update() {  // updates all GameObjects and then checks for collisions
        for (ArrayList<GameObject> objects : layers.values()) {
            for (GameObject obj : objects) {
                obj.checkCollission();
            }
        }
        
        for (ArrayList<GameObject> objects : layers.values()) {
            for (GameObject obj : objects) {
                obj.updatePosition();
            }
        }
    }

    public void draw(Graphics g) {  // draws the bottom layer first
        for (ArrayList<GameObject> objects : layers.values()) {
            for (GameObject obj : objects) {
                obj.draw(g);
            }
        }
    }


    public GameObject addGameObject(GameObject obj, int layer) {  // adds GameObject to the correct layer and creates a new ArrayList if it doesn't already exist
        layers.putIfAbsent(layer, new ArrayList<>());
        layers.get(layer).add(obj);
        obj.setLayerObjects(layers.get(layer));  // Sets layerObjects for GameObject
        return obj;
    }

    public Player addPlayer(Player player, int layer) {
        layers.putIfAbsent(layer, new ArrayList<>());
        layers.get(layer).add(player);
        player.setLayerObjects(layers.get(layer));  // Sets layerObjects for Player
        gamepanel.setPlayer(player, layer);
        return player;
    }

    public ArrayList<GameObject> getGameObjects(int layer) { 
        return layers.get(layer);  
    }
}