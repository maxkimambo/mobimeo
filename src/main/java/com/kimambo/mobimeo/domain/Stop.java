package com.kimambo.mobimeo.domain;

import java.util.Objects;

public class Stop {


    private int id;

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void setY(Integer y) {
        Y = y;
    }

    private int X;
    private int Y;

    public Stop(int id, int x, int y) {
        this.id = id;
        this.X = x;
        this.Y = y;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stop stop = (Stop) o;
        return X == stop.X && Y == stop.Y;
    }

    public boolean equals(int x, int y) {
        return X == x && Y == y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(X, Y);
    }
}
