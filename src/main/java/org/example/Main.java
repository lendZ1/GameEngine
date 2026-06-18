package org.example;

import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
            GameObject g1 = new GameObject(100, 100, 50, 50, Color.red);
            new GameWindow(g1);
    }
}
