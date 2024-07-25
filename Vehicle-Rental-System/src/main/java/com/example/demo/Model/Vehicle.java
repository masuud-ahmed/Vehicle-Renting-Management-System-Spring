package com.example.demo.Model;

import jakarta.persistence.*;


@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VehicleID")
    private Long id;

    @Column(name = "Make")
    private String make;

    @Column(name = "Model")
    private String model;

    @Column(name = "Type")
    private String type;

    @Column(name = "Year")
    private int year;

    @Column(name = "PricePerDay")
    private double pricePerDay;

    // Constructors, getters, setters, toString

    public Vehicle() {
    }

    public Vehicle(String make, String model, String type, int year, double pricePerDay) {
        this.make = make;
        this.model = model;
        this.type = type;
        this.year = year;
        this.pricePerDay = pricePerDay;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", year=" + year +
                ", pricePerDay=" + pricePerDay +
                '}';
    }
}
