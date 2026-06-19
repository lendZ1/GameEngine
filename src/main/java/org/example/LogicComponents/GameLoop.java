package org.example.LogicComponents;

public class GameLoop implements Runnable {
    private static final int TICK_RATE = 60; // Ticks per second
    private static final double TICK_INTERVAL = 1000000000 / TICK_RATE; // Nanoseconds per tick
    private long window;
    private volatile boolean paused = false;

    public GameLoop(long window) {
        this.window=window;
    }

    public void pause(){
        paused = true;
    }
    public void resume(){
        paused = false;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long now;
        double delta = 0;

        while (true) {
            if (paused) {
                // while paused, yield and keep the lastTime fresh so we don't accumulate delta
                while (paused) {
                    Thread.yield();
                }
                lastTime = System.nanoTime();
            }

            now = System.nanoTime();
            delta += (now - lastTime) / (double) TICK_INTERVAL;
            lastTime = now;

            while (delta >= 1) {
                if (paused) break; // stop processing ticks immediately when paused
                update();
                delta--;
            }

            try {
                Thread.sleep(2); // optional: prevents CPU overload
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update(){

    }
}