import java.util.TreeMap;


public class Game {
    public Vindu vindu;
    public Map<Integer, GameObject> gameObjects;    //Holder GameObjects og hvilket lagsnivå de er på

    public Game(){
        vindu = new Vindu();
        gameObjects = new TreeMap<>();
    }
}
