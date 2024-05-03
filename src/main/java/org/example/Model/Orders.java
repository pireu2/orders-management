package org.example.Model;

/**
 * This class represents an Order with id, client_id, product_id, and quantity.
 */
public class Orders {
    private int id;
    private int client_id;


    private int product_id;
    private int quantity;

    /**
     * Default constructor.
     */
    public Orders(){
    }

    /**
     * Constructor with all parameters.
     *
     * @param id the id of the order
     * @param client_id the client_id of the order
     * @param product_id the product_id of the order
     * @param quantity the quantity of the order
     */
    public Orders(int id, int client_id, int product_id, int quantity){
        super();
        this.id = id;
        this.client_id = client_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    /**
     * Constructor without id.
     *
     * @param client_id the client_id of the order
     * @param product_id the product_id of the order
     * @param quantity the quantity of the order
     */
    public Orders(int client_id, int product_id, int quantity){
        super();
        this.client_id = client_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
        return;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString(){
        return "Order{" +
                "id=" + id +
                ", client_id=" + client_id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                '}';
    }
}
