package tests;

import entities.ExitPoint;
import entities.HexagonalBox;
import main.Game;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static main.Game.loadHexagonalBoxes;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ArrayListTest {
    @Test
    public void LoadExitPointsTest() {
        Game game = new Game();

        ArrayList<ExitPoint> exitPointsList; // contains the coordinates of each exit point
        exitPointsList = Game.loadExitPointCoords();

        assertNotNull(exitPointsList, "Exit Points array list is null");
        assertFalse(exitPointsList.isEmpty());
    }

    @Test
    public void LoadHexagonalBoxesTest() {
        Game game = new Game();

        ArrayList<HexagonalBox> hexagonalBoxes;
        hexagonalBoxes = loadHexagonalBoxes();

        assertNotNull(hexagonalBoxes, "Exit Points array list is null");
        assertFalse(hexagonalBoxes.isEmpty());
    }
}
