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
    private int kollisjonAvstand; //dersom det er en kollisjon vil den ha avstanden til veggen for å gå helt inntil
   

    public Player(GameMap gamemap, int xpos, int ypos) {
        super(gamemap, xpos, ypos, 50, 50, Color.BLUE, 1);
        setBounce(false);
    }

    public void fremover(boolean fremover) { this.fremover = fremover; }
    public void bakover(boolean bakover)   { this.bakover = bakover; }
    public void opp(boolean opp)           { this.opp = opp; }
    public void ned(boolean ned)           { this.ned = ned; }

    @Override
    public void oppdaterPosisjon() {    //sjekker også kollisjoner i denne metoden for å unngå å "dytte" objektet videre

        int nextX = xpos;
        int nextY = ypos;

        if (fremover) nextX += hastighet;
        if (bakover)  nextX -= hastighet;
        if (opp)      nextY -= hastighet;
        if (ned)      nextY += hastighet;

        // Separate axis movement for smooth sliding along walls
        if (!kollisjonVed(nextX, ypos)) xpos = nextX;
        else xpos += kollisjonAvstand;

        if (!kollisjonVed(xpos, nextY)) ypos = nextY;
        else ypos += kollisjonAvstand;
    }


    // Checks whether moving to (testX, testY) would cause a collision
    private boolean kollisjonVed(int testX, int testY) {
        kollisjonAvstand=0;
        // Map bounds
        if (testX < 0) {
            kollisjonAvstand = -xpos; // Move to edge
            return true;
        }else if(testX + bredde > gamemap.bredde) {
            kollisjonAvstand = gamemap.bredde - (xpos + bredde);
            return true;
        } else if (testY < 0) {
            kollisjonAvstand = -ypos; // Move to edge
            return true;
        } else if (testY + høyde > gamemap.høyde) {
            kollisjonAvstand = gamemap.høyde - (ypos + høyde);
            return true;
        }


        // Other objects in same layer
        for (GameObject obj : layerObjects) {
            if (obj != this) {
                if (testX < obj.xpos + obj.bredde) {
                    kollisjonAvstand = xpos-(obj.xpos + obj.bredde);
                    return true;
                }else if(testX + bredde > obj.xpos) {
                    kollisjonAvstand = obj.xpos - (xpos + bredde);
                    return true;
                } else if (testY < obj.ypos + obj.høyde) {
                    kollisjonAvstand = ypos - (obj.ypos + obj.høyde); 
                    return true;
                } else if (testY + høyde > obj.ypos) {
                    kollisjonAvstand = obj.ypos - (ypos + høyde);
                    return true;
                }
            }
        }
        return false;
    }
}