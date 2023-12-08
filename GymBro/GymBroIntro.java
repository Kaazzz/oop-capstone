package GymBro;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class GymBroIntro extends JFrame {

    private JButton btnPreMade;
    private JButton btnCustom;
    private JPanel introPanel;
    private JTextArea taImage;
    private JTextField tfUser;
    private JButton submitButton;
    private JTextArea taWelcomeMessage;
    private JLabel label;

    ImageIcon banner;

    public GymBroIntro() {
        btnPreMade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GymBroPreMade preMadeFrame = new GymBroPreMade();
                preMadeFrame.setVisible(true);
                dispose();
            }
        });

        btnCustom.addActionListener(new ActionListener() {
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
        introFrame.setSize(480, 800);
        introFrame.setTitle("GymBro Intro");
        introFrame.setVisible(true);
    }
}
