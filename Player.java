import java.awt.*;
import javax.swing.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.ArrayList;

public class Player extends GameObject {
    private int hastighet=6; // Akselerasjon for spilleren
    private boolean fremover = false;
    private boolean bakover = false;
    private boolean opp = false;
    private boolean ned = false;


    public Player(GameMap gamemap, int xpos, int ypos) {
        super(gamemap, xpos, ypos, 50, 50, Color.BLUE, 1);
        setBounce(false); // Player does not bounce off walls
    }

    public void fremover(boolean fremover) {
        this.fremover = fremover;
    }
    public void bakover(boolean bakover) {
        this.bakover = bakover;
    }
    public void opp(boolean opp) {
        this.opp = opp;
    }
    public void ned(boolean ned) {
        this.ned = ned;
    }


    @Override
    public void oppdaterPosisjon() {
        if (fremover) {
            xpos += hastighet;
        }
        if (bakover) {
            xpos -= hastighet;
        }
        if (opp) {
            ypos -= hastighet;
        }
        if (ned) {
            ypos += hastighet;
        }
    }
}