package Engine;

import Engine.*;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import Engine.GameObjects.*;

public class GamePanel extends JPanel implements KeyListener {

    private GameMap gamemap;
    private Player player;
    private GameLoop gameLoop;

    

    public GamePanel(Game game, int width, int height) {
        this.gamemap=game.gamemap;
        super();
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
        setPreferredSize(new Dimension(width, height));
    }

    public void setGameLoop(GameLoop gameLoop){
        this.gameLoop=gameLoop;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void paintComponent(Graphics g) {    //redraws all the gameobjects
        super.paintComponent(g);
        gamemap.draw(g);
    }


    @Override
    public void keyPressed(KeyEvent e){     //handles userinput
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player.up(true);
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            player.down(true);
        }   
        else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.left(true);
        }
        else if (e.getKeyCode() == KeyEvent.VK_D) {
            player.right(true);
        }

        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            gameLoop.pause();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {   //handles userinput
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player.up(false);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            player.down(false);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.left(false);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            player.right(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }
}
