package ru.java.study.lesson3.seminar.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * Даны сведения об экспортируемых товарах: указывается
     *   наименование товара,
     *   страна, экспортирующая товар,
     *   и объем поставляемой партии в штуках.
     * Найти страны, которые экспортируют данный(введенный с кл-ы) товар, и общий объем его экспорта.
     */
    public static void main(String[] args) {
        Product pr = new Product("qwe", "zxc", 3);
        Product pr2 = new Product("qwe", "zxc2", 3);
        Product pr3 = new Product("qwe", "zxc2", 3);
        List<Product> productList = new ArrayList<>();
        productList.add(pr);
        productList.add(pr2);
        productList.add(pr3);
        System.out.println(productList);
        Scanner sc = new Scanner(System.in);
        String searchNameProduct = sc.nextLine();
        Integer exportVolume = 0;
        List<String> countryList = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).name.equals(searchNameProduct)) {
                exportVolume += productList.get(i).volume;
                if (!countryList.contains(productList.get(i).country)) countryList.add(productList.get(i).country);
            }
        }
        System.out.println(countryList);
        System.out.println(exportVolume);
    }
}
