package org.example.LogicComponents;

import static org.lwjgl.glfw.GLFW.*;

public class EventHandler {
    private Player player;
    private GameLoop gameLoop;

    public EventHandler(Player player, GameLoop gameLoop) {
        this.player=player;
        this.gameLoop=gameLoop;
    }

    public void handleKeyEvent(int key, int action) {
        if (action == GLFW_PRESS) {
            if (key == GLFW_KEY_W) {
            player.move(Direction.UP, true);
            } else if (key == GLFW_KEY_S) {
            player.move(Direction.DOWN, true);
            } else if (key == GLFW_KEY_A) {
            player.move(Direction.LEFT, true);
            } else if (key == GLFW_KEY_D) {
            player.move(Direction.RIGHT, true);
            } else if (key == GLFW_KEY_ESCAPE) {
                gameLoop.togglePause();
            }
        } else if (action == GLFW_RELEASE) {
            if (key == GLFW_KEY_W) {
            player.move(Direction.UP, false);
            } else if (key == GLFW_KEY_S) {
            player.move(Direction.DOWN, false);
            } else if (key == GLFW_KEY_A) {
            player.move(Direction.LEFT, false);
            } else if (key == GLFW_KEY_D) {
            player.move(Direction.RIGHT, false);
            }
        }
    }
}
