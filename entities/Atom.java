package entities;

public class Atom {
    private int xPosition = 0;
    private int yPosition = 0;

    public Atom(){};
    public Atom(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }
}
