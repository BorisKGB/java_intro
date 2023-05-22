package ru.java.study.lesson6.hw;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LaptopSearchApp {
    public static Scanner iScanner;
    private static Logger logger;
    private static String logFile =
            String.join(File.separator, new String[] {"data", "lesson6", "hw", "LaptopSearchApp.log"});
    public static String csvFilePath =
            String.join(File.separator, new String[] {"data", "lesson6", "hw", "laptops_some_data.csv"});
    public static String csvFileDataDelimiter = "|";
    public static boolean executionError = false;

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
        LaptopSearchData.loadCSV(csvFilePath, csvFileDataDelimiter);
        if (executionError) return 1;
        logInfo(String.format("Из файла '%s' получено %d записей.", csvFilePath, LaptopSearchData.laptops.size()),
                true);
        boolean continueFlag = true;
        showMenu();
        logDebug("Application end");
        return 0;
    }

    private static void showMenu() {
        System.out.println("There will be menu here, believe me");
    }

    /**
     * Set logging to 'LaptopSearchApp.logFile' only.
     * If unable to open 'logFile' print error message and set executionError flag.
     */
    private static void configureLogger() {
        logger = Logger.getLogger(LaptopSearchApp.class.getName());
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.FINE);
        try {
            FileHandler fh = new FileHandler(logFile);
            logger.addHandler(fh);
            fh.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            System.out.printf("ERROR: Unable to open log file '%s'", logFile);
            executionError = true;
            return;
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
}
