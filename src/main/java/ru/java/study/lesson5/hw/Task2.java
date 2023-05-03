package ru.java.study.lesson5.hw;

import java.util.*;

public class Task2 {
    static List<String> employeeList = new ArrayList<>(Arrays.asList(
            "Иван Иванов",
            "Светлана Петрова",
            "Кристина Белова",
            "Анна Мусина",
            "Анна Крутова",
            "Иван Юрин",
            "Петр Лыков",
            "Павел Чернов",
            "Петр Чернышов",
            "Мария Федорова",
            "Марина Светлова",
            "Мария Савина",
            "Мария Рыкова",
            "Марина Лугова",
            "Анна Владимирова",
            "Иван Мечников",
            "Петр Петин",
            "Иван Ежов"
    ));
    /**
     *  Пусть дан список сотрудников.
     *  Написать программу, которая найдёт и выведет повторяющиеся имена с количеством повторений.
     *  Отсортировать по убыванию популярности.
     */
    public static void main(String[] args) {
        printEmployeeNamesByPopularity(employeeList);
    }

    /**
     * Count and print names of employees sorted by popularity.
     * @param employees list of employees
     */
    private static void printEmployeeNamesByPopularity(List<String> employees) {
        Map<String, Integer> nameCounter = new HashMap<>();
        int maxCounter = 1;
        for (String employee : employees) {
            employee = employee.split(" ")[0];
            if (nameCounter.containsKey(employee)) {
                nameCounter.replace(employee, nameCounter.get(employee)+1);
                if (nameCounter.get(employee) > maxCounter) maxCounter = nameCounter.get(employee);
            }
            else nameCounter.put(employee, 1);
        }
        System.out.println("Список имён по убыванию повторений:");
        while (!nameCounter.isEmpty()) {
            if (nameCounter.containsValue(maxCounter)) {
                String key = keyFromValue(nameCounter, maxCounter);
                System.out.printf("%s: %d\n", key, maxCounter);
                nameCounter.remove(key, maxCounter);
            } else maxCounter--;
        }
    }

    /**
     * @param map Map where to look for the keys
     * @param targetValue value to search for
     * @return first key associated with "targetValue" or null
     */
    private static String keyFromValue(Map<String, Integer> map, Integer targetValue) {
        for (Map.Entry<String, Integer> element : map.entrySet()) {
            if (element.getValue().equals(targetValue)) return element.getKey();
        }
        return null;
    }
}
