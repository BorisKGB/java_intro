package ru.java.study.lesson6.hw;

import java.util.*;

public class Record {
    public static final Integer IntegerField = 0;
    public static final Integer DoubleField = 1;
    public static final Integer StringField = 2;
    Map<String, Field> fields = new HashMap<>();
    static List<String> fieldNames = new ArrayList<>();
    static Map<String, Integer> fieldTypes = new HashMap<>();
    static Map<String, Set<String>> uniqueValues = new HashMap<>();

    public Record(String[] values) {
        if (values.length != fieldNames.size()) throw new IllegalArgumentException("Incorrect values count");
        int counter = 0;
        String fieldName;
        for (String value : values) {
            fieldName = fieldNames.get(counter);
            if (value.isEmpty()) {
                value = null;
                SearchApp.logWarning(
                        String.format("Column '%s' is empty, treated as 'not set'", fieldName), false);
            }
            if (!fieldTypes.get(fieldName).equals(StringField)) {
                Double numValue = null;
                if (value != null) {
                    try {
                        numValue = Double.parseDouble(value);
                        if (fieldTypes.get(fieldName).equals(IntegerField) && numValue % 1 != 0)
                            fieldTypes.replace(fieldName, DoubleField);
                    } catch (NumberFormatException e) {
                        fieldTypes.replace(fieldName, StringField);
                    }
                }
                fields.put(fieldName, new Field(value, numValue));
            } else fields.put(fieldName, new Field(value));
            uniqueValues.get(fieldName).add(value);
            counter++;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String fieldName : fieldNames) {
            String fieldValue;
            if (fieldTypes.get(fieldName).equals(IntegerField)) {
                fieldValue = String.format("%.0f", fields.get(fieldName).numValue);
            } else {
                fieldValue = fields.get(fieldName).strValue;
            }
            result.append(String.format("%s=%s, ", fieldName, fieldValue));
        }
        result.delete(result.length()-2, result.length());
        return result.toString();
    }

    /**
     * Init variables with field names
     * @param fieldList list on field names
     */
    public static void initFields(String[] fieldList) {
        for (String fieldName : fieldList) {
            fieldNames.add(fieldName);
            fieldTypes.put(fieldName, IntegerField);
            uniqueValues.put(fieldName, new HashSet<String>());
        }
    }
}
