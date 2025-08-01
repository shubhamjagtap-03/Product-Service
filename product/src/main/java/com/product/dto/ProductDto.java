package com.product.dto;


public class ProductDto {
    private String name;
    private double price;

    // Constructors
    public ProductDto() {}

    public ProductDto(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
