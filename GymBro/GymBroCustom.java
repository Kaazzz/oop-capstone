package GymBro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GymBroCustom extends JFrame {

    private JPanel customPanel;
    private JLabel lblEnterExercise;
    private JTextField tfExercise;
    private JButton btnSubmit;
    private JTextArea taOutput;
    private JLabel lblNumberOfRepetitions;
    private JLabel lblNumberOfSets;
    private JLabel lblTimeForRecovery;
    private JTextField tfRecovery;
    private JComboBox<Integer> cbxRepetitions;
    private JComboBox<Integer> cbxSets;

    public GymBroCustom() {
        // Initialize JComboBox values
        Integer[] repetitionsValues = {1, 2, 3, 4, 5};
        Integer[] setsValues = {1, 2, 3, 4, 5};

        cbxRepetitions.setModel(new DefaultComboBoxModel<>(repetitionsValues));
        cbxSets.setModel(new DefaultComboBoxModel<>(setsValues));

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSubmitButtonClicked();
            }
        });

        this.setContentPane(this.customPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(480, 800);
        this.setTitle("GymBro Custom");
        this.setVisible(true);
    }

    private void onSubmitButtonClicked() {
        String exercise = tfExercise.getText();
        int repetitions = (int) cbxRepetitions.getSelectedItem();
        int sets = (int) cbxSets.getSelectedItem();
        int recoveryTime = Integer.parseInt(tfRecovery.getText());

        // Assuming you have a method to calculate the total time
        int totalTime = calculateTotalTime(repetitions, sets, recoveryTime);

        // Display the output in the JTextArea
        taOutput.setText("Exercise: " + exercise + "\n" +
                "Repetitions: " + repetitions + "\n" +
                "Sets: " + sets + "\n" +
                "Recovery Time: " + recoveryTime + " seconds\n" +
                "Total Time: " + totalTime + " seconds");
    }

    private int calculateTotalTime(int repetitions, int sets, int recoveryTime) {
        // Your logic to calculate total time based on repetitions, sets, and recovery time
        // For example: return repetitions * sets * recoveryTime;
        return repetitions * sets * recoveryTime;
    }

    public static void main(String[] args) {
        new GymBroCustom();
    }
}
