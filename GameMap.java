import java.util.ArrayList;
import java.util.TreeMap;
import java.awt.Graphics;

public class GameMap{
    public int bredde, høyde;
    private TreeMap<Integer, ArrayList<GameObject>> lag;    // TreeMap to hold GameObjects by layer
        //1 er det nederste laget

    public GameMap(int bredde, int høyde) {
        this.bredde = bredde;
        this.høyde = høyde;
        lag = new TreeMap<>();
        GameObject.gamemap=this; //lager en referanse til GameMap i GameObject
    }

    public void update() {  //oppdaterer alle GameObject og så sjekker etter kollisjoner
        for (ArrayList<GameObject> objects : lag.values()) {
            for (GameObject obj : objects) {
                obj.sjekkKollisjon();
            }
        }
        
        for (ArrayList<GameObject> objects : lag.values()) {
            for (GameObject obj : objects) {
                obj.oppdaterPosisjon();
            }
        }
    }

    public void tegn(Graphics g) {  //tegner det nedeste laget først
        for (ArrayList<GameObject> objects : lag.values()) {
            for (GameObject obj : objects) {
                obj.tegn(g);
            }
        }
    }


    public void addGameObject(GameObject obj, int layer) {  //legger til GameObject i riktig lag og lager ny ArrayList hvis det ikke allerde finnes
        lag.putIfAbsent(layer, new ArrayList<>());
        lag.get(layer).add(obj);
        obj.setLayerObjects(lag.get(layer));  // Setter layerObjects for GameObject
    }

    public ArrayList<GameObject> getGameObjects(int layer) { 
        return lag.get(layer);  
    }
}