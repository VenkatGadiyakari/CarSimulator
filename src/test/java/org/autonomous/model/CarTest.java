package org.autonomous.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    @Test
    void carStartsAtInitialPositionAndDirection() {
        Car car = new Car("A", new Position(1, 2), Direction.N);

        assertEquals(new Position(1, 2), car.getPosition());
        assertEquals(Direction.N, car.getDirection());
    }

    @Test
    void resetRestoresInitialState() {
        Car car = new Car("A", new Position(1, 2), Direction.N);

        car.turnRight();
        car.moveTo(new Position(5, 5));
        car.reset();

        assertEquals(new Position(1, 2), car.getPosition());
        assertEquals(Direction.N, car.getDirection());
    }

    @Test
    void turnLeftChangesDirectionAntiClockwise() {
        Car car = new Car("A", new Position(0, 0), Direction.N);

        car.turnLeft();

        assertEquals(Direction.W, car.getDirection());
    }

    @Test
    void turnRightChangesDirectionClockwise() {
        Car car = new Car("A", new Position(0, 0), Direction.N);

        car.turnRight();

        assertEquals(Direction.E, car.getDirection());
    }

    @Test
    void stoppedCarCannotTurn() {
        Car car = new Car("A", new Position(0, 0), Direction.N);
        car.stop();

        car.turnLeft();

        assertEquals(Direction.N, car.getDirection());
    }

    @Test
    void nextForwardPositionMovesInCurrentDirection() {
        Car car = new Car("A", new Position(1, 1), Direction.N);

        Position next = car.getNextForwardPosition();

        assertEquals(new Position(2, 1), next);
    }

    @Test
    void collidedCarDoesNotMoveForward() {
        Car car = new Car("A", new Position(1, 1), Direction.N);
        car.collide();

        Position next = car.getNextForwardPosition();

        assertEquals(new Position(1, 1), next);
    }

    @Test
    void moveToUpdatesPosition() {
        Car car = new Car("A", new Position(0, 0), Direction.N);

        car.moveTo(new Position(2, 3));

        assertEquals(new Position(2, 3), car.getPosition());
    }

    @Test
    void stopMarksCarAsStopped() {
        Car car = new Car("A", new Position(0, 0), Direction.N);

        car.stop();

        assertTrue(car.isStopped());
    }

}