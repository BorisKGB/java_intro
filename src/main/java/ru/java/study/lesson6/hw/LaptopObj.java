package ru.java.study.lesson6.hw;

import java.util.HashSet;
import java.util.Set;

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
    private static Set<String> uniqueManufacturer = new HashSet<>();
    private static Set<String> uniqueModel = new HashSet<>();
    private static Set<Double> uniqueScreenSize = new HashSet<>();
    private static Set<String> uniqueCPUManufacturer = new HashSet<>();
    private static Set<String> uniqueCPUModel = new HashSet<>();
    private static Set<Double> uniqueRamSize = new HashSet<>();
    private static Set<Double> uniqueStorageSize = new HashSet<>();
    private static Set<String> uniqueStorageType = new HashSet<>();
    private static Set<String> uniqueSystem = new HashSet<>();
    private static Set<String> uniqueColor = new HashSet<>();

    /**
     * Load object from Array of Strings as parameters.
     * Empty or incorrect parameters treated an 'null'
     * Throw IllegalArgumentException only if got incorrect array size.
     * update Set of unique* fields with new values
     * @param values array of laptop parameters
     */
    public LaptopObj(String[] values) {
        if (values.length != 10) throw new IllegalArgumentException("Incorrect values count");
        this.manufacturer = values[0];
        uniqueManufacturer.add(manufacturer);
        this.model = values[1];
        uniqueModel.add(model);
        try {
            this.screenSize = Double.parseDouble(values[2]);
        } catch (NumberFormatException e) {
            LaptopSearchApp.logWarning(String.format("Unable to parse 'screenSize' column, value '%s' treated as 'not set'", values[2]), false);
            this.screenSize = null;
        }
        uniqueScreenSize.add(screenSize);
        this.cpuManufacturer = values[3];
        uniqueCPUManufacturer.add(cpuManufacturer);
        this.cpuModel = values[4];
        uniqueCPUModel.add(cpuModel);
        try {
            this.ramSize = Double.parseDouble(values[5]);
        } catch (NumberFormatException e) {
            LaptopSearchApp.logWarning(String.format("Unable to parse 'ramSize' column, value '%s' treated as 'not set'", values[5]), false);
            this.ramSize = null;
        }
        uniqueRamSize.add(ramSize);
        try {
            this.storageSize = Double.parseDouble(values[6]);
        } catch (NumberFormatException e) {
            LaptopSearchApp.logWarning(String.format("Unable to parse 'storageSize' column, value '%s' treated as 'not set'", values[6]), false);
            this.storageSize = null;
        }
        uniqueStorageSize.add(storageSize);
        this.storageType = values[7];
        uniqueStorageType.add(storageType);
        this.system = values[8];
        uniqueSystem.add(system);
        this.color = values[9];
        uniqueColor.add(color);
    }
    public static Set<String> getUniqueManufacturer() {
        return uniqueManufacturer;
    }

    public static Set<String> getUniqueModel() {
        return uniqueModel;
    }

    public static Set<Double> getUniqueScreenSize() {
        return uniqueScreenSize;
    }

    public static Set<String> getUniqueCPUManufacturer() {
        return uniqueCPUManufacturer;
    }

    public static Set<String> getUniqueCPUModel() {
        return uniqueCPUModel;
    }

    public static Set<Double> getUniqueRamSize() {
        return uniqueRamSize;
    }

    public static Set<Double> getUniqueStorageSize() {
        return uniqueStorageSize;
    }

    public static Set<String> getUniqueStorageType() {
        return uniqueStorageType;
    }

    public static Set<String> getUniqueSystem() {
        return uniqueSystem;
    }

    public static Set<String> getUniqueColor() {
        return uniqueColor;
    }
}
