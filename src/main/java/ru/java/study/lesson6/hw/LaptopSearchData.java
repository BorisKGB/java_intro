package ru.java.study.lesson6.hw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class LaptopSearchData {
    public static Set<LaptopObj> laptops = new HashSet<>();

    public static void loadCSV(String filePath, String csvDelimiter) {
        LaptopSearchApp.logInfo(String.format("Loading csv file '%s'", filePath), false);
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();
            int lineNum = 1;
            while (line != null) {
                if (lineNum != 1) {
                    String[] elements = line.split(Pattern.quote(csvDelimiter));
                    try {
                        laptops.add(new LaptopObj(elements));
                    } catch (IllegalArgumentException e) {
                        LaptopSearchApp.logInfo(String.format("Ошибка загрузки данных из строки %d", lineNum), false);
                    }
                }
                line = br.readLine();
                lineNum++;
            }
            br.close();
        } catch (IOException e) {
            LaptopSearchApp.logError(e.toString(), true);
            LaptopSearchApp.executionError = true;
        }
    }

    /**
     * Fancy print laptops
     * @param laptops Set of LaptopObj to print
     */
    public static void printLaptops(Set<LaptopObj> laptops) {
        int counter = 1;
        for (LaptopObj laptop : laptops) {
            String diskSize = (laptop.getStorageSize() % 1024 == 0) ? laptop.getStorageSize()/1024+"TB" : laptop.getStorageSize()+"GB";
            System.out.printf("%d: %s %s, %s (%.1f\"/%s %s/%.0fGB RAM/%s %s) with %s\n", counter,
                    laptop.getManufacturer(), laptop.getModel(), laptop.getColor(), laptop.getScreenSize(),
                    laptop.getCpuManufacturer(), laptop.getCpuModel(), laptop.getRamSize(),
                    diskSize, laptop.getStorageType(), laptop.getSystem());
            counter++;
        }
    }
}
