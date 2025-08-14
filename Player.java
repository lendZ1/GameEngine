import java.awt.*;
import javax.swing.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.ArrayList;

public class Player extends GameObject {
    private int hastighet = 6; 
    private boolean fremover = false;
    private boolean bakover = false;
    private boolean opp = false;
    private boolean ned = false;

    public Player(GameMap gamemap, int xpos, int ypos) {
        super(gamemap, xpos, ypos, 50, 50, Color.BLUE, 1);
        setBounce(false);
    }

    public void fremover(boolean fremover) { this.fremover = fremover; }
    public void bakover(boolean bakover)   { this.bakover = bakover; }
    public void opp(boolean opp)           { this.opp = opp; }
    public void ned(boolean ned)           { this.ned = ned; }

    @Override
    public void oppdaterPosisjon() {
        int nextX = xpos;
        int nextY = ypos;

        if (fremover) nextX += hastighet;
        if (bakover)  nextX -= hastighet;
        if (opp)      nextY -= hastighet;
        if (ned)      nextY += hastighet;

        // Separate axis movement for smooth sliding along walls
        if (!kollisjonVed(nextX, ypos)) xpos = nextX;
        if (!kollisjonVed(xpos, nextY)) ypos = nextY;
    }


    // Checks whether moving to (testX, testY) would cause a collision
    private boolean kollisjonVed(int testX, int testY) {
        // Map bounds
        if (testX < 0 || testX + bredde > gamemap.bredde ||
            testY < 0 || testY + høyde > gamemap.høyde) {
            return true;
        }

        // Other objects in same layer
        for (GameObject obj : layerObjects) {
            if (obj != this) {
                if (testX < obj.xpos + obj.bredde &&
                    testX + bredde > obj.xpos &&
                    testY < obj.ypos + obj.høyde &&
                    testY + høyde > obj.ypos) {
                    return true;
                }
            }
        }
        return false;
    }
}