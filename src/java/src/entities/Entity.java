package entities;

public class Entity {
    private final int x;
    private final int y;

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
