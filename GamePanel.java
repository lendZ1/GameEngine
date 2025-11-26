import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements KeyListener {

    private GameMap gamemap;
    private Player player;
    

    public GamePanel(GameMap gamemap, int width, int height) {
        super();
        setFocusable(true);
        requestFocusInWindow(); // Important for key events to work
        addKeyListener(this); // Make sure the panel listens to keys
        setPreferredSize(new Dimension(width, height));
        
        this.gamemap = gamemap;
    }


    public void setPlayer(Player player, int layer) {
        this.player = player;
    }

    @Override
    public void paintComponent(Graphics g) {    // Tegner alle GameObjects på skjermen
        super.paintComponent(g);
        gamemap.draw(g);
    }


    @Override
    public void keyPressed(KeyEvent e){
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
    }

    @Override
    public void keyReleased(KeyEvent e) {
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
