import java.awt.Color;


public class Game {
    public Window window;
    public GamePanel panel;
    public GameMap gamemap;
    private GameObject g1;



    public Game(Window window, int width, int height) {
        this.window = window;
        gamemap = new GameMap(800, 600);
        panel = new GamePanel(gamemap, width, height);
        gamemap.setGamePanel(panel);
        window.setContentPane(panel);
        window.revalidate();
        window.repaint();
        




        Player p=new GOBuilder()
            .position(100, 100)
            .størrelse(50, 50)
            .hastighet(6)
            .color(Color.BLUE)
            .layer(1)
            .buildPlayer();
            p.addImage("idle","resources/images/WalkingSprites/boy_up_1.png");
            p.addImage("idle","resources/images/WalkingSprites/boy_up_2.png");
            p.addImage("up","resources/images/WalkingSprites/boy_up_1.png");
            p.addImage("up","resources/images/WalkingSprites/boy_up_2.png");
            p.addImage("right","resources/images/WalkingSprites/boy_right_1.png");
            p.addImage("right","resources/images/WalkingSprites/boy_right_2.png");
            p.addImage("left","resources/images/WalkingSprites/boy_left_1.png");
            p.addImage("left","resources/images/WalkingSprites/boy_left_2.png");
            p.addImage("down","resources/images/WalkingSprites/boy_down_1.png");
            p.addImage("down","resources/images/WalkingSprites/boy_down_2.png");
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
