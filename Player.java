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


        for (GameObject obj : layerObjects) {
            if (obj != this) {
                    // Check overlap in both axes
                    boolean overlapX = testX < obj.xpos + obj.bredde && testX + bredde > obj.xpos;
                    boolean overlapY = testY < obj.ypos + obj.høyde && testY + høyde > obj.ypos;

                    if (overlapX && overlapY) {
                        // Determine if it's a horizontal or vertical collision
                        int distLeft   = Math.abs(testX + bredde - obj.xpos);           // hitting from left
                        int distRight  = Math.abs(testX - (obj.xpos + obj.bredde));     // hitting from right
                        int distTop    = Math.abs(testY + høyde - obj.ypos);            // hitting from top
                        int distBottom = Math.abs(testY - (obj.ypos + obj.høyde));      // hitting from bottom

                        // Pick the smallest distance — that's the collision side
                        int minDist = Math.min(Math.min(distLeft, distRight), Math.min(distTop, distBottom));

                        if (minDist == distLeft) {
                            kollisjonAvstand = obj.xpos - (testX + bredde);
                        } else if (minDist == distRight) {
                            kollisjonAvstand = (obj.xpos + obj.bredde) - testX;
                        } else if (minDist == distTop) {
                            kollisjonAvstand = obj.ypos - (testY + høyde);
                        } else {
                            kollisjonAvstand = (obj.ypos + obj.høyde) - testY;
                        }

                        return true;
                    }
                }
            }
        return false;
    }
}
