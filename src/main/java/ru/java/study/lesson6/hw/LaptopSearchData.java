package ru.java.study.lesson6.hw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import static ru.java.study.lesson6.hw.LaptopSearchApp.logger;

public class LaptopSearchData {
    public static Set<LaptopObj> laptops = new HashSet<>();

    public static void loadCSV(String filePath, String csvDelimiter) {
        logger.info("file reading");
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
                        logger.warning("Ошибка загрузки данных из строки "+lineNum);
                    }
                }
                line = br.readLine();
                lineNum++;
            }
            br.close();
        } catch (IOException e) {
            logger.severe(e.toString());
            LaptopSearchApp.executionError = true;
        }
    }
}
