package org.example;

import org.example.LogicComponents.GameLoop;
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
    public Game(){
        gameObject = new GameObject(100, 100, 50, 50, Color.red);
        window=new GameWindow().provideWindow();
        gameLoop = new GameLoop(window, this);


    }

    public void run(){
        gameLoop.run();
    }

    public void update(){
        gameObject.draw();
    }
}
