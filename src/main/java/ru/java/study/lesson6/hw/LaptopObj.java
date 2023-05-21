package ru.java.study.lesson6.hw;

import java.util.regex.Pattern;

public class LaptopObj {
    private String manufacturer;
    private String model;
    private Double screenSize;
    private String cpuManufacturer;
    private String cpuModel;
    private Double ramSize;
    private Double storageSize;
    private String storageType;
    private String system;
    private String color;

    public LaptopObj(String[] values) {
        if (values.length != 10) throw new IllegalArgumentException("Incorrect values count");
        this.manufacturer = values[0];
        this.model = values[1];
        try {
            this.screenSize = Double.parseDouble(values[2]);
        } catch (NumberFormatException e) {
            // TODO: add ERR message
            this.screenSize = null;
        }
        this.cpuManufacturer = values[3];
        this.cpuModel = values[4];
        try {
            this.ramSize = Double.parseDouble(values[5]);
        } catch (NumberFormatException e) {
            // TODO: add ERR message
            this.ramSize = null;
        }
        try {
            this.storageSize = Double.parseDouble(values[6]);
        } catch (NumberFormatException e) {
            // TODO: add ERR message
            this.storageSize = null;
        }
        this.storageType = values[7];
        this.system = values[8];
        this.color = values[9];
    }
}
