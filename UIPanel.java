import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

public class UIPanel extends JPanel {
    private Vindu vindu;
    private int width;
    private int height;

    JButton startButton;

    public UIPanel(Vindu vindu, int width, int height) {
        this.vindu = vindu;
        this.width = width;
        this.height = height;


        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to execute when the button is clicked
                System.out.println("Button clicked!");
                startGame();
            }
        });


        add(startButton);
        setPreferredSize(new Dimension(width, height));

    }

    public void startGame(){
        Game game = new Game(vindu, width, height);
        GameLoop gameLoop = new GameLoop(game);

        Thread gameThread = new Thread(gameLoop);   //starter game loop i en egen tråd
        gameThread.start();
    }
}

