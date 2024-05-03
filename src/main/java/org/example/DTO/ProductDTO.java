package org.example.DTO;

public class ProductDTO {
    private final String name;
    private final int stock;
    private final double price;

    public ProductDTO(String name, double price, int stock){
        super();
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }
}
