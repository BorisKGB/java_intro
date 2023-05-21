package ru.java.study.lesson6.hw;

import java.io.File;
import java.util.Scanner;
import java.util.logging.Logger;

public class LaptopSearchApp {
    public static Scanner iScanner;
    public static Logger logger;
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
        logger = Logger.getLogger(LaptopSearchApp.class.getName());
        // load laptops
        //  1. Ask 'path to csv file for loading' and 'delimiter'
        //  2. Try to load it
        // show menu with search options
        logger.info("Service starting");
        validateCSVFileParams();
        LaptopSearchData.loadCSV(csvFilePath, csvFileDataDelimiter);
        /*
         TODO: think about other way to return error code in main class.
           Now do: catch exception, print error log 'in place' and set executionError flag.
           Probably can define custom exception and catch in in main, as described in https://stackoverflow.com/questions/6171265/best-way-to-exit-a-program-when-i-want-an-exception-to-be-thrown/6171351#6171351
        */
        if (executionError) return 1;
        printLoadedStatistics();
        System.out.println("There will be something");
        return 0;
    }

    /**
     * Print object counter, loaded from csvFilePath
     */
    private static void printLoadedStatistics() {
        System.out.printf("Из файла '%s' получено %d записей.\n",
                csvFilePath, LaptopSearchData.laptops.size());
    }

    /**
     * Ask user what csv file and delimiter to use
     */
    public static void validateCSVFileParams() {
        System.out.printf("Использовать данные из файла '%s'? (Y/n)\n", csvFilePath);
        String choice = iScanner.nextLine();
        if (!(choice.equals("") || choice.equalsIgnoreCase("y"))) {
            System.out.print("Введите путь до файла относительно корня проекта:\n> ");
            csvFilePath = iScanner.nextLine();
        }
        System.out.printf("Использовать '%s' как разделитель ячеек в файле '%s'? (Y/n)\n",
                csvFileDataDelimiter, csvFilePath);
        choice = iScanner.nextLine();
        if (!(choice.equals("") || choice.equalsIgnoreCase("y"))) {
            System.out.print("Укажите свой разделитель: ");
            csvFileDataDelimiter = iScanner.nextLine();
        }
    }
}
