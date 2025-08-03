import java.awt.*;
import javax.swing.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.ArrayList;

public class Player extends GameObject {
    public Player(GameMap gamemap, int xpos, int ypos) {
        super(gamemap, xpos, ypos, 50, 50, Color.BLUE, 1);
        setBounce(false); // Player does not bounce off walls
    }

}
