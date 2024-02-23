package entities;

import javax.swing.*;
import java.awt.*;

public class Atom {
    private static final Image atomImage = (new ImageIcon("res/atomImage.png").getImage());
    private int xPosition = 0;
    private int yPosition = 0;

    public Atom(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    @Override
    public String toString() {
        return "Atom: Position ("+getXPosition()+","+getYPosition()+")";
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public static Image getAtomImage() {
        return atomImage;
    }
}
