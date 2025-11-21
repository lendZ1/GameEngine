import java.awt.Color;


public class Game {
    public Window window;
    public GamePanel panel;
    public GameMap gamemap;
    private GameObject g1;


    public Game(){
        gamemap = new GameMap(800, 600);
        panel = new GamePanel(gamemap); 
        gamemap.setGamePanel(panel);
        window = new Window(panel);


        gamemap.addPlayer(new GOBuilder()
            .position(400, 400)
            .size(50, 50)
            .speed(6)
            .color(Color.BLUE)
            .layer(1)
            .buildPlayer(), 1);

        gamemap.addGameObject(new GOBuilder()
            .position(50, 50)
            .size(50, 50)
            .color(Color.RED)
            .layer(1)
            .speed(4, 4)
            .movable(true)
            .build(), 1);

        gamemap.addGameObject(new GOBuilder()
            .position(120, 120)
            .size(50, 50)
            .color(Color.YELLOW)
            .layer(1)
            .speed(4, 4)
            .movable(false)
            .build(), 1);
        
        gamemap.addGameObject(new GOBuilder()
            .position(300, 200)
            .size(50, 50)
            .color(Color.GREEN)
            .layer(1)
            .speed(1, 1)
            .movable(true)
            .build(), 2);
    }
    
}
