package ru.java.study.lesson3.hw.task2;

public class Product {
    private String name;
    private String manufacturer;
    private double weight;
    private Integer price;
    private Integer grade;

    public Product(String name, String manufacturer, double weight, Integer price, Integer grade) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.weight = weight;
        this.price = price;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
