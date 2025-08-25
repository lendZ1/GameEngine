import java.awt.Color;


public class Game {
    public Vindu vindu;
    public GamePanel panel;
    public GameMap gamemap;
    private GameObject g1;


    public Game(){
        gamemap = new GameMap(800, 600);
        panel = new GamePanel(gamemap); 
        vindu = new Vindu(panel);

        gamemap.addGameObject(new GOBuilder()
            .position(50, 50)
            .størrelse(50, 50)
            .color(Color.RED)
            .layer(1)
            .fart(2, 3)
            .build(), 1);
    }
    
}
