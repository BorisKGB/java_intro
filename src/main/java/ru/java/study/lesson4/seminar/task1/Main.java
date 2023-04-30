package ru.java.study.lesson4.seminar.task1;

import static ru.java.study.lesson2.hw.Lib.generateNumbersArray;

public class Main {
    /**
     * <a href="https://leetcode.com/problems/reverse-linked-list/">Reverse Linked List</a>
     */
    public static void main(String[] args) {
        ListNode myList = null;
        for (int i = 5; i>=1; i--) {
            if (myList != null) myList = new ListNode(i, myList);
            else myList = new ListNode(i);
        }
        ListNode myListGenerated = generateList(5, 0,10);
        printList(myList);
        printList(reverseList(myList));
        System.out.println("for generated list");
        printList(myListGenerated);
        printList(reverseList(myListGenerated));
    }

    public static void printList(ListNode head) {
        printList(head, " ");
    }
    public static void printList(ListNode head, String delimitter) {
        while (head != null) {
            if (head.next != null) System.out.print(head.val + delimitter);
            else System.out.print(head.val);
            head = head.next;
        }
        System.out.println();
    }

    public static ListNode reverseList(ListNode head) {
        ListNode result = null;
        if (head != null) {
            result = new ListNode(head.val);
            head = head.next;
        }
        while (head != null) {
            result = new ListNode(head.val, result);
            head = head.next;
        }
        return result;
    }

    public static ListNode reverseList2(ListNode head) {
        ListNode result = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = result;
            result = head;
            head = tmp;
        }
        return result;
    }

    public static ListNode generateList(int size, int min, int max) {
        ListNode head = null;
        ListNode current = new ListNode();
        for (int number : generateNumbersArray(size, min, max)) {
            if (head == null) {
                head = new ListNode(number);
                current = head;
            } else {
                current.next = new ListNode(number);
                current = current.next;
            }
        }
        return head;
    }
}
