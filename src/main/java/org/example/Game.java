package org.example;

import org.example.LogicComponents.GameLoop;
import org.example.LogicComponents.GameMap;
import org.example.LogicComponents.GameObject;
import org.example.LogicComponents.GameWindow;
import org.lwjgl.opengl.GL;

import java.awt.*;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Game {
    GameObject gameObject;
    long window;
    GameLoop gameLoop;
    GameMap gameMap;
    public Game(){
        window=new GameWindow().provideWindow();
        gameLoop = new GameLoop(window, this);
        gameMap = new GameMap(1000, 1000);
        gameMap.addGameObject(new GameObject(100, 100, 50, 50, Color.RED), 0);

    }

    public void run(){
        gameLoop.run();
    }

    public void update(){
        gameMap.draw();
    }
}
