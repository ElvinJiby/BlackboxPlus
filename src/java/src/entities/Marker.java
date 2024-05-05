package entities;

import java.awt.*;

public class Marker extends Entity {
    private final Color markerColour;

    public Marker() {
        super();
        markerColour = Color.MAGENTA;
    }

    public Marker(int x, int y, Color markerColour) {
        super(x-5,y-5);
        this.markerColour = markerColour;
    }

    public Color getMarkerColour() {
        return markerColour;
    }
}
