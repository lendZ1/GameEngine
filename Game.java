import java.awt.Color;


public class Game {
    public Vindu vindu;
    public GamePanel panel;
    public GameMap gamemap;
    private GameObject g1;
    private GameObject g2;
    private GameObject g3;


    public Game(){
        gamemap = new GameMap(800, 600);
        panel = new GamePanel(gamemap); 
        vindu = new Vindu(panel);

        g1 = new GameObject(gamemap, 300, 200, 50, 50, Color.RED, 1);
        g1.settFart(2, 3); // Setter fart for GameObject
        g2 = new GameObject(gamemap, 400, 300, 50, 50, Color.BLUE, 1);
        g2.settFart(1, 2); // Setter fart for GameObject
        g3 = new GameObject(gamemap, 500, 400, 50, 50, Color.GREEN, 1);
        g3.settFart(3, 1); // Setter fart for GameObject

        gamemap.addGameObject(g1, 1);
        gamemap.addGameObject(g2, 1);
        gamemap.addGameObject(g3, 2);

    }
    
}
