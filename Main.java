import java.awt.*;
import javax.swing.*;

class Main{
    static int width = 800;
    static int height = 600;
    public static void main(String args[]){
        Vindu vindu = new Vindu();
        UIPanel uiPanel = new UIPanel(vindu, width, height);
        vindu.add(uiPanel);
        vindu.setVisible(true);
        vindu.pack();
    }
}