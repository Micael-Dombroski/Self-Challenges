package domain;

import java.util.Objects;

public class Coordinates {
    private double x;
    private double y;
    private Quadrant quadrant;
    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
        setQuadrant();
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setQuadrant() {
        if(x > 0 && y > 0)
            quadrant = Quadrant.FIRST_QUADRANT;
        if(x < 0 && y > 0)
            quadrant = Quadrant.SECOND_QUADRANT;
        if(x < 0 && y < 0)
            quadrant = Quadrant.THIRD_QUADRANT;
        if(x > 0 && y < 0)
            quadrant = Quadrant.FOURTH_QUADRANT;
    }

    public int getQuadrant() {
        return quadrant.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Double.compare(x, that.x) == 0 && Double.compare(y, that.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    public static Coordinates sumCoordinates(Coordinates c1, Coordinates c2) {
        return new Coordinates(c1.getX() + c2.getX(), c1.getY() + c2.getY());
    }
    public static Coordinates subCoordinates (Coordinates c1, Coordinates c2) {
        return new Coordinates(c1.getX() - c2.getX(), c1.getY() - c2.getY());
    }

    @Override
    public String toString() {
        return "Coordinates =(" +
                "x=" + x +
                ", y=" + y +
                ')';
    }
}
