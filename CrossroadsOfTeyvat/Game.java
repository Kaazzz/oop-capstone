package CrossroadsOfTeyvat;//Class for graphics..

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

//Class for event/key listeners.
//Class for random numbers.
//Class for array lists.
//Class for timer, JPanel and dialog boxes.

/**
 * JPanel to hold the game in the window.
 */
//@SuppressWarnings("serial")
class Game extends JPanel implements ActionListener {

    private Clip themeMusic;
    private boolean isThemeMusicPlaying = false;
    private boolean isMusicPlaying = false;
    private Clip backgroundMusic;
    private int remainingInvUses = 3;

    //Variable for the game logo 'Crossroads Of Teyvat'.
    private Sprite logo = new Sprite("Misc/LogoFull.png");
    private boolean showLogo = false;
    //New game variables.
    private boolean newGame = false;

    /*
     * Variables.
     */
    //Creates a strip generator object.
    private TerrainGenerator stripGen = new TerrainGenerator();
    //Holds Number of strips on screen.
    private int numOfStrips = 9;
    //2D array for holding sprite strips.
    private Sprite[][] allStrips = new Sprite[numOfStrips][8];
    //Holds the index values of special strip images.
    private ArrayList<Integer> special = new ArrayList<>();
    //Holds number of special images in special strip.
    private int land = 1, water = 0;
    //Array that holds the fireballs.
    private ArrayList<Sprite> fireballs = new ArrayList<>();
    //Array that holds the Pillar.
    private ArrayList<Sprite> Pillar = new ArrayList<>();
    private JButton startButton, controlsButton;

    private ManageObstacles vManager = new ManageObstacles();

    //Create hero sprite.
    private Sprite hero = new Sprite("Character/Character_down.png");

    //Variable to hold score and travel.
    private int score = 0, movement = 0;
    private Score scoreManager = new Score();

    //Variables for directional control.
    private int up = 0, down = 0, left = 0, right = 0;
    private boolean press = false;

    //Variables for hero invincibility power.
    private boolean invincibility = false;
    private int invDuration = 0, invTimeLeft = 0;

    //Create timer.
    private Timer gameLoop;

    //Create random generator.
    private Random rand = new Random();


    /**
     * Default constructor.
     */
    Game(boolean pause) {
        playThemeMusic();


        //Set layout to absolute for buttons.
        setLayout(null);

        //Create button component, set image, remove borders.
        startButton = new JButton(new ImageIcon(getClass().getResource("Misc/Start6.png")));
        startButton.setBorder(BorderFactory.createEmptyBorder());
        controlsButton = new JButton(new ImageIcon(getClass().getResource("Misc/Controls2.png")));
        controlsButton.setBorder(BorderFactory.createEmptyBorder());

        startButton.addActionListener(this);
        controlsButton.addActionListener(this);

        add(startButton);
        add(controlsButton);

        startButton.setBounds(233, 405, 318, 106);
        controlsButton.setBounds(316, 520, 158, 65);


        //Create key listener for character.
        addKeyListener(new KeyPressing());

        //Set the focus to JPanel.
        setFocusable(true);

        //Make the movement smooth.
        setDoubleBuffered(true);

        //Method to set the sprite locations.
        setInitialLocations();

        //Create the game timer and start it.
        gameLoop = new Timer(25, this);

        ///Pauses the game on first run.
        if (!pause) {
            startButton.setVisible(false);
            controlsButton.setVisible(false);
            playBackgroundMusic("Sounds/Mondstadt.wav");
            gameLoop.start();
        } else {
            showLogo = true;
        }

    }

    private void playThemeMusic() {
        try {
            // Load the audio file for the theme music
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("Sounds/Theme.wav"));

            // Get a clip resource.
            themeMusic = AudioSystem.getClip();

            // Open audio clip and load samples from the audio input stream.
            themeMusic.open(audioInputStream);

            // Start playing the theme music in a loop
            themeMusic.loop(Clip.LOOP_CONTINUOUSLY);

            // Update the flag to indicate that the theme music is now playing
            isThemeMusicPlaying = true;
        } catch (Exception e) {
            // Handle exceptions (e.g., file not found, unsupported audio format)
            e.printStackTrace();
        }
    }

    private void pauseThemeMusic() {
        // Pause the theme music if it's playing
        if (isThemeMusicPlaying && themeMusic != null && themeMusic.isRunning()) {
            themeMusic.stop();
        }
    }

    private void resumeThemeMusic() {
        // Resume the theme music if it was paused
        if (isThemeMusicPlaying && themeMusic != null) {
            themeMusic.start();
        }
    }


    private void playBackgroundMusic(String filePath) {
        try {
            // Load the audio file for the background music
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(filePath));

            // Get a clip resource.
            backgroundMusic = AudioSystem.getClip();

            // Open audio clip and load samples from the audio input stream.
            backgroundMusic.open(audioInputStream);

            // Start playing the background music in a loop
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);

            // Update the flag to indicate that the music is now playing
            isMusicPlaying = true;
        } catch (Exception e) {
            // Handle exceptions (e.g., file not found, unsupported audio format)
            e.printStackTrace();
        }
    }

    private void stopBackgroundMusic() {
        // Stop the current background music if it's playing
        if (isMusicPlaying && backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
            backgroundMusic.close();
            isMusicPlaying = false;
        }
    }

    private void pauseBackgroundMusic() {
        // Pause the background music if it's playing
        if (isMusicPlaying && backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
        }
    }

    private void resumeBackgroundMusic() {
        // Resume the background music if it was paused
        if (isMusicPlaying && backgroundMusic != null) {
            backgroundMusic.start();
        }
    }

    /**
     * Method to set the initial location of all the sprites.
     */
    private void setInitialLocations() {

        //Sets the heros location.
        hero.setXLoc(298);
        hero.setYLoc(400);


        //Initializes game with land strips.
        for (int i = 0; i < numOfStrips; i++) {

            //Creates a new land sprite strip.
            Sprite[] strip = stripGen.getLandStrip();

            //Adds sprite strip to strips array.
            allStrips[i] = strip;
        }

        //Sets a grass image under and in front of the Character location.
        //(Prevents the Character from starting on a tree or shrub)
        allStrips[5][3].setImage("Misc/Grass.png");
        allStrips[4][3].setImage("Misc/Grass.png");


        /*
         * Sets the location for the sprites in the strip array.
         */
        //Spaces sprites 100 pixels apart horizontally.
        int x = 0;
        //Spaces sprites 100 pixels apart vertically.
        int y = -100;

        for (int i = 0; i < numOfStrips; i++) {

            for (int z = 0; z < 8; z++) {

                allStrips[i][z].setXLoc(x);

                allStrips[i][z].setYLoc(y);

                x += 100;
            }
            x = 0;
            y += 100;
        }

        //Sets special array to first initialized land sprite array.
        //Prevents water/lillypad offset if it is generated right after the grass field.
        for (int i = 0; i < 8; i++) {
            if (allStrips[0][i].getFileName().equals("Misc/Grass.png")) {
                special.add(i);
                land++;
            }
        }
    }


    /**
     * Timer runs these statement on a loop.
     */
    public void actionPerformed(ActionEvent e) {

        // Pause the theme music when the game is paused
        if (!gameLoop.isRunning()) {

            resumeThemeMusic();
        } else {
            // Resume the theme music when the game is not paused
            //resumeThemeMusic();
            pauseThemeMusic();

            // Other actionPerformed code

            // Play the Mondstadt music when the game is started
            if (newGame) {
                stopBackgroundMusic();
                playBackgroundMusic("Sounds/Mondstadt.wav");
                newGame();
            }
        }

            //Makes a new game if start button is clicked.
            if (e.getSource() == startButton) {

                newGame = true;
                newGame();

            }

        //Show message dialog with controls.
        else if (e.getSource() == controlsButton) {

            JOptionPane.showMessageDialog(null, "Arrow Keys:  Move the Character." +
                    "\nCtrl:  Activates Skill: 3 seconds of Invincibility only 3 times per game." +
                    "\n         (Makes the Character pass through any object)" +
                    "\nShift:  Pause / Resume the game." +
                    "\nEnter:  Start game / Restart game while paused.");

        }
        //Runs the timer.
        else {

            //Method that prevents hero from moving onto
            //trees and checks for death and invincibility.
            heroBounds();

            //Method to smoothly move the character one block.
            jumpHero();

            //Sprite method that moves the hero.
            hero.move();


            //Method to move fireballs.
            managefireballs();

            //Method to move Pillar.
            managePillar();


            //Moves all the sprites in the sprite strips.
            for (int i = 0; i < numOfStrips; i++) {
                for (int x = 0; x < 8; x++) {
                    allStrips[i][x].move();
                }
            }

            //Method that resets the strips.
            manageStrips();


            //Method to set the scrolling speed.
            scrollScreen();

            //Assigns farthest travel to score.
            if (movement > score)
                score = movement;


            if (e.getSource() == startButton) {
                //stopBackgroundMusic();
                //playBackgroundMusic("Sounds/StartTheme.wav");
                newGame = true;
                newGame();
            }
            //Redraws sprites on screen.
            repaint();

            //Stop stuttering (linux issue).
            Toolkit.getDefaultToolkit().sync();
        }
    }



    /**
     * Method that starts a new game.
     */
    private void newGame() {

        if (newGame) {
            pauseThemeMusic();
            pauseBackgroundMusic();
            //Get this JFrame and destroy it.
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();

            //Create new main menu JFrame.
            new GameWindow(false);
        }
    }

    /**
     * Method to end game.
     * Stops loop, saves scores, displays message.
     */
    private void killMsg(String killer) {

        repaint();
        gameLoop.stop();
        scoreManager.updateScores(score);

        pauseBackgroundMusic();

        //Displays correct message based on death.
        switch (killer) {
            case "water":
                playAudio("Sounds/Jean1.wav");
                JOptionPane.showMessageDialog(null, "You drowned!" + "\nScore: " + score);
                break;
            case "tooFarDown":
                playAudio("Sounds/Jean2.wav");

                JOptionPane.showMessageDialog(null, "You were trapped!" + "\nScore: " + score);
                break;
            case "tooFarUp":
                playAudio("Sounds/Jean3.wav");
                JOptionPane.showMessageDialog(null, "You left the game!" + "\nScore: " + score);
                break;
            case "fireball":
                playAudio("Sounds/Jean2.wav");
                JOptionPane.showMessageDialog(null, "You got hit by a fireball!" + "\nScore: " + score);
                break;
            case "pillar":
                playAudio("Sounds/Jean1.wav");
                JOptionPane.showMessageDialog(null, "You got hit by a pillar!" + "\nScore: " + score);
                break;
        }

        resumeThemeMusic();
        //Show start button.
        //Start button makes new window.
        startButton.setVisible(true);
        controlsButton.setVisible(true);

        showLogo = true;
    }

    private void playAudio(String s) {
        try {
            // Load the audio file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(s));

            // Get a clip resource.
            Clip clip = AudioSystem.getClip();

            // Open audio clip and load samples from the audio input stream.
            clip.open(audioInputStream);

            // Start playing the audio
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Method that prevent hero from moving on trees, and checks
     * for death with water, pillar, or fireballs.
     */
    private void heroBounds() {

        //Invincibility countdown.
        if (invincibility) {
            invDuration++;

            if (invDuration == 1)
                invTimeLeft = 3;
            if (invDuration == 50)
                invTimeLeft = 2;
            if (invDuration == 100)
                invTimeLeft = 1;
            if (invDuration == 150) {
                invTimeLeft = 0;
                invincibility = false;

                // Reset invincibility countdown for reuse.
                if (remainingInvUses > 0) {
                    invDuration = 0;
                }
            }
        }

        //Collision method for Character.
        for (int i = 0; i < numOfStrips; i++) {
            for (Sprite s : allStrips[i]) {

                //Checks too see if user is invincible.
                if (!invincibility) {

                    //Prevents hero from jumping through trees.
                    if (s.getFileName().equals("Misc/Hydro_Slime.png") || s.getFileName().equals("Misc/Pyro_Slime.png")) {
                        if (collision(hero, s)) {

                            if ((s.getYLoc() + 100) - (hero.getYLoc()) < 5 && (s.getXLoc() + 100) - hero.getXLoc() < 125 && (s.getXLoc() + 100) - hero.getXLoc() > 20) {
                                up = 0;
                            } else if ((hero.getYLoc() + 105) > (s.getYLoc()) && (hero.getXLoc() + 100) - s.getXLoc() < 125 && (hero.getXLoc() + 100) - s.getXLoc() > 20) {
                                down = 0;
                            } else if (hero.getXLoc() - (s.getXLoc() + 100) > -5 && (s.getYLoc() + 100) - hero.getYLoc() < 125 && (s.getYLoc() + 100) - hero.getYLoc() > 20) {
                                left = 0;
                            } else if (s.getXLoc() - (hero.getXLoc() + 100) > -5 && (hero.getYLoc() + 100) - s.getYLoc() < 125 && (hero.getYLoc() + 100) - s.getYLoc() > 20) {
                                right = 0;
                            }
                        }
                    }

                    //Ends game if user lands on water.
                    if (s.getFileName().equals("Misc/Water.png")) {
                        if (s.getXLoc() - hero.getXLoc() > 0 && s.getXLoc() - hero.getXLoc() < 10) {
                            if (s.getYLoc() - hero.getYLoc() > 0 && s.getYLoc() - hero.getYLoc() < 10) {

                                //Method to end game.
                                killMsg("water");
                            }
                        }
                    }
                }


                //Ends game if user goes too far down.
                if (hero.getYLoc() > 800) {

                    //Reset hero location.
                    hero.setYLoc(500);
                    hero.setXLoc(900);

                    //Method to end game.
                    killMsg("tooFarDown");
                }

                //Ends game if user goes too far up.
                if (hero.getYLoc() < -110) {

                    //Reset hero location.
                    hero.setYLoc(500);
                    hero.setXLoc(900);

                    //Method to end game.
                    killMsg("tooFarUp");
                }
            }
        }

    }

    /**
     * Moves the character one strip forward or
     * one strip backwards WITHOUT OFF-SETTING THE
     * LOCATION DUE TO SCROLLING.
     *
     * Moves hero sprite smoothly by movement and not location.
     * up,down,left,right : number of iterations.
     * press : prevents over moving issue.
     */
    private void jumpHero() {

        //Holds the hero's location.
        int location;


        //If left/right is pressed.
        if (left > 0 && press) {
            hero.setXDir(-12.5);
            left--;
            hero.setImage("Character/Character_down.png");
        } else if (right > 0 && press) {
            hero.setXDir(12.5);
            right--;
            hero.setImage("Character/Character_down.png");
        } else if (left == 0 && right == 0 && up == 0 && down == 0) {
            hero.setXDir(0);
            press = false;
        }


        //If up is pressed.
        if (up > 0 && press) {

            //Set hero speed.
            hero.setYDir(-10);
            hero.move();
            hero.setImage("Character/Character_down.png");

            //Get hero Y location.
            location = hero.getYLoc();

            //Sets the hero's location up one strip.
            for (int i = 0; i < numOfStrips; i++) {

                Sprite x = allStrips[i][0];

                //Aligns hero to strip after movement.
                if (location - x.getYLoc() > 95 && location - x.getYLoc() < 105) {

                    hero.setYDir(0);
                    up = 0;
                    press = false;

                    hero.setYLoc(x.getYLoc() + 101);

                    //Increases travel keeper.
                    movement++;

                    i = numOfStrips;
                }
            }
        }

        //If down in pressed.
        else if (down > 0 && press) {

            //Set hero speed.
            hero.setYDir(10);
            hero.move();
            hero.setImage("Character/Character_up.png");

            //Get hero location
            location = hero.getYLoc();

            //Sets the heros location down one strip.
            for (int i = 0; i < numOfStrips; i++) {

                Sprite x = allStrips[i][0];

                //Align hero to strip after movement.
                if (location - x.getYLoc() < -95 && location - x.getYLoc() > -105) {

                    hero.setYDir(0);
                    down = 0;
                    press = false;

                    hero.setYLoc(x.getYLoc() - 99);

                    //location = x.getYLoc() - 100;

                    //Decreases travel keeper.
                    movement--;

                    i = numOfStrips;
                }
            }
        }
    }

    /**
     * Method that:
     * Moves fireballs.
     * Removes fireballs passing Y bounds.
     * Checks for fireball collisions.
     * Note: foreach not working correctly.
     */
    private void managefireballs() {

        //Cycles through fireball sprites.
        for (int i = 0; i < fireballs.size(); i++) {

            Sprite fireball = fireballs.get(i);

            //Removes fireballs passing Y bounds.
            if (fireball.getYLoc() > 800) {
                fireballs.remove(i);
            }else {

                //Moves fireball sprites.
                fireball.move();


                //Reset fireballs passing X bounds.
                if (fireball.getXLoc() < -(rand.nextInt(700) + 400)){

                    //Right to left.
                    fireball.setXDir(-(rand.nextInt(10) + 10));

                    fireball.setXLoc(900);

                    fireball.setImage(vManager.randomFireball("left"));
                } else if (fireball.getXLoc() > (rand.nextInt(700) + 1100)) {

                    //Left to right.
                    fireball.setXDir((rand.nextInt(10) + 10));

                    fireball.setXLoc(-200);

                    fireball.setImage(vManager.randomFireball("right"));
                }
            }


            //Checks for fireball collisions.
            if (collision(fireball, hero) && !invincibility) {

                //Method to end game.
                killMsg("fireball");
            }
        }


    }

    /**
     * Method that:
     * Moves Pillar.
     * Removes Pillar passing Y bounds.
     * Checks for pillar collisions.
     */
    private void managePillar() {

        //Cycles through fireball sprites.
        for (int i = 0; i < Pillar.size(); i++) {


            Sprite pillar = Pillar.get(i);


            //Removes Pillar passing Y bounds.
            if (pillar.getYLoc() > 800) {
                Pillar.remove(i);
            }else {

                //Moves pillar sprites.
                pillar.move();


                //Reset X bounds.
                if (pillar.getXLoc() < -(rand.nextInt(2500) + 2600)) {
                    pillar.setXDir(-(rand.nextInt(10) + 30));

                    pillar.setXLoc(900);

                    pillar.setImage(vManager.randomPillar());
                } else if (pillar.getXLoc() > rand.nextInt(2500) + 1800) {
                    pillar.setXDir((rand.nextInt(10) + 30));

                    pillar.setXLoc(-1500);

                    pillar.setImage(vManager.randomPillar());
                }

            }


            //Checks for pillar collisions.
            if (collision(pillar, hero) && !invincibility) {

                //Method to end game.
                killMsg("pillar");
            }
        }

    }

    /**
     * Method that correctly resets the strips.
     */
    private void manageStrips() {

        //Blank strip test variables.
        int allWater;
        int allGrass;


        //Cycles through each strip.
        for (int v = 0; v < numOfStrips; v++) {

            //Checks if strip is out of bounds.
            if (allStrips[v][0].getYLoc() > 800) {

                //Generates a new strip.
                allStrips[v] = stripGen.getStrip();


                //Prevents an all water or grass strip.
                do {
                    //Reset variables.
                    allWater = 0;
                    allGrass = 0;

                    //Check sprites in strip.
                    for (Sprite s : allStrips[v]) {
                        if (s.getFileName().equals("Misc/Water.png"))
                            allWater++;
                        if (s.getFileName().equals("Misc/Grass.png"))
                            allGrass++;
                    }

                    if (allWater == 8)
                        allStrips[v] = stripGen.getWaterStrip();
                    if (allGrass == 8)
                        allStrips[v] = stripGen.getLandStrip();


                } while (allWater == 8 || allGrass == 8);


                //If there was previously a water strip, and this strip is a water strip, match this strips lillypads to the previous strip.
                if (water > 0) {
                    if (allStrips[v][0].getFileName().equals("Misc/Water.png") || allStrips[v][0].getFileName().equals("Misc/Rock.png")) {

                        water = 0;

                        for (int i : special) {
                            allStrips[v][i].setImage("Misc/Rock.png");
                        }
                    }
                }

                //If there was previously a water strip, and this strip is a land strip, match the grass to the previous strips lillypads.
                if (water > 0) {
                    if (allStrips[v][0].getFileName().equals("Misc/Grass.png") || allStrips[v][0].getFileName().equals("Misc/Dendro_Slime.png") ||
                            allStrips[v][0].getFileName().equals("Misc/Hydro_Slime.png") || allStrips[v][0].getFileName().equals("Misc/Pyro_Slime.png")) {

                        allStrips[v] = stripGen.getSpecialLandStrip();

                        water = 0;

                        for (int i : special) {
                            allStrips[v][i].setImage("Misc/Grass.png");
                        }
                    }
                }

                //If there was previously a land strip, and this strip is a water strip, match the lillypads to the grass.
                if (land > 0) {
                    if (allStrips[v][0].getFileName().equals("Misc/Water.png") || allStrips[v][0].getFileName().equals("LilyPad.png")) {

                        land = 0;

                        int val = 0;

                        while (val == 0) {

                            allStrips[v] = stripGen.getWaterStrip();

                            for (int i = 0; i < 8; i++) {
                                if (allStrips[v][i].getFileName().equals("Misc/Rock.png")) {
                                    //TODO: Remove
                                    for(int s : special){
                                        if (i == s) {
                                            val++;
                                        }

                                    }
                                }
                            }
                        }

                    }
                }


                //if there is a water strip, write down the index of the Lillypads.
                if (allStrips[v][0].getFileName().equals("Misc/Water.png") || allStrips[v][0].getFileName().equals("Misc/Rock.png")) {

                    special.clear();

                    water = 0;

                    for (int i = 0; i < 8; i++) {
                        if (allStrips[v][i].getFileName().equals("Misc/Rock.png")) {
                            special.add(i);
                            water++;
                        }
                    }
                } else
                    water = 0;

                //if there is a land strip, write down the index of the grass.
                if (allStrips[v][0].getFileName().equals("Misc/Grass.png") || allStrips[v][0].getFileName().equals("Misc/Dendro_Slime.png") ||
                        allStrips[v][0].getFileName().equals("Misc/Hydro_Slime.png") || allStrips[v][0].getFileName().equals("Misc/Pyro_Slime.png")) {

                    special.clear();

                    land = 0;

                    for (int i = 0; i < 8; i++) {
                        if (allStrips[v][i].getFileName().equals("Misc/Grass.png")) {
                            special.add(i);
                            land++;
                        }
                    }
                }


                //Variable to reset horizontal strip location.
                int X = 0;

                //Reset the location of the strip.
                for (int i = 0; i < 8; i++) {

                    allStrips[v][i].setYLoc(-99);
                    allStrips[v][i].setXLoc(X);

                    X += 100;
                }

                //Set fireball.
                if (allStrips[v][0].getFileName().equals("Misc/Road.png")){
                    fireballs.add(vManager.setFireball(allStrips[v][0].getYLoc() + 10));
                }

                //Set pillar.
                if (allStrips[v][0].getFileName().equals("Misc/Tracks.png")) {
                    Pillar.add(vManager.setPillar(allStrips[v][0].getYLoc() + 10));
                }
            }
        }
    }



    /**
     * Scrolls the strips and the hero.
     */
    private void scrollScreen() {

        //Cycles through strip array.
        for (int v = 0; v < numOfStrips; v++) {
            for (int x = 0; x < 8; x++) {
                allStrips[v][x].setYDir(2);
            }
        }
        //Sets scrolling if hero is not moving.
        if (!press) {
            hero.setYDir(2);
        }
    }

    /**
     * Checks for sprite collisions.
     */
    private boolean collision(Sprite one, Sprite two) {

        //Creates rectangles around sprites and checks for interesection.
        Rectangle first = new Rectangle(one.getXLoc(), one.getYLoc(), one.getWidth(), one.getHeight());
        Rectangle second = new Rectangle(two.getXLoc(), two.getYLoc(), two.getWidth(), two.getHeight());

        return first.intersects(second);
    }

    /**
     * Draws graphics onto screen.
     */
    public void paintComponent(Graphics g) {

        //Erases the previous screen.
        super.paintComponent(g);

        g.setColor(Color.blue);
        g.drawString("Skills Left: " + remainingInvUses, 50, 250);



        //Draws strips.
        for (int i = 0; i < numOfStrips; i++) {
            for (int x = 0; x < 8; x++) {
                allStrips[i][x].paint(g, this);
            }
        }

        //Draw hero sprite.
        hero.paint(g, this);

        //Draw fireball sprites.
        for (Sprite s : fireballs)
            s.paint(g, this);

        //Draw pillar sprites.
        for (Sprite s : Pillar)
            s.paint(g, this);

        //Set the font size and color.
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 3f);
        g.setFont(newFont);
        g.setColor(Color.green);

        g.drawString("Skills Left: " + remainingInvUses, 50, 100);

        //Draws the high score on the screen.
        g.drawString("High Score: " + scoreManager.readScore(), 50, 50);

        //Set the font size and color.
        Font cF = g.getFont();
        Font nF = cF.deriveFont(cF.getSize() * 3f);
        g.setFont(nF);
        g.setColor(Color.yellow);

        //Draws the score on the screen.
        g.drawString("" + score, 50, 250);


        //Set the font size and color.
        Font CF = g.getFont();
        Font NF = CF.deriveFont(CF.getSize() * 1f);
        g.setFont(NF);
        g.setColor(Color.red);

        //Draws invincibility status.
        if (invincibility)

            g.drawString("" + invTimeLeft, 350, 350);


        //Draws logo on screen.
        if (showLogo) {
            // Calculate the X and Y coordinates to center the logo.
            int logoX = (getWidth() - logo.getWidth()) / 2;
            int logoY = (getHeight() - logo.getHeight()) / 2;

            logo.setXLoc(logoX);
            logo.setYLoc(logoY);

            logo.paint(g, this);
        }

        //Stop stuttering (linux issue).
        Toolkit.getDefaultToolkit().sync();
    }


    /**
     * Reads keyboard input for moving
     * when key is pressed down.
     */
    private class KeyPressing extends KeyAdapter {

        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {

                //Moves hero within left and right bounds.
                case KeyEvent.VK_RIGHT:
                    if (!press && hero.getXLoc() < 695) {
                        right = 8;
                        press = true;
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (!press && hero.getXLoc() > 0) {
                        left = 8;
                        press = true;
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (!press) {
                        up = 10;
                        press = true;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (!press) {
                        down = 10;
                        press = true;
                    }
                    break;
                case KeyEvent.VK_CONTROL:
                    if (remainingInvUses > 0 && !invincibility && invDuration < 150) {
                        playAudio("Sounds/Skill1.wav");
                        invincibility = true;
                        remainingInvUses--;
                    }
                    break;
                case KeyEvent.VK_SHIFT:
                    if (gameLoop.isRunning())
                        gameLoop.stop();
                    else
                        gameLoop.start();
                    break;
                case KeyEvent.VK_ENTER:
                    if (!gameLoop.isRunning()) {
                        newGame = true;
                        newGame();
                    }
                    break;
            }
        }

        /**
         * Reads keyboard for input for stopping
         * when key is not pressed down.
         */
        public void keyReleased(KeyEvent e) {

            switch (e.getKeyCode()) {

                case KeyEvent.VK_RIGHT:
                    hero.setXDir(0);
                    break;
                case KeyEvent.VK_LEFT:
                    hero.setXDir(0);
                    break;
                case KeyEvent.VK_UP:
                    hero.setYDir(2);
                    break;
                case KeyEvent.VK_DOWN:
                    hero.setYDir(2);
                    break;
            }
        }
    }
//////////
}
