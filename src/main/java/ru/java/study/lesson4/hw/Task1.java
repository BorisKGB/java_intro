package ru.java.study.lesson4.hw;

import ru.java.study.lesson4.seminar.task1.ListNode;

import java.util.Collections;
import java.util.LinkedList;

import static ru.java.study.lesson2.hw.Lib.generateNumbersArray;
import static ru.java.study.lesson4.seminar.task1.Main.*;

public class Task1 {
    /**
     * <p>Вывести список на экран в перевернутом виде (без массивов и ArrayList)<br>
     * Пример:<br>
     * 1 -> 2->3->4<br>
     * Вывод:<br>
     * 4->3->2->1</p>
     */
    public static void main(String[] args) {
        LinkedList<Integer> list = generateLinkedList(5, 0, 10);
        System.out.println(list);
        System.out.println(reverseLinkedList(list));
        System.out.println("---alternative solution---");
        ListNode otherList = generateList(5, 0, 10);
        printList(otherList, "->");
        printList(reverseList(otherList), "->");
    }

    /**
     * @param list linkedList
     * @return linkedList with reversed elements order
     */
    private static LinkedList<Integer> reverseLinkedList(LinkedList<Integer> list) {
        LinkedList<Integer> result = new LinkedList<>();
        LinkedList<Integer> listCopy = cloneLinkedList(list);
        while (!listCopy.isEmpty()) result.addFirst(listCopy.poll());
        return result;
    }

    /**
     * @param list source list
     * @return return copy of linkedList
     */
    private static LinkedList<Integer> cloneLinkedList(LinkedList<Integer> list) {
        LinkedList<Integer> sourceList = new LinkedList<>(Collections.nCopies(list.size(), 0));
        Collections.copy(sourceList, list);
        return sourceList;
    }

    /**
     * @param size number of numbers to generate
     * @param min mix number to generate
     * @param max max number to generate
     * @return gereneted linkedList. Unoptimized reuse of already existed generateNumbersArray function
     */
    private static LinkedList<Integer> generateLinkedList(int size, int min, int max) {
        LinkedList<Integer> result = new LinkedList<>();
        for (int number : generateNumbersArray(size, min, max)) {
            result.add(number);
        }
        return result;
    }
}
