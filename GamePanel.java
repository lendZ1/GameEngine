import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements KeyListener {

    private GameMap gamemap;
    private Player player;
    

    public GamePanel(GameMap gamemap) {
        super();
        setFocusable(true);
        requestFocusInWindow(); // Important for key events to work
        addKeyListener(this); // Make sure the panel listens to keys
        setPreferredSize(new Dimension(800, 600));
        
        this.gamemap = gamemap;
    }


    public void setPlayer(Player player, int layer) {
        this.player = player;
    }

    @Override
    public void paintComponent(Graphics g) {    // Tegner alle GameObjects på skjermen
        super.paintComponent(g);
        gamemap.tegn(g);
    }


    @Override
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player.opp(true);
            System.out.println("Player moving up");
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            player.ned(true);
        }   
        else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.bakover(true);
        }
        else if (e.getKeyCode() == KeyEvent.VK_D) {
            player.fremover(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player.opp(false);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            player.ned(false);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.bakover(false);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            player.fremover(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
