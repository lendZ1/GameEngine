package Logic; 

public class GameLoop implements Runnable {
    private static final int TICK_RATE = 60; // Ticks per second
    private static final double TICK_INTERVAL = 1000000000 / TICK_RATE; // Nanoseconds per tick
    private Game game;
    private GamePanel panel;
    private GameMap gamemap;

    public GameLoop(Game game) {
        this.game = game;
        this.panel = game.panel; // Initialize the game panel with the game instance
        this.gamemap = game.gamemap;     // Initialize the map with the game instance
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long now;
        double delta = 0;

        while (true) {
            now = System.nanoTime();
            delta += (now - lastTime) / (double) TICK_INTERVAL;
            lastTime = now;

            while (delta >= 1) {
                gamemap.update();     // update game logic
                panel.repaint();    // request a redraw
                delta--;
            }

            try {
                Thread.sleep(2); // optional: prevents CPU overload
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
