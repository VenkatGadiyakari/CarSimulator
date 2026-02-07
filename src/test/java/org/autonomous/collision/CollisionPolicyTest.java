package org.autonomous.collision;

import static org.junit.jupiter.api.Assertions.*;

import org.autonomous.model.Car;
import org.autonomous.model.Direction;
import org.autonomous.model.Position;
import org.autonomous.simulation.SimulationReporter;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CollisionPolicyTest {

    @Test
    void detectsCollisionAndStopsCars() {

        SimulationReporter reporter =
                new SimulationReporter();

        CollisionPolicy policy = new CollisionPolicy(reporter);

        Position p = new Position(1, 1);
        Car c1 = new Car("A", p, Direction.N);
        Car c2 = new Car("B", p, Direction.S);

        Map<Position, List<Car>> gridState = new HashMap<>();
        gridState.put(p, List.of(c1, c2));

        policy.detectCollision(gridState, 3);

        assertTrue(c1.isCollided());
        assertTrue(c2.isCollided());
        assertTrue(c1.isStopped());
        assertTrue(c2.isStopped());

    }
}
