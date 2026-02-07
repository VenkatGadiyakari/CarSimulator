package org.autonomous.simulation;

import org.autonomous.collision.CollisionPolicy;
import org.autonomous.model.Car;
import org.autonomous.model.Direction;
import org.autonomous.model.Grid;
import org.autonomous.model.Position;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SimulatorTest {

    @Test
    void simulateOneCar(){
        Grid grid = new Grid(10,10);
        Position car1Position = new Position(1,2);
        Direction car1Direction = Direction.N;
        Car c1 = new Car("A",car1Position,car1Direction);
        SimulationReporter simulationReporter = new SimulationReporter();
        CollisionPolicy collisionPolicy = new CollisionPolicy(simulationReporter);
        Simulator simulator = new Simulator(grid,simulationReporter,collisionPolicy);
        String car1Command = "FFRFFFFRRL";
        Map<Car,String> carCommandMap = new HashMap<>();
        carCommandMap.put(c1,car1Command);
        simulator.simulate(carCommandMap);

        PrintStream originalOut = System.out;

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        simulator.simulate(carCommandMap);
//        System.out.println(c1.getPosition());

        System.setOut(originalOut);

        String output = out.toString().trim();
        System.out.println("Captured output:");
        System.out.println(output);
        assertEquals("- A, (3,6) S", output);
    }

    @Test
    void simulateTwoCarsThatCollide(){
        Grid grid = new Grid(10,10);

        Position car1Position = new Position(1,2);
        Direction car1Direction = Direction.N;
        Car c1 = new Car("A",car1Position,car1Direction);

        Position car2Position = new Position(7,8);
        Direction car2Direction = Direction.W;
        Car c2 = new Car("B",car2Position,car2Direction);

        String car1Command = "FFRFFFFRRL";
        String car2Command = "FFLFFFFFFF";

        SimulationReporter simulationReporter = new SimulationReporter();
        CollisionPolicy collisionPolicy = new CollisionPolicy(simulationReporter);
        Simulator simulator = new Simulator(grid,simulationReporter,collisionPolicy);

        Map<Car,String> carCommandMap = new HashMap<>();
        carCommandMap.put(c1,car1Command);
        carCommandMap.put(c2,car2Command);

        simulator.simulate(carCommandMap);

        PrintStream originalOut = System.out;

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        simulator.simulate(carCommandMap);
//        System.out.println(c1.getPosition());

        System.setOut(originalOut);

        String output = out.toString().trim();
//        System.out.println("Captured output:");
//        System.out.println(output);
        assertEquals("- A, collides with B (3,6) at step 7\n" +
                "- B, collides with A (3,6) at step 7", output);
    }

}