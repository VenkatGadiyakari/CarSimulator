package org.example.model;

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

//    public boolean isActive() { return state.st; }

    public void reset() {
        this.state = new CarState(config.startPosition(), config.startDirection());
    }

    public void turnLeft() {
        if (state.stop() || state.collision()){
            return;
        }
        state = state.turnLeft();
    }

    public void turnRight() {
        if (state.stop() || state.collision()){
            return;
        }
        state = state.turnRight();
    }

    public Position getNextForwardPosition() {
        if (state.stop() || state.collision()){
            return state.position();
        }
        return state.position().move(state.direction());

    }

    public void moveTo(Position p) {
        state = state.moveTo(p);
    }

    public void stop() {
        state = state.stopped();
    }

    public void collide(){
        state = state.collided();
    }

    public boolean isStopped(){
        return state.stop();
    }

    public boolean isCollided(){
        return state.collision();
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
