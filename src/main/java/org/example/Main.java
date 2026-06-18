package org.example;

import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
            GameWindow gameWindow=new GameWindow();
            GameObject g1=new GameObject(100,100,50,50, Color.red);
            g1.draw();
    }
}

