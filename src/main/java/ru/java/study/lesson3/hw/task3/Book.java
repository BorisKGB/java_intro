package ru.java.study.lesson3.hw.task3;

public class Book {
//    названия, фамилии автора, цены, года издания и количества страниц.
    private String name;
    private String author;
    private Integer price;
    private Integer publishYear;
    private Integer pages;

    public Book(String name, String author, Integer price, Integer publishYear, Integer pages) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.publishYear = publishYear;
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", publishYear=" + publishYear +
                ", pages=" + pages +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
