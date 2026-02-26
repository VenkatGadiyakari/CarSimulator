package org.autonomous.model;

import java.util.Objects;

public class Car {
    private final String name;
    private final CarConfig config;
    private CarState state;

    public Car(String name, Position start, Direction direction) {
        this.name = name;
        this.config = new CarConfig(start, direction);
        reset();
    }

    public String getName() { return name; }

    public Position getPosition() { return state.position(); }

    public Direction getDirection() { return state.direction(); }


    public void reset() {
        this.state = new CarState(config.startPosition(), config.startDirection());
    }

    public void turnLeft() {
        if (state.isStopped() || state.isCollided()){
            return;
        }
        state = state.turnLeft();
    }

    public void turnRight() {
        if (state.isStopped() || state.isCollided()){
            return;
        }
        state = state.turnRight();
    }

    public Position getNextForwardPosition() {
        if (state.isStopped() || state.isCollided()){
            return state.position();
        }
        return state.position().move(state.direction());

    }

    public void moveTo(Position p) {
        state = state.moveTo(p);
    }

    public void stop() {
        state = state.stop();
    }

    public void collide(){
        state = state.collide();
    }

    public boolean isStopped(){
        return state.isStopped();
    }

    public boolean isCollided(){
        return state.isCollided();
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
