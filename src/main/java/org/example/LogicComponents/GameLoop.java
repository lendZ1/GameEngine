package org.example.LogicComponents;

import org.example.Game;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

public class GameLoop implements Runnable {
    private static final int TICK_RATE = 60; // Ticks per second
    private static final double TICK_INTERVAL = 1000000000 / TICK_RATE; // Nanoseconds per tick
    private long window;
    private volatile boolean paused = false;
    private Game game;
    private Player player;

    public GameLoop(long window, Game game, Player player) {
        this.window=window;
        this.game=game;
        this.player=player;
    }

    public void pause(){
        paused = true;
    }
    public void resume(){
        paused = false;
    }
    public void togglePause() {
        paused = !paused;
    }

    @Override
    public void run() {

        long lastTime = System.nanoTime();
        long now;
        double delta = 0;

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Set the clear color
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0.0, 1000.0, 1000.0, 0.0, -1.0, 1.0);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        player.addImage("Player","/var/home/erlend/Documents/projects/GameEngine/src/resources/images/sprites/knight.png");


        while (!glfwWindowShouldClose(window)) {

            if (paused) {
                glfwPollEvents();
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lastTime = System.nanoTime();
                continue;
            }

            now = System.nanoTime();
            delta += (now - lastTime) / (double) TICK_INTERVAL;
            lastTime = now;

            while (delta >= 1) {
                if (paused) break; // stop processing ticks immediately when paused
                delta--;
            }

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            game.update(); // draw once per frame

            try {
                Thread.sleep(2); // optional: prevents CPU overload
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            glfwSwapBuffers(window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }

        // Cleanup
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

}