package ru.java.study.lesson3.hw.task1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    /**
     *  1. Дан массив записей: наименование товара, цена, сорт.
     *  Найти наибольшую цену товаров 1го или 2го сорта среди товаров, название которых содержит «высший».
     */
    public static void main(String[] args) {
        List<Product> products = createList();
        System.out.println(products);
        System.out.println("findMaxPriceWFilter = " + findMaxPriceWFilter(products));
    }

    /**
     * @param list list of products
     * @return Max price of pruducts of 1st or 2nd grade and name contains "высший"
     */
    private static int findMaxPriceWFilter(List<Product> list) {
        int maxPrice = 0;
        for (Product elem : list) {
            if (elem.getName().contains("высший") && (elem.getGrade() == 1 || elem.getGrade() == 2)){
                if (elem.getPrice() > maxPrice) maxPrice = elem.getPrice();
            }
        }
        return maxPrice;
    }

    /**
     * @return generated List of products
     */
    public static List<Product> createList(){
        List<Product> list = new ArrayList<>();
        list.add(new Product("товар", 1235, 2));
        list.add(new Product("товар высший", 1651, 1));
        list.add(new Product("товар высший", 1525, 2));
        list.add(new Product("высший товар", 1415, 2));
        list.add(new Product("товар2", 1215, 2));
        list.add(new Product("товар3", 1535, 1));
        return list;
    }
}
