package test;

import domain.Coordinates;
import domain.Vector;

public class VectorTes01 {
    public static void main(String[] args) {
        //v=(4;3)
        Vector v = new Vector(new Coordinates(4,3));
        System.out.println(v.getHead());
        //4*v
        System.out.println(Vector.multiplicationByDouble(v, 4).getHead());
        //v=(1;4)
        v = new Vector(new Coordinates(1,4));
        //u=(2;3)
        Vector u = new Vector(new Coordinates(2,3));
        System.out.println(u.getHead());
        System.out.println(v.getHead());
        //u*v
        System.out.println(Vector.scalarProduct(u, v));
        //u-v
        System.out.println(Coordinates.subCoordinates(u.getHead(),v.getHead()));
        //3*u-2*v
        System.out.println(Coordinates.subCoordinates(Vector.multiplicationByDouble(u, 3).getHead(),Vector.multiplicationByDouble(v,2).getHead()));
        //(2*u-3*v)*(u+2*v)
        System.out.println(Vector.scalarProduct(new Vector(Coordinates.subCoordinates(Vector.multiplicationByDouble(u, 2).getHead(), Vector.multiplicationByDouble(v,3).getHead())),
                new Vector(Coordinates.sumCoordinates(u.getHead(), Vector.multiplicationByDouble(v, 2).getHead()))));
        //u=(0;3)
        u = new Vector(new Coordinates(0,3));
        //v=(4;-1)
        v = new Vector(new Coordinates(4,-1));
        //t=(0;-2)
        Vector t = new Vector(new Coordinates(0,-2));
        //w=(-3;-2)
        Vector w = new Vector(new Coordinates(-3,-2));
        //|u|+|v|
        System.out.printf("%.2f\n",Vector.calculateMagnitude(u.getTail(), u.getHead())+Vector.calculateMagnitude(v.getTail(), v.getHead()));
        //u+v+t+w
        System.out.println(Coordinates.sumCoordinates(u.getHead(),Coordinates.sumCoordinates(v.getHead(),Coordinates.sumCoordinates(t.getHead(),w.getHead()))));
        //u*v
        System.out.println(Vector.scalarProduct(u,v));
    }
}
