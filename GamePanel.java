import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;


public class GamePanel extends JPanel {
    private ArrayList<GameObject> GO1;   //holder det nederste laget med GameObjects "bakgrunnen"
    private ArrayList<GameObject> GO2;  //holder det midterste laget med GameObjects
    private ArrayList<GameObject> GO3;  //holder det øverste laget med GameObjects

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(800, 600));
        GO1 = new ArrayList<>();
        GO2 = new ArrayList<>();
        GO3 = new ArrayList<>();
    }
    public void update() {
        for (GameObject obj : GO1) {   //Oppdaterer posisjonen til alle GameObjects
            obj.oppdaterPosisjon(); 
        }
        for (GameObject obj : GO2) {
            obj.oppdaterPosisjon();
        }
        for (GameObject obj : GO3) {
            obj.oppdaterPosisjon();
        }
    }

    @Override
    public void paintComponent(Graphics g) {    // Tegner alle GameObjects på skjermen
        super.paintComponent(g);
        for (GameObject obj : GO1) {
            obj.tegn(g);
        }
        for (GameObject obj : GO2) {
            obj.tegn(g);
        }
        for (GameObject obj : GO3) {
            obj.tegn(g);
        }
    }

    public void addGameObject(GameObject obj, int layer) {
        switch (layer) {
            case 1:
                GO1.add(obj);
                break;
            case 2:
                GO2.add(obj);
                break;
            case 3:
                GO3.add(obj);
                break;
            default:
                throw new IllegalArgumentException("Invalid layer: " + layer);
        }
    }

}
