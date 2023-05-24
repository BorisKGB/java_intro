package ru.java.study.lesson6.hw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class SearchData {

    private static Set<Record> records = new HashSet<>();
    private static List<Filter> filters = new ArrayList<>();
    public static void loadCSV(String filePath, String csvDelimiter) {
        SearchApp.logInfo(String.format("Loading csv file '%s'", filePath), false);
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();
            int lineNum = 1;
            while (line != null) {
                if (lineNum != 1) {
                    String[] elements = line.split(Pattern.quote(csvDelimiter));
                    try {
                        records.add(new Record(elements));
                    } catch (IllegalArgumentException e) {
                        SearchApp.logWarning(String.format("Ошибка загрузки данных из строки %d", lineNum), true);
                    }
                } else {
                    Record.initFields(line.split(Pattern.quote(csvDelimiter)));
                }
                line = br.readLine();
                lineNum++;
            }
            br.close();
        } catch (IOException e) {
            SearchApp.logError(e.toString(), true);
            SearchApp.executionError = true;
        }
    }

    public static void initFilters(){
        for (String name : Record.fieldNames) {
            filters.add(new Filter(name));
        }
    }

    public static Integer getRecordsSize() {
        return records.size();
    }

    public static Set<Record> getRecords() {
        boolean applyFilter = false;
        List<Filter> filtersToApply = new ArrayList<>();
        for (Filter filter : filters) {
            if (!filter.isEmpty) {
                applyFilter = true;
                filtersToApply.add(filter);
            }
        }
        if (applyFilter){
            Set<Record> filteredRecords = new HashSet<>();
            for (Record record : records) {
                boolean allowRecord = true;
                for (Filter filter : filtersToApply) {
                    if (filter.type.equals(Filter.ValuesType)) {
                        if (!filter.strValues.contains(record.fields.get(filter.name).strValue))
                            allowRecord = false;
                    } else {
                        if (filter.moreThen == null || record.fields.get(filter.name).numValue < filter.moreThen) {
                            allowRecord = false;
                        }
                        if (filter.lessThen == null || record.fields.get(filter.name).numValue > filter.lessThen) {
                            allowRecord = false;
                        }
                    }
                }
                if (allowRecord) filteredRecords.add(record);
            }
            return filteredRecords;
        }else return records;
    }

    public static void printRecords(Set<Record> records) {
        int counter = 1;
        for (Record record : records) {
            System.out.printf("%d: %s\n", counter, record);
            counter++;
        }
    }

    public static List<Filter> getFilters() {
        return filters;
    }

    public static boolean filterExistByNumber(int filterNumber) {
        return (filterNumber >= 0 && filterNumber < filters.size());
    }

    public static Filter getFilterByNum(int filterNumber) {
        return filters.get(filterNumber);
    }

    public static void updateFilterByNum(int filterNumber, Filter filter) {
        filters.set(filterNumber, filter);
    }
}
