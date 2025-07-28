import java.util.ArrayList;
import java.util.TreeMap;
import java.awt.Graphics;

public class GameMap{
    private int bredde;
    private int hoyde;
    private TreeMap<Integer, ArrayList<GameObject>> lag;    // TreeMap to hold GameObjects by layer
        //1 er det nederste laget

    public GameMap(int bredde, int hoyde) {
        this.bredde = bredde;
        this.hoyde = hoyde;
        lag = new TreeMap<>();
    }

    public void update() {
        for (ArrayList<GameObject> objects : lag.values()) {
            for (GameObject obj : objects) {
                obj.oppdaterPosisjon();
            }
        }
    }

    public void tegn(Graphics g) {
        for (ArrayList<GameObject> objects : lag.values()) {
            for (GameObject obj : objects) {
                obj.tegn(g);
            }
        }
    }

    public void addGameObject(GameObject obj, int layer) {  //legger til GameObject i riktig lag og lager ny ArrayList hvis det ikke allerde finnes
        lag.putIfAbsent(layer, new ArrayList<>());
        lag.get(layer).add(obj);
    }




}