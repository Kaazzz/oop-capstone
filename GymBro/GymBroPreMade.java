package GymBro;

import javax.swing.*;

public class GymBroPreMade extends JFrame {
    private JPanel preMadePanel;
    private JLabel lblChooseProgram;
    private JComboBox cbxChooseProgram;
    private JTextArea taProgramsToDo;

    public GymBroPreMade() {
        this.setContentPane(this.preMadePanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(480, 800);
        this.setTitle("GymBro PreMade");
        this.setVisible(true);
    }


}

