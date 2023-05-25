package ru.java.study.lesson6.hw.model;

import java.util.HashSet;
import java.util.Set;

public class Filter {
    private String name;
    private boolean isEmpty;
    private Set<String> values = new HashSet<>();
    private Double moreThen = null;
    private Double lessThen = null;
    private Integer type;
    public static final Integer ValuesType = 0;
    public static final Integer IntervalsType = 1;

    public Filter(String name) {
        this.name = name;
        this.isEmpty = true;
        this.type = ValuesType;
    }
    public Filter(String name, Double moreThen, Double lessThen) {
        this.name = name;
        this.type = IntervalsType;
        if (moreThen != null || lessThen != null) {
            this.moreThen = moreThen;
            this.lessThen = lessThen;
            this.isEmpty = false;
        } else this.isEmpty = true;
    }

    public Filter(String name, Set<String> Values) {
        this.name = name;
        this.type = ValuesType;
        if (!Values.isEmpty()) {
            this.values = Values;
            this.isEmpty = false;
        } else this.isEmpty = true;
    }

    @Override
    public String toString() {
        if (isEmpty) return String.format("%s: Не задан", name);
        if (type.equals(ValuesType)) {
            return String.format("%s: %s", name, values.toString());
        } else {
            return String.format("%s: Значения %s%s", name,
                    (moreThen == null) ? "" : "от "+moreThen,
                    (lessThen == null) ? "" : " до "+lessThen
            );
        }
    }

    public boolean inInterval(Double value) {
        if (moreThen == null || value < moreThen) {
            return false;
        }
        if (lessThen == null || value > lessThen) {
            return false;
        }
        return true;
    }

    public boolean isType(Integer type) {
        return this.type.equals(type);
    }

    public boolean isEmpty() {
        return isEmpty;
    }
    public boolean contains(String value) {
        return values.contains(value);
    }

    public String getName() {
        return name;
    }
}
