package entities;

public class Entity {
    private int x;
    private int y;

    public Entity() {
        x = 0;
        y = 0;
    }

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
