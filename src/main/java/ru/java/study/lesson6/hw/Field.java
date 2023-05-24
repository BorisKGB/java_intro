package ru.java.study.lesson6.hw;

public class Field {
//    String name;
    String strValue;
    Double numValue;
    public Field(String strValue) {
        this.strValue = strValue;
        this.numValue = null;
    }
    public Field(String strValue, Double numValue) {
        this.strValue = strValue;
        this.numValue = numValue;
    }
}
