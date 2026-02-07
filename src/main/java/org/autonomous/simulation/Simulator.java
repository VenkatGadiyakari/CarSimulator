package org.autonomous.simulation;


import org.autonomous.collision.CollisionPolicy;
import org.autonomous.model.Car;
import org.autonomous.model.Grid;
import org.autonomous.model.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulator {
    private final Grid grid;
    private final SimulationReporter simulationReporter;
    private final CollisionPolicy collisionPolicy;

    public Simulator(Grid grid, SimulationReporter simulationReporter, CollisionPolicy collisionPolicy){
        this.grid = grid;
        this.simulationReporter = simulationReporter;
        this.collisionPolicy = collisionPolicy;
    }

    private void resetState(List<Car> cars){
        for(Car c: cars){
            c.reset();
        }
    }

    public void simulate(Map<Car,String> mp){
        int step = 0;
        boolean running = true;

        //reset car state
        List<Car> allCars = mp.keySet().stream().toList();
        resetState(allCars);

        while(running){
            Map<Position, List<Car>> gridState = new HashMap<>();

            running = false;
            for(Map.Entry<Car,String> entry: mp.entrySet()){
                Car c = entry.getKey();
                String command = entry.getValue();

                if(!c.isStopped() && !c.isCollided() && step<command.length()){
                    running = true;
                    switch (command.charAt(step)){
                        case 'L' -> c.turnLeft();
                        case 'R' -> c.turnRight();
                        case 'F' -> moveForward(c);
                    }
                    gridState.computeIfAbsent(c.getPosition(), k -> new ArrayList<>()).add(c);

                }
                else{
                    //car exceeded its command length and can be stopped;
                    c.stop();
                    if(!c.isCollided() && c.isStopped()){
                        //add active cars position that are stopped  to grid current state , so other cars might collide later into this position
                        gridState.computeIfAbsent(c.getPosition(), k -> new ArrayList<>()).add(c);
                    }
                }
            }


            //collision detection
            collisionPolicy.detectCollision(gridState,step);
            step+=1;
        }

        //print final state of cars after collision
        simulationReporter.reportFinalState(allCars);


    }

    private void moveForward(Car car){
        Position nextPosition = car.getNextForwardPosition();
        if(grid.isWithinBounds(nextPosition)){
            car.moveTo(nextPosition);
        }
    }

}
