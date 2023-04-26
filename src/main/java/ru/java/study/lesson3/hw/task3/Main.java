package ru.java.study.lesson3.hw.task3;

import java.util.ArrayList;
import java.util.List;

import static ru.java.study.lesson2.hw.Task1.isPrimeNumber;

public class Main {
    /**
     *  <p>3. Сведения о книге состоят из
     *  названия, фамилии автора, цены, года издания и количества страниц.
     *  Найти названия книг, в которых:<br>
     *   - простое количество страниц<br>
     *   - фамилия автора содержит «А»<br>
     *   - год издания после 2010 г, включительно</p>
     */
    public static void main(String[] args) {
        List<Book> books = createList();
        System.out.println(books);
        System.out.println(filterBooks(books));
    }

    /**
     * @param books books list
     * @return books list filtered by 1) pages is Prime number 2) PublistYear >= 2010 3) Book.author.contains "А"
     */
    private static List<Book> filterBooks(List<Book> books) {
        List<Book> suitableBooks = new ArrayList<>();
        for (Book book : books) {
            if (isPrimeNumber(book.getPages()) && book.getPublishYear() >= 2010 && book.getAuthor().contains("А")) suitableBooks.add(book);
        }
        return suitableBooks;
    }

    /**
     * @return generated List of products
     */
    public static List<Book> createList(){
        List<Book> list = new ArrayList<>();
        list.add(new Book("book1", "Аauthor1", 352, 1983, 1000));
        list.add(new Book("book2", "authАor2", 234, 2010, 610));
        list.add(new Book("book3", "author3", 525, 1972, 115));
        list.add(new Book("book4", "author4", 143, 2015, 251));
        list.add(new Book("book5", "author5А", 123, 2008, 320));
        list.add(new Book("book6", "author6", 512, 1992, 160));
        list.add(new Book("book7", "auАthor7", 162, 2020, 79));
        list.add(new Book("book8", "author8", 612, 1992, 250));
        list.add(new Book("book9", "author9А", 162, 2012, 181));
        return list;
    }
}
