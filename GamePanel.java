import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;


public class GamePanel extends JPanel {
    private GameMap gamemap;
    
    public GamePanel(GameMap gamemap) {
        super();
        this.gamemap = gamemap;
        setPreferredSize(new Dimension(800, 600));

    }


    @Override
    public void paintComponent(Graphics g) {    // Tegner alle GameObjects på skjermen
        gamemap.tegn(g);
        super.paintComponent(g);
    }
}
