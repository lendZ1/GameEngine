

public class Player extends GameObject {
    public Player(GameMap gamemap, int xpos, int ypos) {
        super(gamemap, xpos, ypos, 50, 50, Color.BLUE, 1);
        bounce = false; // Player does not bounce off walls
    }

    @Override
    public void oppdaterPosisjon() {
        super.oppdaterPosisjon();
    }
}
