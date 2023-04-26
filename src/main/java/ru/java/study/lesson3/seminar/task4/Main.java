package ru.java.study.lesson3.seminar.task4;

import java.util.ArrayList;
import java.util.List;

public class Main {
    /**
     * Напишите метод, который на вход получает коллекцию объектов, а возвращает коллекцию уже без дубликатов.
     */
    public static void main(String[] args) {
        List<MyObject> list = new ArrayList<>();
        list.add(new MyObject(5));
        list.add(new MyObject(5));
        list.add(new MyObject(3));
        list.add(new MyObject(6));
        list.add(new MyObject(1));
        System.out.println(list);
        System.out.println(uniqueList(list));
    }
    public static List<MyObject> uniqueList(List<MyObject> srcList){
        List<MyObject> resultList = new ArrayList<>();
        for (MyObject obj : srcList) {
            if (!resultList.contains(obj)) resultList.add(obj);
        }
        return resultList;
    }
}
