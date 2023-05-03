package ru.java.study.lesson5.hw;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Task1 {
    /**
     * <p>Подсчитать количество искомого слова, через map (наполнением значение, где искомое слово будет являться ключом), вносить все слова не нужно<br>
     * Пример: "Россия идет вперед всей планеты. Планета — это не Россия."<br>
     * Запрос: Россия<br>
     * Ответ: Россия - 2<br>
     * (Усложнение - игнорировать пунктуацию)<br>
     * (Усложнение2 - выполнить чтение строки из файла)</p>
     */
    public static void main(String[] args) {
        String sample1 = "Россия идет вперед всей планеты. Планета — это не Россия.";
        Scanner iScanner = new Scanner(System.in);
        System.out.printf("Введите слово для поиска в строке\n%s\n> ", sample1);
        String word = iScanner.nextLine().toLowerCase();
        System.out.printf("Слов найдено = %d\n", countWord(sample1, word));
        String filePath = String.join(File.separator, new String[] {"data", "lesson5", "hw", "text.txt"});
        System.out.printf("Введите слово для поиска в файле по пути\n%s\n> ", filePath);
        word = iScanner.nextLine().toLowerCase();
        iScanner.close();
        System.out.printf("Слов найдено = %d\n", countWordInFile(filePath, word));
    }

    /**
     * @param filePath path to file to search in
     * @param targetWord word
     * @return number of "targetWord" occurrences in file on "filePath, ignore any not alphabetic symbols, use spaces as words delimiter and case-insensitive.<br>
     * If file not exist or unable to read it return 0 and sout error message
     */
    private static Integer countWordInFile(String filePath, String targetWord) {
        Map<String, Integer> counterMap = new HashMap<>();
        List<Character> allowedSymbols = new ArrayList<>(Arrays.asList('а', 'б', 'в', 'г', 'д',
                'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н',
                'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч',
                'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'));
        List<Character> separatorSymbols = new ArrayList<>(Arrays.asList(' ', '\n'));
        try {
            FileReader fr = new FileReader(filePath);
            StringBuilder currWord = new StringBuilder();
            boolean continueFileRead = true;
            while (continueFileRead) {
                int i = fr.read();
                char ch = Character.toLowerCase((char) i);
                if (allowedSymbols.contains(ch)) currWord.append(ch);
                if (separatorSymbols.contains(ch) || i == -1) {
                    if (targetWord.equals(currWord.toString().toLowerCase())) {
                        Integer currCount = counterMap.getOrDefault(targetWord, 0);
                        counterMap.put(targetWord, currCount + 1);
                    }
                    if (i == -1) continueFileRead = false;
                    currWord = new StringBuilder();
                }
            }
            fr.close();
        } catch (IOException e) {
            System.out.printf("WARNING: %s\n", e.getMessage());
            return 0;
        }
        return counterMap.getOrDefault(targetWord, 0);
    }

    /**
     * @param text text to search in
     * @param targetWord word
     * @return number of "targetWord" occurrences in "text", ignore punctuation marks and not case-sensitive
     */
    private static Integer countWord(String text, String targetWord) {
        Map<String, Integer> counterMap = new HashMap<>();
        for (String word : text.toLowerCase().replaceAll("[^а-я ]", "").split(" ")) {
            if (word.equals(targetWord)) {
                Integer currCount = counterMap.getOrDefault(word, 0);
                counterMap.put(word, currCount+1);
            }
        }
        return counterMap.getOrDefault(targetWord, 0);
    }
}
