package CrossroadsOfTeyvat;
//Class for JFrame extension.
import javax.swing.*;



//  Window that holds the Display JPanel.

public class GameWindow extends JFrame {

    //Variable for final JFrame size.
    private final int HEIGHT = 840;
    private final int WIDTH = 814;



     // Default constructor.

    GameWindow(boolean pause) {

        //Set the title.
        setTitle("Crossroads of Teyvat");

        //Set the size of the JFrame.
        setSize(WIDTH, HEIGHT);

        //Set window to screen center.
        setLocationRelativeTo(null);

        //Specify the close button action.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set resize.
        setResizable(true);

        //Add panel to frame.
        add(new Game(pause));

        //Display the window.
        setVisible(true);


    }

      //Main constructor to start program.
    public static void main(String[] args) {

        //Pause game if first run.
        final boolean pause = true;

        //Create window for game.
        new GameWindow(pause);
    }
}