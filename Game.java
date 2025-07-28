import java.awt.Color;


public class Game {
    public Vindu vindu;
    public GamePanel panel;

    public Game(){
        panel = new GamePanel(); 
        vindu = new Vindu(panel);
    }
}
