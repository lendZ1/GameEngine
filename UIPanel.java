public class UIPanel extends JPanel {
    JBUtton startButton= new JButton("Start");
        startButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Code to execute when the button is clicked
            System.out.println("Button clicked!");
        }
    });
    public UIPanel() {
        add(startButton);
    }
}

