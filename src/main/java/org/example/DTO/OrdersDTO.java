package org.example.DTO;

public class OrderDTO {
    private String clientName;
    private String productName;
    private int quantity;

    public OrderDTO(){
    }

    public OrderDTO(String clientName, String productName, int quantity){
        super();
        this.clientName = clientName;
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
