import java.awt.*;
import javax.swing.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.ArrayList;

public class Player extends GameObject {
    private int hastighet=6; // Akselerasjon for spilleren
    public Player(GameMap gamemap, int xpos, int ypos) {
        super(gamemap, xpos, ypos, 50, 50, Color.BLUE, 1);
        setBounce(false); // Player does not bounce off walls
    }

    public void fremover() {
        økXfart(hastighet);
    }
    public void bakover() {
        økXfart(-hastighet);
    }
    public void opp() {
        økYfart(-hastighet);
    }
    public void ned() {
        økYfart(hastighet);
    }
}
