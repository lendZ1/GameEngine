import java.awt.*;
import javax.swing.*;
import Engine.*;
import Engine.Tools.*;
import Engine.UI.*;

class Main{
    public static void main(String args[]){
        //logic to open the window as fullscreen
        boolean fullscreen = false;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width, height;
        if (fullscreen){
            width = (int) screenSize.getWidth();
            height = (int) screenSize.getHeight();
        } else {
            width = 800;
            height = 600;
        }

        GWindow window = new GWindow();
        StartMenu startMenu = new StartMenu(window, width, height);
        window.add(startMenu);
        window.setVisible(true);
        window.pack();
    }
}