package org.autonomous.model;

public record CarState(Position position, Direction direction, boolean stop, boolean collision) {

    public CarState(Position position, Direction direction){
        this(position, direction, false,false);
    }

    public CarState turnLeft(){
        return new CarState(position,direction.turnLeft(),stop, collision);
    }

    public CarState turnRight(){
        return new CarState(position, direction.turnRight(), stop,collision);
    }

    public CarState stopped(){
        return new CarState(position, direction, true, collision);
    }

    public CarState collided(){
        return new CarState(position, direction, stop, true);
    }


    public CarState moveTo(Position newPos) {
        return new CarState(newPos, direction, stop, collision);
    }
}
