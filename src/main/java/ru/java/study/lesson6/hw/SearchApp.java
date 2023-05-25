package ru.java.study.lesson6.hw;

import ru.java.study.lesson6.hw.model.Filter;
import ru.java.study.lesson6.hw.model.Record;
import ru.java.study.lesson6.hw.model.SearchData;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SearchApp {
    private static Scanner iScanner;
    private static Logger logger;
    private static final String logFile =
            String.join(File.separator, new String[] {"data", "lesson6", "hw", "SearchApp.log"});
    private static String csvFilePath =
            String.join(File.separator, new String[] {"data", "lesson6", "hw", "laptops_some_data.csv"});
    private static String csvFileDataDelimiter = "|";
    private static boolean executionError = false;

    /**
     * Application start point
     * @return exit code >0 if any errors
     */
    public static Integer start(){
        iScanner = new Scanner(System.in);
        configureLogger();
        /*
         TODO: think about other way to return error code in main class.
           Now do: catch exception, print error log 'in place' and set executionError flag.
           Probably can define custom exception and catch in in main, as described in https://stackoverflow.com/questions/6171265/best-way-to-exit-a-program-when-i-want-an-exception-to-be-thrown/6171351#6171351
        */
        if (executionError) return 1;
        logDebug("Application start");
        validateCSVFileParams();
        SearchData.loadCSV(csvFilePath, csvFileDataDelimiter);
        if (executionError) return 1;
        logInfo(String.format("Из файла '%s' получено %d записей.", csvFilePath, SearchData.getRecordsSize()),
                true);
        SearchData.initFilters();
        mainMenu();
        logDebug("Application end");
        return 0;
    }

    /**
     * main menu interacting with user
     */
    private static void mainMenu() {
        boolean continueFlag = true;
        String userChoice;
        while (continueFlag) {
            logDebug("Show main menu");
            System.out.println("-".repeat(10));
            System.out.println("Выберите действие:");
            System.out.println("  1. Задать/посмотреть фильтр");
            System.out.println("  2. Вывести результаты фильтрации");
            System.out.println("  3. Выход");
            userChoice = iScanner.nextLine();
            logDebug(String.format("User action '%s'", userChoice));
            switch (userChoice) {
                case "1":
                    filtersMenu();
                    break;
                case "2":
                    printRecords(SearchData.getRecords());
                    break;
                case "3":
                    continueFlag = false;
                    break;
                default:
                    System.out.println("Неизвестный пункт.");
                    break;
            }
        }
    }

    /**
     * Menu for list and configure search filters
     */
    private static void filtersMenu() {
        logDebug("Show filters menu");
        boolean continueFlag = true;
        String userChoice;
        while (continueFlag) {
            System.out.println("-".repeat(10));
            System.out.println("Активные фильтры:");
            printFilters();
            System.out.println("Выберите действие:");
            System.out.println("  0. Вернуться в главное меню");
            System.out.println("  N. Изменить фильтр номер N");
            userChoice = iScanner.nextLine();
            logDebug(String.format("User action '%s'", userChoice));
            if (userChoice.equals("0")) {
                continueFlag = false;
            } else {
                try {
                    int filterNum = Integer.parseInt(userChoice)-1;
                    if (SearchData.filterExistByNumber(filterNum)) {
                        updateFilterMenu(filterNum);
                    } else {
                        System.out.println("Фильтра с таким номером нет");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ожидается целое число");
                }
            }
        }
        logDebug("Exit filters menu");
    }

    /**
     * Fancy update filter menu
     * @param filterNum number of filter in 'LaptopSearchData.filters' to change
     */
    public static void updateFilterMenu(int filterNum) {
        Filter filter = SearchData.getFilterByNum(filterNum);
        Filter newFilter;
        logDebug(String.format("Updating %s filter", filter.getName()));
        if (Record.fieldType(filter.getName()).equals(Record.StringField)) {
            newFilter = updateFilterValues(filter);
        } else {
            System.out.println("-".repeat(10));
            System.out.println("Выберите действие:");
            System.out.println("  1. Задать промежуток значений");
            System.out.println("  2. Выбрать значения из списка");
            String choice = iScanner.nextLine();
            switch (choice){
                case "1":
                    newFilter = updateFilterIntervals(filter);
                    break;
                case "2":
                    newFilter = updateFilterValues(filter);
                    break;
                default:
                    System.out.println("Неизвестный пункт");
                    newFilter = filter;
                    break;
            }
        }
        SearchData.updateFilterByNum(filterNum, newFilter);
        logDebug(String.format("Filter %s set to %s", newFilter.getName(), newFilter));
    }

    /**
     * Ask user to check possible values in filter
     * @param filter filter to update
     * @return new filter object
     */
    private static Filter updateFilterValues(Filter filter) {
        logDebug(String.format("Updating filter %s Values", filter.getName()));
        Set<String> allowedValues = new HashSet<>();
        List<String> uniqueValuesList = Record.getUniqueValuesAsList(filter.getName());
        int counter = 1;
        System.out.println("Допустимые значения:");
        for (String uniqueValue : uniqueValuesList) {
            System.out.printf("%d: %s\n", counter, uniqueValue);
            counter++;
        }
        System.out.println("Введите через пробел номера значений для выбора > ");
        String userInput = iScanner.nextLine();
        Scanner userScanner = new Scanner(userInput);
        while (userScanner.hasNextInt()) {
            allowedValues.add(uniqueValuesList.get(userScanner.nextInt()-1));
        }
        return new Filter(filter.getName(), allowedValues);
    }

    /**
     * Ask user to set intervals in filter
     * @param filter filter to update
     * @return new filter object
     */
    private static Filter updateFilterIntervals(Filter filter) {
        logDebug(String.format("Updating filter %s Intervals", filter.getName()));
        Double moreThen = null;
        Double lessThen = null;
        System.out.printf("Значения поля %s находятся в диапазоне от %s до %s\n", filter.getName(),
                Record.getMinValue(filter.getName()), Record.getMaxValue(filter.getName()));
        System.out.print("Введите минимальное значение фильтра или ничего > ");
        String userInput = iScanner.nextLine();
        if (!userInput.isEmpty()) {
            try {
                moreThen = Double.parseDouble(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Параметр не распознан, значение не задано");
            }
        }
        System.out.print("Введите максимальное значение фильтра или ничего > ");
        userInput = iScanner.nextLine();
        if (!userInput.isEmpty()) {
            try {
                lessThen = Double.parseDouble(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Параметр не распознан, значение не задано");
            }
        }
        return new Filter(filter.getName(), moreThen, lessThen);
    }

    /**
     * print state of all filters
     */
    public static void printFilters() {
        int counter = 1;
        for (Filter filter : SearchData.getFilters()) {
            printFilter(filter, String.valueOf(counter));
            counter++;
        }
    }


    /**
     * Fancy print filter state
     * @param filter filter which state to print
     */
    public static void printFilter(Filter filter, String prefix) {
        System.out.printf("%s. %s\n", prefix, filter);
    }

    /**
     * Fancy print Records
     * @param records Set of Record objects to print
     */
    public static void printRecords(Set<Record> records) {
        int counter = 1;
        for (Record record : records) {
            System.out.printf("%d: %s\n", counter, record);
            counter++;
        }
        logDebug(String.format("Printed %d records of %d", counter-1, SearchData.getRecordsSize()));
    }

    /**
     * Set logging to 'LaptopSearchApp.logFile' only.
     * If unable to open 'logFile' print error message and set executionError flag.
     */
    private static void configureLogger() {
        logger = Logger.getLogger(SearchApp.class.getName());
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.FINE);
        try {
            FileHandler fh = new FileHandler(logFile);
            logger.addHandler(fh);
            fh.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            System.out.printf("ERROR: Unable to open log file '%s'", logFile);
            setExecutionError();
        }
    }

    /**
     * Ask user what csv file and delimiter to use
     */
    public static void validateCSVFileParams() {
        System.out.printf("Использовать данные из файла '%s'? (Y/n) ", csvFilePath);
        String choice = iScanner.nextLine();
        if (!(choice.equals("") || choice.equalsIgnoreCase("y"))) {
            System.out.print("Введите путь до файла относительно корня проекта:\n> ");
            csvFilePath = iScanner.nextLine();
        }
        System.out.printf("Использовать '%s' как разделитель ячеек в файле '%s'? (Y/n) ",
                csvFileDataDelimiter, csvFilePath);
        // Todo: probably need to cover this with 'while (delimiterIncorrect) {... if (!delimiter.equals("")) ....
        choice = iScanner.nextLine();
        if (!(choice.equals("") || choice.equalsIgnoreCase("y"))) {
            System.out.print("Укажите свой разделитель: ");
            csvFileDataDelimiter = iScanner.nextLine();
        }
    }

    /**
     * send message to log facility with DEBUG level
     * @param message message
     */
    public static void logDebug(String message) {
        logger.fine(message);
    }

    /**
     * send message to log facility with INFO level
     * @param message message
     * @param showToUser show message to User in console?
     */
    public static void logInfo(String message, boolean showToUser) {
        logger.info(message);
        if (showToUser) System.out.printf("INFO: %s\n", message);
    }
    /**
     * send message to log facility with WARNING level
     * @param message message
     * @param showToUser show message to User in console?
     */
    public static void logWarning(String message, boolean showToUser) {
        logger.warning(message);
        if (showToUser) System.out.printf("WARNING: %s\n", message);
    }
    /**
     * send message to log facility with ERROR level
     * @param message message
     * @param showToUser show message to User in console?
     */
    public static void logError(String message, boolean showToUser) {
        logger.severe(message);
        if (showToUser) System.out.printf("ERROR: %s\n", message);
    }
    public static void setExecutionError() {
        executionError = true;
    }
}
