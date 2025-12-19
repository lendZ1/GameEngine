package Engine.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import Engine.Tools.*;
import Engine.*;


public class UIPanel extends JPanel {
    private GWindow window;
    private int width;
    private int height;

    JButton startButton;

    public UIPanel(GWindow window, int width, int height) {
        this.window = window;
        this.width = width;
        this.height = height;


        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to execute when the button is clicked
                System.out.println("Button clicked!");
                startGame();
                //AudioPlayer.playAudio("resources/test.wav");
            }
        });


        add(startButton);
        setPreferredSize(new Dimension(width, height));**

    }

    private void startGame(){
        Game game = new Game(window, width, height);
    }
}

