

public class GameLoop implements Runnable {
    private static final int TICK_RATE = 60; // Ticks per second
    private static final int TICK_INTERVAL = 
    private Game game;

    public GameLoop(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long now;
        double delta = 0;

        while (true) {
            now = System.nanoTime();
            delta += (now - lastTime) / (double) TIME_PER_TICK;
            lastTime = now;

            while (delta >= 1) {
                panel.update();     // update game logic
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
