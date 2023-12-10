package CrossroadsOfTeyvat;

import java.util.Random;

class ManageObstacles {

    //Create random generator.
    private Random rand = new Random();


    //Method that creates and resets Fireballs on the road strip.

    Sprite setFireball(int stripYLoc) {

        //Makes sprite.
        Sprite Fireball = new Sprite();

        //Scrolls sprite.
        Fireball.setYDir(2);

        //Set sprite to strip location.
        Fireball.setYLoc(stripYLoc);

        if (rand.nextInt(2) == 1) {
            //Right to left.
            Fireball.setXLoc(900);
            Fireball.setXDir(-(rand.nextInt(10) + 10));
            Fireball.setImage(randomFireball("left"));

        } else {
            //Left to right.
            Fireball.setXLoc(-200);
            Fireball.setXDir((rand.nextInt(10) + 10));
            Fireball.setImage(randomFireball("right"));
        }

        return Fireball;
    }

    /*
    Method to return random Fireball color.
     */
    String randomFireball(String dir) {

        //Fireball color variables.
        int FireballColor = rand.nextInt(8);
        String FireballImage = "";

        if (dir.equals("left")) {

            switch (FireballColor) {
                case 0:
                    FireballImage = "\\Fireball_Left\\Fireball_Left_Blue.png";
                    break;
                case 1:
                    FireballImage = "\\Fireball_Left\\Fireball_Left_Blue.png";
                    break;
                case 2:
                    FireballImage = "\\Fireball_Left\\Fireball_Left_Red.png";
                    break;
                case 3:
                    FireballImage = "\\Fireball_Left\\Fireball_Left_Purple.png";
                    break;
                case 4:
                    FireballImage = "\\Fireball_Left\\Fireball_Left_Purple.png";
                    break;
                case 5:
                    FireballImage = "\\Fireball_Left\\Fireball_Left_Red.png";
                    break;
                case 6:
                    FireballImage = "\\Fireball_Left\\Fireball_Left_Red.png";
                    break;
                case 7:
                    FireballImage = "\\Fireball_Left\\Fireball_Left_Purple.png";
                    break;
            }
        }

        if (dir.equals("right")) {

            switch (FireballColor) {
                case 0:
                    FireballImage = "\\Fireball_Right\\Fireball_Right_Blue.png";
                    break;
                case 1:
                    FireballImage = "\\Fireball_Right\\Fireball_Right_Blue.png";
                    break;
                case 2:
                    FireballImage = "\\Fireball_Right\\Fireball_Right_Purple.png";
                    break;
                case 3:
                    FireballImage = "\\Fireball_Right\\Fireball_Right_Red.png";
                    break;
                case 4:
                    FireballImage = "\\Fireball_Right\\Fireball_Right_Purple.png";
                    break;
                case 5:
                    FireballImage = "\\Fireball_Right\\Fireball_Right_Red.png";
                    break;
                case 6:
                    FireballImage = "\\Fireball_Right\\Fireball_Right_Blue.png";
                    break;
                case 7:
                    FireballImage = "\\Fireball_Right\\Fireball_Right_Purple.png";
                    break;
            }
        }

        return FireballImage;
    }

    /*
    Method that creates and resets Pillar on the track strip.
     */
    Sprite setPillar(int stripYLoc) {

        //Makes sprite.
        Sprite train = new Sprite(randomPillar());

        //Scrolls sprite.
        train.setYDir(2);

        //Set sprite to strip location.
        train.setYLoc(stripYLoc);

        if (rand.nextInt(2) == 1) {
            //Right to left.
            train.setXLoc(900);
            train.setXDir(-(rand.nextInt(10) + 30));
        } else {
            //Left to right.
            train.setXLoc(-1500);
            train.setXDir((rand.nextInt(10) + 30));
        }

        return train;
    }

    /*
    Method to return a random colored train.
     */

    String randomPillar() {

        int trainNum = rand.nextInt(10);
        String trainImage = "";

        switch (trainNum) {

            case 0:

                trainImage = "\\Pillar\\Pillar.png";
                break;

            case 1:

                trainImage = "\\Pillar\\Pillar.png";
                break;

            case 2:

                trainImage = "\\Pillar\\Pillar.png";
                break;

            case 3:

                trainImage = "\\Pillar\\Pillar.png";
                break;

            case 4:

                trainImage = "\\Pillar\\Pillar.png";
                break;

            case 5:

                trainImage = "\\Pillar\\Pillar.png";
                break;

            case 6:

                trainImage = "\\Pillar\\Pillar.png";
                break;

            case 7:

                trainImage = "\\Pillar\\Pillar.png";
                break;
        }

        return trainImage;
    }
}
