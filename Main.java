import java.awt.*;
import javax.swing.*;
//test
class Main{
    public static void main(String args[]){
        Game game = new Game();
        GameLoop gameLoop = new GameLoop(game);

        Thread gameThread = new Thread(gameLoop);   //starter game loop i en egen tråd
        gameThread.start();
    }
}