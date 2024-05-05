package tests;

import entities.ExitPoint;
import main.Game;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ArrayListTest {
    @Test
    public void LoadExitPointsTest() {
        Game game = new Game();

        ArrayList<ExitPoint> exitPointsList = new ArrayList<>(); // Arraylist that contains the coordinates of each exit point
        exitPointsList = Game.loadExitPointCoords();

        assertNotNull(exitPointsList, "Exit Points array list is null");
        assertFalse(exitPointsList.isEmpty());
    }
}
