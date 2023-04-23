package ru.java.study.lesson2.seminar.task4;

public class Task4 {
    public static void main(String[] args) {
        Vector vector = new Vector(1d, 2d, 3d);
        Vector secondVector = new Vector(1d, 2d, 3d);
        System.out.println(vector);
        System.out.println(vector.vecrorLength());
        System.out.println("vector.scalar(secondVector) = " + vector.scalar(secondVector));
        System.out.println("vector.vectorPr() = " + vector.vectorPr(new Vector(1, 2, 3)));
        System.out.println("vector.vectorCos() = " + vector.vectorCos(new Vector(1.2, 3.6, 2.1)));
    }
}
