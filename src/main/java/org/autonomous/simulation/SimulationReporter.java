package org.autonomous.simulation;

import org.autonomous.model.Car;
import org.autonomous.model.Position;

import java.util.List;

public class SimulationReporter {

    public void reportCollision(Car car, List<String> cars, Position position, int step) {
        String collisionCarNames = String.join(",",cars);
        System.out.printf(
                "- %s, collides with %s (%d,%d) at step %d%n",
                car.getName(), collisionCarNames, position.getX(), position.getY(), step+1
        );
    }

    public void reportFinalState(List<Car> cars) {
        cars.stream().filter(c -> !c.isCollided()).forEach(c -> System.out.printf(
                                "- %s, (%d,%d) %s%n",
                                c.getName(),
                                c.getPosition().getX(),
                                c.getPosition().getY(),
                                c.getDirection()));
    }
}
