import java.awt.Color;


public class Game {
    public Vindu vindu;
    public GamePanel panel;
    public GameMap gamemap;
    private GameObject gameObject;

    public Game(){
        gamemap = new GameMap(800, 600);
        panel = new GamePanel(gamemap); 
        vindu = new Vindu(panel);

        gameObject = new GameObject(gamemap, 300, 200, 50, 50, Color.RED, 1);
        gameObject.settFart(2, 3); // Setter fart for GameObject
        gamemap.addGameObject(gameObject, 1);

    }
    
}
