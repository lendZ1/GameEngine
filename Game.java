import java.awt.Color;


public class Game {
    public Vindu vindu;
    public GamePanel panel;
    public GameMap gamemap;

    public Game(){
        gamemap = new GameMap(800, 600);
        panel = new GamePanel(gamemap); 
        vindu = new Vindu(panel);
    }
    
}
