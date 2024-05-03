package org.example.DTO;

/**
 * This class provides the DTO functionality for the Product class.
 */
public class ProductDTO {
    private final String name;
    private final int stock;
    private final double price;

    /**
     * Constructs a new ProductDTO.
     * @param name the name of the product
     * @param price the price of the product
     * @param stock the stock of the product
     */
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
