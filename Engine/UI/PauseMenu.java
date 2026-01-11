package Engine.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import Engine.*;

public class PauseMenu extends JPanel {
    GameLoop gameLoop;
    GamePanel gamePanel;
    GWindow window;
    
    public PauseMenu(int width, int height, Game game) {
        gameLoop=game.gameLoop;
        gamePanel=game.panel;
        window=game.window;
        

        JButton resumeButton=new JButton("Resume");
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLoop.resume();
            }
        });

        add(resumeButton);
        setPreferredSize(new Dimension(width, height));
    }
}