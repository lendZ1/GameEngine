import java.awt.*;
import javax.swing.*;
import java.util.TreeMap;


public class GamePanel extends JPanel {
    public TreeMap<Integer, GameObject> gameObjects;    //Holder GameObjects og hvilket lagsnivå de er på
    public GamePanel() {
        super();
        setPreferredSize(new Dimension(800, 600));
        gameObjects = new TreeMap<>();
    }
    public void update() {
        for (GameObject obj : gameObjects.values()) {
            obj.oppdaterPosisjon(); // Update position of each GameObject
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (GameObject obj : gameObjects.values()) {
            obj.tegn(g); // Draw each GameObject
        }
    }
}
