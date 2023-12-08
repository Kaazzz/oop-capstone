package CrossyRoads;

import javax.swing.*;

public class GameWindow extends JFrame {
    private final int HEIGHT = 800;
    private final int WIDTH = 800;

    Window(boolean pause) {


        setTitle("Crossroads of Teyvat");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(new Game(pause));

        setVisible(true);
    }

    public static void main(String[] args) {

        final boolean pause = true;

        new GameWindow(pause);
    }



}

