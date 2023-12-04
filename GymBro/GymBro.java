package GymBro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GymBro extends JFrame {
    private JTextField tfUser;
    private JButton btnUser;
    private JComboBox<String> cbxProgram;
    private JLabel lblUser;
    private JLabel lblProgram;
    private JPanel jpanel;
    private JButton btnAddProgram;
    private JTextField tfExercise;
    private JTextArea taExercise;
    private JTextArea taWelcomeUser;
    private JButton btnFinishProgram;
    private JComboBox cbxRPE;
    private JTextField tfNumberOfSets;
    private JTextField tfRestTime;
    private JTextField tfTimer;
    private JTextField tfSetNumber;
    private JTextField tfNumberOfReps;
    private JButton btnFinishSet;

    private ArrayList<String> exercises; // Store exercises for the current program

    public GymBro() {
        exercises = new ArrayList<>();

        btnUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = tfUser.getText();
                String selectedProgram = (String) cbxProgram.getSelectedItem(); // Get the selected program

                // Compare strings using equals method
                if ("Choose a program".equals(selectedProgram)) {
                    selectedProgram = "None"; // Use the assignment operator here
                }

                taWelcomeUser.setText("Welcome, " + userName + "!\nProgram: " + selectedProgram);
            }
        });




        btnAddProgram.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String exercise = tfExercise.getText();
                exercises.add(exercise);
                updateExerciseTextArea();
            }
        });

        // Add more event listeners for other buttons as needed...

        btnFinishProgram.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logic to finish the program and reset data as needed
                exercises.clear();
                updateExerciseTextArea();
            }
        });
    }

    private void updateExerciseTextArea() {
        StringBuilder exerciseText = new StringBuilder();
        for (String exercise : exercises) {
            exerciseText.append(exercise).append("\n");
        }
        taExercise.setText(exerciseText.toString());
    }

    public static void main(String[] args) {
        GymBro app = new GymBro();
        app.setContentPane(app.jpanel);
        app.setSize(800, 500);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setTitle("GymBro App");
        app.setVisible(true);
    }
}
