package org.example.DTO;

public class OrdersDTO {
    private final String clientName;
    private final String productName;
    private final int quantity;


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
