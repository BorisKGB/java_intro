package ru.java.study.lesson3.hw.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     *  <p>2. Сведения о товаре состоят из<br>
     *      наименования, страны-производителя, веса, цены, сорта.<br>
     *  Получить наименования товаров заданного сорта с наименьшей ценой.</p>
     */
    public static void main(String[] args) {
        List<Product> products = createList();
        System.out.println(products);
        Scanner iScaner = new Scanner(System.in);
        System.out.print("Введите сорт для поиска товаров (число)> ");
        int targetGrade = iScaner.nextInt();
        List<String> productNames = findNamesWLowestPriceByGrade(products, targetGrade);
        System.out.println(productNames);
    }

    /**
     * @param data Product list
     * @param targetGrade Grade filter
     * @return List of product names with minimum price
     */
    private static List<String> findNamesWLowestPriceByGrade(List<Product> data, int targetGrade) {
        List<String> productNames = new ArrayList<>();
        Integer minPrice = Integer.MAX_VALUE;
        for (Product elem : data) {
            if (elem.getGrade().equals(targetGrade)){
                if (elem.getPrice().equals(minPrice) && productNames.lastIndexOf(elem.getName()) == -1) productNames.add(elem.getName());
                if (elem.getPrice() < minPrice) {
                    productNames = new ArrayList<>();
                    productNames.add(elem.getName());
                    minPrice = elem.getPrice();
                }
            }
        }
        return productNames;
    }

    /**
     * @return generated List of products
     */
    public static List<Product> createList(){
        List<Product> list = new ArrayList<>();
        list.add(new Product("товар6", "country2", 0.3, 1000, 1));
        list.add(new Product("товар2", "country1", 2.0, 850, 1));
        list.add(new Product("товар5", "country2", 2.0, 850, 1));
        list.add(new Product("товар7", "country3", 1.5, 850, 1));
        list.add(new Product("товар4", "country2", 1.5, 1413, 2));
        list.add(new Product("товар8", "country3", 1.5, 1413, 2));
        list.add(new Product("товар1", "country1", 1.5, 1423, 2));
        list.add(new Product("товар3", "country1", 0.3, 888, 3));
        return list;
    }
}
