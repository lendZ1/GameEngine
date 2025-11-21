import java.util.ArrayList;
import java.util.TreeMap;
import java.awt.Graphics;

public class GameMap{
    private int width, height;
    public GamePanel gamepanel;
    private int gravity=0;    //sets the gravity acceleration where 0 equals no gravity
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

    public int setGravity(int gravity){
        this.gravity=gravity;
        return gravity;
    }

    public int getGravity(){
        return gravity;
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
                if (gravity != 0){
                    obj.adjustGravity(gravity);
                }
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


    public void addGameObject(GameObject obj, int layer) {  // adds GameObject to the correct layer and creates a new ArrayList if it doesn't already exist
        layers.putIfAbsent(layer, new ArrayList<>());
        layers.get(layer).add(obj);
        obj.setLayerObjects(layers.get(layer));  // Sets layerObjects for GameObject
    }

    public void addPlayer(Player player, int layer) {
        layers.putIfAbsent(layer, new ArrayList<>());
        layers.get(layer).add(player);
        player.setLayerObjects(layers.get(layer));  // Sets layerObjects for Player
        gamepanel.setPlayer(player, layer);
    }

    public ArrayList<GameObject> getGameObjects(int layer) { 
        return layers.get(layer);  
    }
}