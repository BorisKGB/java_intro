package ru.java.study.lesson6.seminar.task4;

public class KelvinConverter implements Converter{
    @Override
    public double convertValue(double grad) {
        return grad - 273.150;
    }
}
