package org.example.model;

import java.util.Objects;

public class Car {
    private String name;
    private Position startPosition;
    private Position endPosition;
    private Direction initialDirection;
    private Direction finalDirection;
    private boolean isStopped = false;

    public Car(String name,Position position,Direction direction){
        this.name = name;
        this.startPosition = position;
        this.endPosition = position;
        this.initialDirection = direction;
        this.finalDirection = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Position endPosition) {
        this.endPosition = endPosition;
    }

    public Direction getInitialDirection() {
        return initialDirection;
    }

    public void setInitialDirection(Direction direction) {
        this.initialDirection = direction;
    }

    public Direction getFinalDirection() {
        return finalDirection;
    }

    public void setFinalDirection(Direction direction) {
        this.finalDirection = direction;
    }

    public void turnLeft(){
        if(!this.isStopped){
            this.finalDirection = this.finalDirection.turnLeft();
        }

    }

    public void turnRight(){
        if(!this.isStopped){
            this.finalDirection = this.finalDirection.turnRight();
        }

    }

    public Position getNextForwardPosition(){
        return this.endPosition.move(this.finalDirection);
    }

    public void stop(){
        this.isStopped = true;
    }

    public boolean isCarActive(){
        return !this.isStopped;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
