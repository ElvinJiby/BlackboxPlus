package entities;

public class HexagonalBox {
    // (x,y) coordinates of the center of a hexagonal box on the board
    private final int x;
    private final int y;
    private boolean hasAtom = false;

    public HexagonalBox(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setHasAtom(Boolean hasAtom) { // used when generating atoms. call when a hexagonal box is chosen to host an atom
        this.hasAtom = hasAtom;
    }

    public boolean HasAtom() {
        return hasAtom;
    }
}
