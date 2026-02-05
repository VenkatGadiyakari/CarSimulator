package org.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid {
    int height;
    int width;
    private final Map<Position, List<Car>> occupancy = new HashMap<>();

    public Grid(int height,int width){
        this.height = height;
        this.width = width;
    }

    public void addCar(Car car){
        occupancy.computeIfAbsent(car.getStartPosition(), k -> new ArrayList<>()).add(car);
    }

    public boolean isWithinBounds(Position p){
        int x = p.getX();
        int y = p.getY();
        return x>=0 && y>=0 && x<height && y<height;
    }

    public boolean isOccupied(Position pos) {
        return occupancy.containsKey(pos);
    }

    public List<Car> getCarAt(Position pos) {
        return occupancy.getOrDefault(pos, new ArrayList<>());
    }

    public void removeCar(Car car) {
        List<Car> list = occupancy.get(car.getStartPosition());
        if (list != null) {
            list.remove(car);
            if (list.isEmpty()) {
                occupancy.remove(car.getStartPosition());
            }
        }
    }


}
