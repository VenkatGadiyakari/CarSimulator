package org.autonomous.collision;

import org.autonomous.model.Car;
import org.autonomous.model.Position;
import org.autonomous.simulation.SimulationReporter;

import java.util.List;
import java.util.Map;

public class CollisionPolicy {

    private final SimulationReporter simulationReporter;

    public CollisionPolicy(SimulationReporter simulationReporter) {
        this.simulationReporter = simulationReporter;
    }

    public void detectCollision(Map<Position, List<Car>> gridState, int step) {
        for (Map.Entry<Position, List<Car>> entry : gridState.entrySet()) {
            if (entry.getValue().size() > 1) {
                for (Car c : entry.getValue()) {
                    List<String> cars = entry.getValue().stream().map(Car::getName).filter(name ->!name.equals(c.getName())).toList();
                    simulationReporter.reportCollision(c,cars,entry.getKey(),step);
                    c.collide();
                    c.stop();
                }
            }
        }
    }
}
