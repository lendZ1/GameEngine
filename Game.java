import java.awt.Color;


public class Game {
    public Vindu vindu;
    public GamePanel panel;
    public Map map;

    public Game(){
        vindu = new Vindu(panel);
        map = new Map(800, 600);
        panel = new GamePanel(map); 
    }
    
}
