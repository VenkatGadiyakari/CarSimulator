package org.autonomous.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GridTest {

    @Test
    void positionInsideGridReturnsTrue() {
        Grid grid = new Grid(10, 10);

        assertTrue(grid.isWithinBounds(new Position(0, 0)));
        assertTrue(grid.isWithinBounds(new Position(9, 9)));
        assertTrue(grid.isWithinBounds(new Position(5, 3)));
    }

    @Test
    void negativeCoordinatesAreOutOfBounds() {
        Grid grid = new Grid(10, 10);

        assertFalse(grid.isWithinBounds(new Position(-1, 0)));
        assertFalse(grid.isWithinBounds(new Position(0, -1)));
    }

    @Test
    void coordinatesEqualToSizeAreOutOfBounds() {
        Grid grid = new Grid(10, 10);

        assertFalse(grid.isWithinBounds(new Position(10, 0)));
        assertFalse(grid.isWithinBounds(new Position(0, 10)));
    }

    @Test
    void oneAxisOutOfBoundsReturnsFalse() {
        Grid grid = new Grid(10, 10);

        assertFalse(grid.isWithinBounds(new Position(9, 10)));
        assertFalse(grid.isWithinBounds(new Position(10, 9)));
    }
}
