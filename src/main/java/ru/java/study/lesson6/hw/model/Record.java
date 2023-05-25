package ru.java.study.lesson6.hw.model;

import ru.java.study.lesson6.hw.SearchApp;
import ru.java.study.lesson6.hw.model.Field;

import java.util.*;

public class Record {
    public static final Integer IntegerField = 0;
    public static final Integer DoubleField = 1;
    public static final Integer StringField = 2;
    private Map<String, Field> fields = new HashMap<>();
    private static List<String> fieldNames = new ArrayList<>();
    private static Map<String, Integer> fieldTypes = new HashMap<>();
    private static Map<String, Set<String>> uniqueValues = new HashMap<>();

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
            if (!fieldType(fieldName).equals(StringField)) {
                Double numValue = null;
                if (value != null) {
                    try {
                        numValue = Double.parseDouble(value);
                        if (fieldType(fieldName).equals(IntegerField) && numValue % 1 != 0)
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
        List<String> result = new ArrayList<>();
        for (String fieldName : fieldNames) {
            Field currentField = getField(fieldName);
            String fancyFieldValue;
            if (currentField.isEmpty()) {
                fancyFieldValue = "Not set";
            }
            else if (fieldType(fieldName).equals(IntegerField)) {
                fancyFieldValue = String.format("%.0f", currentField.getNumValue());
            } else {
                fancyFieldValue = currentField.getStrValue();
            }
            result.add(String.format("%s=%s", fieldName, fancyFieldValue));
        }
        return String.join(", ", result);
    }

    /**
     * Init variables with field names
     * @param fieldList list of field names
     */
    public static void initFields(String[] fieldList) {
        for (String fieldName : fieldList) {
            fieldNames.add(fieldName);
            fieldTypes.put(fieldName, IntegerField);
            uniqueValues.put(fieldName, new HashSet<String>());
        }
    }

    public Field getField(String name) {
        return fields.get(name);
    }

    public static List<String> getFieldNames() {
        return fieldNames;
    }
    public static Integer fieldType(String fieldName) {
        return fieldTypes.get(fieldName);
    }

    /**
     * Return unique values of fieldName as List
     * @param fieldName field name
     * @return List of unique values
     */
    public static List<String> getUniqueValuesAsList(String fieldName) {
        return new ArrayList<>(uniqueValues.get(fieldName));
    }

    /**
     * Assume that field contains Double values and search for min value
     * @param fieldName field where to search value
     * @return min value in fieldName in String type
     */
    public static String getMinValue(String fieldName) {
        // TODO: probably try other convert variants:
        //   - .stream().min(o1, o2) -> Double.compare(Double.parseDouble(o1), Double.parseDouble(o2))
        //   - .stream().mapToDouble(s -> Double.parseDouble(s)).min()
        return uniqueValues.get(fieldName).stream().min(Comparator.comparingDouble(Double::parseDouble)).get();
    }
    /**
     * Assume that field contains Double values and search for max value
     * @param fieldName field where to search value
     * @return min value in fieldName in String type
     */
    public static String getMaxValue(String fieldName) {
        // TODO: probably try other convert variants:
        //   - .stream().min(o1, o2) -> Double.compare(Double.parseDouble(o1), Double.parseDouble(o2))
        //   - .stream().mapToDouble(s -> Double.parseDouble(s)).min()
        return uniqueValues.get(fieldName).stream().max(Comparator.comparingDouble(Double::parseDouble)).get();
    }
}
