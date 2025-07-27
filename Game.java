import java.awt.Color;


public class Game {
    public Vindu vindu;
    public GamePanel panel;

    public Game(){
        panel = new GamePanel(); 
        vindu = new Vindu(panel);
        GameObject obj1 = new GameObject(100, 100, 50, 50, Color.RED);
        panel.addGameObject(obj1, 1); // Add to layer 1
    }
}
