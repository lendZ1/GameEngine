package org.example;

import org.example.LogicComponents.*;
import org.example.Enums.State;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


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

        gameLoop = new GameLoop(window, this, player);

        eventHandler = new EventHandler(player, gameLoop);
        gameWindow.setEventHandler(eventHandler);

        gameMap = new GameMap(1500, 1500, windowWidth, windowHeight);
        gameMap.addGameObject(new GameObject(100, 100, 50, 50, Color.RED), 0);

        player.addSprite("src/resources/images/sprites/knight.png");
        player.defineSpriteImages(State.IDLE, new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(0, 0, 64, 64)),
                new ArrayList<>(Arrays.asList(64, 0, 64, 64)),
                new ArrayList<>(Arrays.asList(128, 0, 64, 64)),
                new ArrayList<>(Arrays.asList(0, 64, 64, 64)),
                new ArrayList<>(Arrays.asList(64, 64, 64, 64)),
                new ArrayList<>(Arrays.asList(128, 64, 64, 64)),
                new ArrayList<>(Arrays.asList(0, 128, 64, 64)),
                new ArrayList<>(Arrays.asList(64, 128, 64, 64))
        )));
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
