package org.autonomous.model;

public enum Direction {
    N(1, 0),
    E(0, 1),
    S(-1, 0),
    W(0, -1);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Direction turnLeft() {
        switch (this) {
            case N: return W;
            case W:  return S;
            case S: return E;
            case E:  return N;
        }
        throw new IllegalStateException("Unknown direction");
    }

    public Direction turnRight() {
        switch (this) {
            case N: return E;
            case E:  return S;
            case S: return W;
            case W:  return N;
        }
        throw new IllegalStateException("Unknown direction");
    }

    public static Direction fromChar(char c) {
        return switch (Character.toUpperCase(c)) {
            case 'N' -> N;
            case 'E' -> E;
            case 'S' -> S;
            case 'W' -> W;
            default -> throw new IllegalArgumentException(
                    "Invalid direction: " + c);
        };
    }
}
