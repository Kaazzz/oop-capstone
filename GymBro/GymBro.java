package GymBro;

import javax.swing.*;

public class GymBro extends JFrame{
    private JTextArea taOutputToDo;
    private JTextField tfUser;
    private JButton btnUser;
    private JComboBox cbxProgram;
    private JLabel lblUser;
    private JLabel lblProgram;
    private JTextField tfExercise;
    private JButton btnExercise;
    private JTextArea taOutputFinished;
    private JLabel lblExercise;
    private JPanel jpanel;


    public static void main(String[] args) {
        GymBro app = new GymBro();
        app.setContentPane(app.jpanel);
        JButton btn = new JButton("lol");
        app.setSize(500, 300);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setTitle("GymBro App");
        app.setVisible(true);
    }
}
