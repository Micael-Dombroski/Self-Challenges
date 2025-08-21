package domain;

public class Vector {
    private Coordinates head;
    private Coordinates tail;
    private double magnitude;
    private double direction;
    public Vector(Coordinates tail,Coordinates head) {
        this.head = head;
        this.tail = tail;
        setMagnitude();
        setDirection();
    }
    public Vector(Coordinates head) {
        this.head = head;
        this.tail = new Coordinates(0,0);
        setMagnitude();
        setDirection();
    }

    public Coordinates getHead() {
        return head;
    }

    public void setHead(Coordinates head) {
        this.head = head;
    }

    public Coordinates getTail() {
        return tail;
    }

    public void setTail(Coordinates tail) {
        this.tail = tail;
    }
    public static double calculateMagnitude(Coordinates tail, Coordinates head) {
        return Math.sqrt(Math.pow(head.getX() - tail.getX(), 2) + Math.pow(head.getY() - tail.getY(), 2));
    }
    private void setMagnitude() {
        magnitude = calculateMagnitude(tail, head);
    }
    public double getMagnitude() {
        return magnitude;
    }
    public static double calculateDirection(Coordinates tail, Coordinates head) {
        Coordinates coordinates = Coordinates.subCoordinates(head, tail);
        coordinates.setQuadrant();
        double radians = Math.atan2(coordinates.getY(),coordinates.getX());
        double degrees = Math.toDegrees(radians);
        return degrees < 0 ? degrees + 360 : degrees;
    }

    private void setDirection() {
        direction = calculateDirection(tail, head);
    }
    public double getDirection() {
        return direction;
    }
    //Operations with Vectors
    public static Vector sumVectors(Vector v1, Vector v2) {
        Coordinates c1 = Coordinates.subCoordinates(v1.getHead(), v1.getTail());
        Coordinates c2 = Coordinates.subCoordinates(v2.getHead(), v2.getTail());
        return new Vector(new Coordinates(0,0), Coordinates.sumCoordinates(c1, c2));
    }
    public static double scalarProduct(Vector v1, Vector v2) {
        Coordinates sum1 = Coordinates.sumCoordinates(v1.getTail(), v1.getHead());
        Coordinates sum2 = Coordinates.sumCoordinates(v2.getTail(), v2.getHead());
        return sum1.getX() * sum2.getX() + sum1.getY() * sum2.getY();
    }
    public static Vector multiplicationByDouble(Vector v1, double num) {
        Coordinates component = Coordinates.sumCoordinates(v1.getTail(), v1.getHead());
        Coordinates scaledComponent  = new Coordinates(num * component.getX(), num * component.getY());
        return new Vector(new Coordinates(0,0), scaledComponent);
    }
}
