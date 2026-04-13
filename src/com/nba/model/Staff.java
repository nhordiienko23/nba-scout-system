package com.nba.model;

import java.io.Serializable;

public abstract class Staff implements Serializable, Taxable {
    private static final long serialVersionUID = 1L;
    private static int idCounter = 1;
    private final int id;
    private String name;
    private double baseSalary;

    public Staff(String name, double baseSalary) {
        this.id = idCounter++;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public abstract double calculateBonus();

    public int getId() {
        return id;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public double calculateTax() {
        return (baseSalary + calculateBonus()) * TAX_RATE;
    }

    @Override
    public String toString() {
        double total = baseSalary + calculateBonus();
        return String.format("ID: %03d | Name: %-15s | Total Pay: $%,.0f | Tax: $%,.0f",
                id, name, total, calculateTax());
    }
}
