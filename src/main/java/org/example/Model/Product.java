package org.example.Model;

/**
 * This class represents a Product with id, name, price, and stock.
 */
public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

    /**
     * Default constructor.
     */
    public Product(){
    }

    /**
     * Constructor with all parameters.
     *
     * @param id the id of the product
     * @param name the name of the product
     * @param price the price of the product
     * @param stock the stock of the product
     */
    public Product(int id, String name, double price, int stock){
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Constructor without id.
     *
     * @param name the name of the product
     * @param price the price of the product
     * @param stock the stock of the product
     */
    public Product(String name, double price, int stock){
        super();
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name= name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
