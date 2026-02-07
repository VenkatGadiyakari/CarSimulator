package org.autonomous.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @Test
    void turnLeftCyclesCorrectly() {
        assertEquals(Direction.W, Direction.N.turnLeft());
        assertEquals(Direction.S, Direction.W.turnLeft());
        assertEquals(Direction.E, Direction.S.turnLeft());
        assertEquals(Direction.N, Direction.E.turnLeft());
    }

    @Test
    void turnRightCyclesCorrectly() {
        assertEquals(Direction.E, Direction.N.turnRight());
        assertEquals(Direction.S, Direction.E.turnRight());
        assertEquals(Direction.W, Direction.S.turnRight());
        assertEquals(Direction.N, Direction.W.turnRight());
    }

    @Test
    void dxDyValuesAreCorrect() {
        assertEquals(1, Direction.N.dx());
        assertEquals(0, Direction.N.dy());

        assertEquals(0, Direction.E.dx());
        assertEquals(1, Direction.E.dy());

        assertEquals(-1, Direction.S.dx());
        assertEquals(0, Direction.S.dy());

        assertEquals(0, Direction.W.dx());
        assertEquals(-1, Direction.W.dy());
    }

    @Test
    void fromCharAcceptsLowerAndUpperCase() {
        assertEquals(Direction.N, Direction.fromChar('N'));
        assertEquals(Direction.E, Direction.fromChar('e'));
        assertEquals(Direction.S, Direction.fromChar('s'));
        assertEquals(Direction.W, Direction.fromChar('W'));
    }

    @Test
    void fromCharThrowsExceptionForInvalidInput() {
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class,
                        () -> Direction.fromChar('X'));

        assertTrue(ex.getMessage().contains("Invalid direction"));
    }
}
