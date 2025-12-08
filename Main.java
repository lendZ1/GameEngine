import java.awt.*;
import javax.swing.*;
import Engine.*;

class Main{
    static int width = 800;
    static int height = 600;
    public static void main(String args[]){
        Window window = new Window();
        UIPanel uiPanel = new UIPanel(window, width, height);
        window.add(uiPanel);
        window.setVisible(true);
        window.pack();
    }
}