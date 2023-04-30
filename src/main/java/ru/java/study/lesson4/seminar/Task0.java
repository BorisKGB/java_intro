package ru.java.study.lesson4.seminar;

import java.util.LinkedList;
import java.util.Scanner;

public class Task0 {
    public static void main(String[] args) {
        LinkedList<Integer> integerLinkedList = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            integerLinkedList.add(scanner.nextInt());
        }
        scanner.close();
        System.out.println("peek = " + integerLinkedList.peek());
        System.out.println("list= " + integerLinkedList);
        System.out.println("pop = " + integerLinkedList.pop());
        System.out.println("list= " + integerLinkedList);
        //добавим в начало
        integerLinkedList.addFirst(3);
        System.out.println("add first list= " + integerLinkedList);
        //добавление в конец
        integerLinkedList.addLast(7);
        System.out.println("add last list= " + integerLinkedList);
        //замена элемента
        integerLinkedList.set(2, 6);
        System.out.println("set elem list= " + integerLinkedList);
        //проверить на наличие
        System.out.println("search element list(true/false)= " + integerLinkedList.contains(6));
        //удаление элемента (индекс)
        integerLinkedList.remove(1);
        System.out.println("remove elem list= " + integerLinkedList);
        //удаление элемента (объект)
        integerLinkedList.remove((Integer) 6);
        System.out.println("remove elem list= " + integerLinkedList);
    }
}
