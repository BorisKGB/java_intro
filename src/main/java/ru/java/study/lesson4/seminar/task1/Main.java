package ru.java.study.lesson4.seminar.task1;

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
        printList(myList);
        myList = reverseList(myList);
        printList(myList);
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
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
}
