package tests;

import entities.Atom;
import main.Game;
import main.GameWindow;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ResourceLoadingTest {
    @Test
    public void loadGameResourcesTest() {
        // Load the resources
        Image bgImage = new ImageIcon(Game.class.getResource("/Board Layouts/yellow-clear-all.png")).getImage();
        Image boardCover = new ImageIcon(Game.class.getResource("/Board Layouts/yellow-clear-background.png")).getImage();
        Image boardBoxNumber = new ImageIcon(Game.class.getResource("/Board Layouts/transparent-hexagon-numbered.PNG")).getImage();
        Image atomImage = new ImageIcon(Atom.class.getResource("/Miscellaneous/atomImage.png")).getImage();
        Image gameIcon = new ImageIcon(GameWindow.class.getResource("/Icons/new_icon.png")).getImage();

        // Check if resources are not null
        assertNotNull(bgImage, "Background image is not loaded");
        assertNotNull(boardCover, "Board cover image is not loaded");
        assertNotNull(boardBoxNumber, "Board box number image is not loaded");
        assertNotNull(atomImage, "Atom image is not loaded");
        assertNotNull(gameIcon, "Game's icon image is not loaded");
    }
}
