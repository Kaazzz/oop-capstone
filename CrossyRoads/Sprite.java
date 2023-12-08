package CrossyRoads;

import javax.swing.*;
import java.awt.*;

class Sprite {

    //Sprite location.
    private double xloc, yloc;

    //Sprite direction.
    private double xdir, ydir;

    private ImageIcon image;

    private boolean show = true;

    private String filename = "";

    Sprite() {
        image = null;
        xloc = 0;
        yloc = 0;
        xdir = 0;
        ydir = 0;
    }

    public Sprite(String filename, int xloc, int yloc) {
        setImage(filename);
        this.xloc = xloc;
        this.yloc = yloc;
    }

    public Sprite(int xloc, int yloc) {
        this.xloc = xloc;
        this.yloc = yloc;
    }

    Sprite(String filename) {
        setImage(filename);
    }

    void setImage(String filename) {
        this.filename = filename;

        try {
            this.image = new ImageIcon(getClass().getResource(filename));
        } catch (Exception e) {
            image = null;
        }
    }

    //Get xloc.
    int getXLoc() {
        return (int) xloc;
    }

    //Sets xloc.
    void setXLoc(int xloc) {
        this.xloc = xloc;
    }

    //Get yloc.
    int getYLoc() {
        return (int) yloc;
    }

    //Sets yloc.
    void setYLoc(int yloc) {
        this.yloc = yloc;
    }

    //Get xdir.
    public double getXDir() {
        return xdir;
    }

    //Sets xdir.
    void setXDir(double xdir) {
        this.xdir = xdir;
    }

    //Get ydir.
    public double getYDir() {
        return ydir;
    }

    //Sets ydir.
    void setYDir(double ydir) {
        this.ydir = ydir;
    }

    //Get image filename.
    String getFileName() {
        return filename;
    }

    void move() {
        xloc += xdir;
        yloc += ydir;
    }

    int getWidth() {
        if (image == null)
            return 20;
        else
            return image.getIconWidth();
    }

    int getHeight() {
        if (image == null)
            return 20;
        else
            return image.getIconHeight();
    }

     /* Method to draw sprite onto JPanel.*/

    void paint(Graphics g, JPanel panel) {
        if (show) {
            if (image == null)
                g.drawRect((int) xloc, (int) yloc, 50, 50);
            else
                image.paintIcon(panel, g, (int) xloc, (int) yloc);
        }
    }

}