package ru.java.study.lesson3.seminar.task4;

import java.util.Objects;

public class MyObject {
    Integer number;

    public MyObject(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "myObject{" +
                "number=" + number +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyObject myObject = (MyObject) o;
        return number.equals(myObject.number);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(number);
//    }
}
