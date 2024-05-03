package org.example.DTO;

/**
 * This class provides the DTO functionality for the Orders class.
 */
public class OrdersDTO {
    private final String clientName;
    private final String productName;
    private final int quantity;


    /**
     * Constructs a new OrdersDTO.
     * @param clientName the name of the client
     * @param productName the name of the product
     * @param quantity the quantity of the product
     */
    public OrdersDTO(String clientName, String productName, int quantity){
        super();
        this.clientName = clientName;
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getClientName() {
        return clientName;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

}
