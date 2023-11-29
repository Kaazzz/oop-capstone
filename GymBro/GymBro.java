package GymBro;

import javax.swing.*;

public class GymBro extends JFrame{
    private JTextField tfUser;
    private JButton btnUser;
    private JComboBox cbxProgram;
    private JLabel lblUser;
    private JLabel lblProgram;
    private JPanel jpanel;
    private JButton addProgramButton;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField numberOfRepsTextField;
    private JTextField setNumberTextField;
    private JTextField timerTextField;
    private JButton finishSetButton;
    private JButton finishProgramButton;
    private JTextField exerciseTextField1;
    private JComboBox comboBox1;


    public static void main(String[] args) {
        GymBro app = new GymBro();
        app.setContentPane(app.jpanel);
        JButton btn = new JButton("lol");
        app.setSize(800, 500);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setTitle("GymBro App");
        app.setVisible(true);
    }
}
