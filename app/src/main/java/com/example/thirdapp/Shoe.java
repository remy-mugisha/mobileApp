package com.example.thirdapp;

public class Shoe {
    private String brand;
    private int size;
    private boolean inStock;
    private double price;

    public Shoe(String brand, int size, boolean inStock, double price) {
        this.brand = brand;
        this.size = size;
        this.inStock = inStock;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public int getSize() {
        return size;
    }

    public boolean isInStock() {
        return inStock;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return brand + " - Size: " + size + ", Price: $" + String.format("%.2f", price) + ", In Stock: " + (inStock ? "Yes" : "No");
    }
}