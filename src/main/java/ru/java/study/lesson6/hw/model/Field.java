package ru.java.study.lesson6.hw.model;

public class Field {
//    String name;
    private String strValue;
    private Double numValue;
    private boolean isEmpty = true;
    public Field(String strValue) {
        this.strValue = strValue;
        this.numValue = null;
        if (strValue != null && !strValue.isEmpty()) this.isEmpty = false;
    }
    public Field(String strValue, Double numValue) {
        this.strValue = strValue;
        this.numValue = numValue;
        if (strValue != null && !strValue.isEmpty()) this.isEmpty = false;
    }

    public String getStrValue() {
        return strValue;
    }

    public Double getNumValue() {
        return numValue;
    }

    public boolean isEmpty() {
        return isEmpty;
    }
}
