package entities;

public class HexagonalBox extends Entity {
    private boolean hasAtom = false;

    // (x,y) coordinates of the center of a hexagonal box on the board
    public HexagonalBox(int x, int y) {
        super(x,y);
    }

    public void setHasAtom(Boolean hasAtom) { // used when generating atoms. call when a hexagonal box is chosen to host an atom
        this.hasAtom = hasAtom;
    }

    public boolean HasAtom() {
        return hasAtom;
    }
}
