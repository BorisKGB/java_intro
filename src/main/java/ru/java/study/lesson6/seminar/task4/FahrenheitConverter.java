package ru.java.study.lesson6.seminar.task4;

public class FahrenheitConverter implements Converter {
    @Override
    public double convertValue(double grad) {
        return grad*9/5 + 32;
    }
}
