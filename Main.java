import java.awt.*;
import javax.swing.*;
import Engine.*;
import Engine.Tools.*;
import Engine.UI.*;

class Main{
    static int width = 800;
    static int height = 600;
    public static void main(String args[]){
        GWindow window = new GWindow();
        StartMenu startMenu = new StartMenu(window, width, height);
        window.add(startMenu);
        window.setVisible(true);
        window.pack();
    }
}