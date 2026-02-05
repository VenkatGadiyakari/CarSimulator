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
        int index = 0;
        boolean canMoveForward = true;

        for(Map.Entry<Car,String> entry: mp.entrySet()){
            Car c = entry.getKey();
            c.setEndPosition(c.getStartPosition());
            c.setFinalDirection(c.getInitialDirection());
        }

        while(canMoveForward){
            Map<Position, List<Car>> occupied = new HashMap<>();

            canMoveForward = false;
            for(Map.Entry<Car,String> entry: mp.entrySet()){
                Car c = entry.getKey();
                String command = entry.getValue();
                if(!c.isCarActive() || index>=command.length()){
                    occupied.computeIfAbsent(c.getEndPosition(), k -> new ArrayList<>()).add(c);
                    continue;
                }

                canMoveForward = true;
                switch (command.charAt(index)){
                    case 'L' -> c.turnLeft();
                    case 'R' -> c.turnRight();
                    case 'F' -> moveForward(c);
                }
                occupied.computeIfAbsent(c.getEndPosition(), k -> new ArrayList<>()).add(c);
            }


            //collision detection
            for (Map.Entry<Position, List<Car>> entry : occupied.entrySet()) {
                if (entry.getValue().size() > 1) {
                    for (Car c : entry.getValue()) {
                        List<String> cars = entry.getValue().stream().map(Car::getName).filter(name ->!name.equals(c.getName())).toList();
                        String collisionCars = String.join(",",cars);
                        c.stop();
                        System.out.printf(
                                "- %s, collides with %s  (%d,%d) at step %d%n",
                                c.getName(), collisionCars, entry.getKey().getX(), entry.getKey().getY(), index + 1
                        );
                    }
                    return;
                }
            }
            index+=1;
        }

        for(Map.Entry<Car,String> entry: mp.entrySet()){
            Car c = entry.getKey();
            if(c.isCarActive()){
                System.out.printf("%s, (%d, %d) %s", c.getName(),c.getEndPosition().getX(),c.getEndPosition().getY(),c.getFinalDirection().name());
            }
            System.out.println();
        }
    }

    public void moveForward(Car car){
        Position nextPosition = car.getNextForwardPosition();
        if(!grid.isWithinBounds(nextPosition)){
            car.stop();
            return;
        }
        else{
            car.setEndPosition(nextPosition);
        }
    }


}
