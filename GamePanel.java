import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements KeyListener {

    private GameMap gamemap;
    public Player player; // Instans av Player
    

    public GamePanel(GameMap gamemap) {
        super();
        setFocusable(true);
        addKeyListener(this); // Make sure the panel listens to keys
        requestFocusInWindow(); // Important for key events to work
        setPreferredSize(new Dimension(800, 600));
        
        this.gamemap = gamemap;
        player= new Player(gamemap, 100, 100); // Instans av Player
        gamemap.addGameObject(player, 1); // Legger til Player i GameMap
    }


    @Override
    public void paintComponent(Graphics g) {    // Tegner alle GameObjects på skjermen
        super.paintComponent(g);
        gamemap.tegn(g);
    }


    @Override
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player.opp();
            System.out.println("Player moving up");
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            player.ned();
        }   
        else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.bakover();
        }
        else if (e.getKeyCode() == KeyEvent.VK_D) {
            player.fremover();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player.ned();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            player.opp();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.fremover();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            player.bakover();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
