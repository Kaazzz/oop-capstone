package GymBro;

import javax.swing.*;

public class GymBroCustom extends JFrame{


    private JPanel customPanel;
    private JButton lolButton;


    public GymBroCustom() {
        this.setContentPane(this.customPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(480, 800);
        this.setTitle("GymBro Custom");
        this.setVisible(true);
    }
}


