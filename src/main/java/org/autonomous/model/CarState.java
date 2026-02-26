package org.autonomous.model;

public record CarState(Position position, Direction direction, boolean isStopped, boolean isCollided) {

    public CarState(Position position, Direction direction){
        this(position, direction, false,false);
    }

    public CarState turnLeft(){
        return new CarState(position,direction.turnLeft(),isStopped, isCollided);
    }

    public CarState turnRight(){
        return new CarState(position, direction.turnRight(), isStopped,isCollided);
    }

    public CarState stop(){
        return new CarState(position, direction, true, isCollided);
    }

    public CarState collide(){
        return new CarState(position, direction, isStopped, true);
    }


    public CarState moveTo(Position newPos) {
        return new CarState(newPos, direction, isStopped, isCollided);
    }
}
