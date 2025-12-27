package Engine;

import java.awt.Color;
import Engine.GameObjects.*;
import Engine.Tools.GOBuilder;
import Engine.*;
import Engine.UI.*;


public class Game {
    public GWindow window;
    public GamePanel panel;
    public GameMap gamemap;


    public Game(GWindow window, int width, int height) {
        this.window = window;
        gamemap = new GameMap(800, 600);
        gamemap.setPanelSize(width, height);
        panel = new GamePanel(gamemap, width, height);
        window.setContentPane(panel);
        window.revalidate();
        window.repaint();

        startGame();   

        Player p=new GOBuilder()
            .position(100, 100)
            .dimensions(50, 50)
            .speed(6)
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
            panel.setPlayer(p);

        gamemap.addGameObject(new GOBuilder()
            .position(50, 50)
            .dimensions(50, 50)
            .color(Color.RED)
            .layer(1)
            .speed(4, 4)
            .movable(true)
            .build(), 1);

        gamemap.addGameObject(new GOBuilder()
            .position(200, 200)
            .dimensions(50, 50)
            .color(Color.YELLOW)
            .layer(1)
            .speed(4, 4)
            .movable(false)
            .build(), 1);
        
        gamemap.addGameObject(new GOBuilder()
            .position(300, 200)
            .dimensions(50, 50)
            .color(Color.GREEN)
            .layer(1)
            .speed(1, 1)
            .movable(true)
            .build(), 2);
    }

    private void startGame(){
        GameLoop gameLoop = new GameLoop(this);
        Thread gameThread = new Thread(gameLoop);   //starter game loop i en egen tråd
        gameThread.start();
    }     
}
