package org.example;

import org.example.LogicComponents.*;
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
    EventHandler eventHandler;
    Player player;
    int windowHeight, windowWidth;

    public Game(){
        windowWidth=1000;
        windowHeight=1000;
        player = new Player(200, 200, 50, 50, Color.BLUE, 5);
        GameWindow gameWindow = new GameWindow(windowWidth, windowHeight);
        window = gameWindow.provideWindow();
        gameLoop = new GameLoop(window, this);
        eventHandler = new EventHandler(player, gameLoop);
        gameWindow.setEventHandler(eventHandler);
        gameMap = new GameMap(1500, 1500);
        gameMap.setWindowSize(windowWidth, windowHeight);
        gameMap.addGameObject(new GameObject(100, 100, 50, 50, Color.RED), 0);
        gameMap.addPlayer(player,0);

    }

    public void run(){
        gameLoop.run();
    }

    public void update(){
        gameMap.update();
        gameMap.draw();
    }
}
