package ru.java.study.lesson6.hw;

import java.util.HashSet;
import java.util.Set;

public class Filter {
    public String name;
    public boolean isEmpty;
    public Set<String> strValues = new HashSet<>();
    public Double moreThen = null;
    public Double lessThen = null;
    public Integer type;
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

    public Filter(String name, Set<String> strValues) {
        this.name = name;
        this.type = ValuesType;
        if (!strValues.isEmpty()) {
            this.strValues = strValues;
            this.isEmpty = false;
        } else this.isEmpty = true;
    }

    @Override
    public String toString() {
        if (isEmpty) return String.format("%s: Не задан", name);
        if (type.equals(ValuesType)) {
            return String.format("%s: %s", name, strValues.toString());
        } else {
            return String.format("%s: Значения %s%s", name,
                    (moreThen == null) ? "" : "от "+moreThen,
                    (lessThen == null) ? "" : " до "+lessThen
            );
        }
    }
}
