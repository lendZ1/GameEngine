import java.awt.*;
import javax.swing.*;
import Engine.*;
import Engine.Tools.*;

class Main{
    static int width = 800;
    static int height = 600;
    public static void main(String args[]){
        GWindow window = new GWindow();
        UIPanel uiPanel = new UIPanel(window, width, height);
        window.add(uiPanel);
        window.setVisible(true);
        window.pack();
    }
}