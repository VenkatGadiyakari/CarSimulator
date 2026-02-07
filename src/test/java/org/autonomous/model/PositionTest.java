package org.autonomous.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {

    @Test
    void moveAppliesDirectionDelta() {
        Position p = new Position(1, 1);

        Position next = p.move(Direction.E);

        assertEquals(new Position(1, 2), next);
    }

    @Test
    void moveDoesNotMutateOriginalPosition() {
        Position p = new Position(1, 1);

        p.move(Direction.N);

        assertEquals(new Position(1, 1), p);
    }

}
