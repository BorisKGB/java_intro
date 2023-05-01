package ru.java.study.lesson5.seminar.lombok;

public class Main {
    public static void main(String[] args) {
        Vector vector = new Vector(1, 2 ,3);
        System.out.println("vector.getX() = " + vector.getX());
        vector.setX(2);
        vector.hashCode();
        System.out.println("vector = " + vector);
    }
}
