package ru.java.study.lesson6.hw.model;

import ru.java.study.lesson6.hw.SearchApp;
import ru.java.study.lesson6.hw.model.Filter;
import ru.java.study.lesson6.hw.model.Record;

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

    /**
     * Read file at filePath, load header as field names, and rest data
     * Ignore parse errors if they appear in data lines, but log number of error line
     * Throws error when can not open file
     * @param filePath path to file to load data from
     * @param csvDelimiter field delimiter in file
     */
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
            SearchApp.setExecutionError();
        }
    }

    /**
     * create filter objects for each field in data
     */
    public static void initFilters(){
        for (String name : Record.getFieldNames()) {
            filters.add(new Filter(name));
        }
    }

    public static Integer getRecordsSize() {
        return records.size();
    }

    /**
     * Return records after applying Filters
     * @return Set of Record objects
     */
    public static Set<Record> getRecords() {
        boolean applyFilter = false;
        List<Filter> filtersToApply = new ArrayList<>();
        for (Filter filter : filters) {
            if (!filter.isEmpty()) {
                applyFilter = true;
                filtersToApply.add(filter);
            }
        }
        if (applyFilter){
            Set<Record> filteredRecords = new HashSet<>();
            for (Record record : records) {
                if (allowRecord(record, filtersToApply)) filteredRecords.add(record);
            }
            return filteredRecords;
        }else return records;
    }

    /**
     * Check if record matches filters
     * @param record record to check
     * @param filtersToApply List of filters that needs to be applied.
     * @return bool
     */

    private static boolean allowRecord(Record record, List<Filter> filtersToApply) {
        for (Filter filter : filtersToApply) {
            if (filter.isType(Filter.ValuesType)) {
                if (!filter.contains(record.getField(filter.getName()).getStrValue()))
                    return false;
            } else {
                if (!filter.inInterval(record.getField(filter.getName()).getNumValue()))
                    return false;
            }
        }
        return true;
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
