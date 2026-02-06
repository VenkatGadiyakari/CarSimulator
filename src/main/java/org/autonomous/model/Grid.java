package org.autonomous.model;
public class Grid {
    int height;
    int width;

    public Grid(int height,int width){
        this.height = height;
        this.width = width;
    }

    public boolean isWithinBounds(Position p){
        int x = p.getX();
        int y = p.getY();
        return x>=0 && y>=0 && x<height && y<width;
    }
}
