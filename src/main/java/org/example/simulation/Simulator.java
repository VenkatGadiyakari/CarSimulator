package org.example.simulation;


import org.example.model.Car;
import org.example.model.Grid;
import org.example.model.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulator {
    private final Grid grid;

    public Simulator(Grid grid){
        this.grid = grid;
    }

    public void addCar(Car car) {
        grid.addCar(car);
    }

//    public void simulate(Car car, String command){
//        for(char c: command.toCharArray()){
//            switch (c){
//                case 'L' -> car.turnLeft();
//                case 'R' -> car.turnRight();
//                case 'F' -> moveForward(car);
//            }
//        }
//    }
    public void simulate(Map<Car,String> mp){
        int step = 0;
        boolean running = true;

        //reset car state
        for(Map.Entry<Car,String> entry: mp.entrySet()){
            Car c = entry.getKey();
            c.setEndPosition(c.getStartPosition());
            c.setFinalDirection(c.getInitialDirection());
        }

        while(running){
            Map<Position, List<Car>> gridState = new HashMap<>();

            running = false;
            for(Map.Entry<Car,String> entry: mp.entrySet()){
                Car c = entry.getKey();
                String command = entry.getValue();
                if(!c.isCarActive() || step>=command.length()){

                    //add car position to grid current state , so other cars might collide later into this position
                    gridState.computeIfAbsent(c.getEndPosition(), k -> new ArrayList<>()).add(c);
                    continue;
                }

                running = true;
                switch (command.charAt(step)){
                    case 'L' -> c.turnLeft();
                    case 'R' -> c.turnRight();
                    case 'F' -> moveForward(c);
                }
                gridState.computeIfAbsent(c.getEndPosition(), k -> new ArrayList<>()).add(c);
            }


            //collision detection
            for (Map.Entry<Position, List<Car>> entry : gridState.entrySet()) {
                if (entry.getValue().size() > 1) {
                    for (Car c : entry.getValue()) {
                        List<String> cars = entry.getValue().stream().map(Car::getName).filter(name ->!name.equals(c.getName())).toList();
                        String collisionCars = String.join(",",cars);
                        c.stop();
                        System.out.printf(
                                "- %s, collides with %s  (%d,%d) at step %d%n",
                                c.getName(), collisionCars, entry.getKey().getX(), entry.getKey().getY(), step + 1
                        );
                    }
                }
            }
            step+=1;
        }

        //print final state of cars after collision
        for(Map.Entry<Car,String> entry: mp.entrySet()){
            Car c = entry.getKey();
            if(c.isCarActive()){
                System.out.printf("%s, (%d, %d) %s", c.getName(),c.getEndPosition().getX(),c.getEndPosition().getY(),c.getFinalDirection().name());
            }
            System.out.println();
        }
    }

    private void moveForward(Car car){
        Position nextPosition = car.getNextForwardPosition();
        if(!grid.isWithinBounds(nextPosition)){
            car.stop();
        }
        else{
            car.setEndPosition(nextPosition);
        }
    }


}
