package ru.java.study.lesson6.seminar.task4;

public class Main {
    public static void main(String[] args) {
        double gradus = 36.6;
        FahrenheitConverter far = new FahrenheitConverter();
        System.out.println(far.convertValue(gradus));

        KelvinConverter kel = new KelvinConverter();
        System.out.println(kel.convertValue(gradus));
    }

}
