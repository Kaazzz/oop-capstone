package GymBro;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class GymBroIntro extends JFrame {

    private JButton preMadeBtn;
    private JButton customBtn;
    private JPanel introPanel;
    private JTextArea taImage;
    private JTextField tfUser;
    private JButton submitButton;
    private JTextArea taWelcomeMessage;

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

        // Load the image
        ImageIcon gymBroImage = loadImageIcon("gym_bro_image.png");

        // Set the image in the JTextArea
        if (gymBroImage != null) {
            setImageInTextArea(gymBroImage);
        } else {
            System.err.println("Failed to load the image.");
        }

        // Disable editing in the JTextArea
        taImage.setEditable(false);
        taImage.setOpaque(false);
        taImage.setBorder(BorderFactory.createEmptyBorder());
        taImage.setFocusable(false);
    }

    private ImageIcon loadImageIcon(String imagePath) {
        URL imageUrl = getClass().getResource(imagePath);
        if (imageUrl != null) {
            return new ImageIcon(imageUrl);
        } else {
            return null;
        }
    }

    private void setImageInTextArea(ImageIcon imageIcon) {
        StyledDocument doc = (StyledDocument) (StyledDocument) taImage.getDocument();
        Style style = doc.addStyle("StyleName", null);
        StyleConstants.setIcon(style, imageIcon);

        try {
            doc.insertString(doc.getLength(), "dummy text", style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
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
