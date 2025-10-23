import java.awt.Color;


public class Game {
    public Vindu vindu;
    public GamePanel panel;
    public GameMap gamemap;
    private GameObject g1;


    public Game(){
        gamemap = new GameMap(800, 600);
        panel = new GamePanel(gamemap); 
        gamemap.setGamePanel(panel);
        vindu = new Vindu(panel);


        Player p=new GOBuilder()
            .position(100, 100)
            .størrelse(50, 50)
            .hastighet(6)
            .color(Color.BLUE)
            .layer(1)
            .buildPlayer();
            p.addImage("idle","Walking sprites/boy_down_1.png");
            p.addImage("idle","Walking sprites/boy_down_2.png");
            gamemap.addPlayer(p,1);

        gamemap.addGameObject(new GOBuilder()
            .position(50, 50)
            .størrelse(50, 50)
            .color(Color.RED)
            .layer(1)
            .fart(4, 4)
            .movable(true)
            .build(), 1);

        gamemap.addGameObject(new GOBuilder()
            .position(200, 200)
            .størrelse(50, 50)
            .color(Color.YELLOW)
            .layer(1)
            .fart(4, 4)
            .movable(false)
            .build(), 1);
        
        gamemap.addGameObject(new GOBuilder()
            .position(300, 200)
            .størrelse(50, 50)
            .color(Color.GREEN)
            .layer(1)
            .fart(1, 1)
            .movable(true)
            .build(), 2);
    }
    
}
