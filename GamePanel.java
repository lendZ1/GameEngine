import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {
    
    private GameMap gamemap;
    
    public GamePanel(GameMap gamemap) {
        super();
        this.gamemap = gamemap;
        setPreferredSize(new Dimension(800, 600));

    }


    @Override
    public void paintComponent(Graphics g) {    // Tegner alle GameObjects på skjermen
        super.paintComponent(g);
        gamemap.tegn(g);
    }


    @Override
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_W) {
            
        }
    }
}
