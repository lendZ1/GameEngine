import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;


public class GamePanel extends JPanel {
    private Map map;
    
    public GamePanel(Map map) {
        this.map = map;
        super();
        setPreferredSize(new Dimension(800, 600));

    }


    @Override
    public void paintComponent(Graphics g) {    // Tegner alle GameObjects på skjermen
        map.tegn(g);
        super.paintComponent(g);
    }
}
