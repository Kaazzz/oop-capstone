package GymBro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GymBroIntro extends JFrame {

    private JButton preMadeBtn;
    private JButton customBtn;
    private JPanel introPanel;

    public GymBroIntro() {

        preMadeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GymBroPreMade preMadeFrame = new GymBroPreMade();
                preMadeFrame.setVisible(true);
                dispose();
            }
        });

        customBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GymBroCustom customFrame = new GymBroCustom();
                customFrame.setVisible(true);
                dispose();
            }
        });


    }

    public static void main(String[] args) {
        GymBroIntro introFrame = new GymBroIntro();
        introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        introFrame.setContentPane(introFrame.introPanel);
        introFrame.setSize(400, 200);
        introFrame.setTitle("GymBro Intro");
        introFrame.setVisible(true);
    }
}
