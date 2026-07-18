package org.example.LogicComponents;
import org.lwjgl.system.Platform;

import static org.lwjgl.glfw.GLFW.*;

public class EventHandler {
    private long window;
    private Player player;

    public EventHandler(long window, Player player) {
        this.window = window;
        this.player=player;
    }

    public boolean isKeyPressed(int key) {
        return glfwGetKey(window, key) == GLFW_PRESS;
    }

    public void handlePlayerInput() {
        if (isKeyPressed(GLFW_KEY_W)) player.move(Direction.UP);
        if (isKeyPressed(GLFW_KEY_S)) player.move(Direction.DOWN);
        if (isKeyPressed(GLFW_KEY_A)) player.move(Direction.LEFT);
        if (isKeyPressed(GLFW_KEY_D)) player.move(Direction.RIGHT);
    }
}
