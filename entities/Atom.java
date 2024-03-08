package entities;

import javax.swing.*;
import java.awt.*;

public class Atom {
    private static Image atomImage = (new ImageIcon(Atom.class.getClassLoader().getResource("res/atomImage.png")).getImage());
    // (x,y) coordinate of an atom on a board
    private int x = 0;
    private int y = 0;

    public Atom(int x, int y) {
        this.x = x-25;
        this.y = y-25;
    }

    @Override
    public String toString() {
        return "Atom: Position ("+getX()+","+getY()+")";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCenterX() {
        return x+25;
    }
    public int getCenterY() {
        return y+25;
    }

    public static Image getAtomImage() {
        return atomImage;
    }
}
