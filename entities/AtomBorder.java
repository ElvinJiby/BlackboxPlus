package entities;

public class AtomBorder {
    private int radius = 5; // 5 px radius

    public AtomBorder(){};
    public AtomBorder(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }
}
