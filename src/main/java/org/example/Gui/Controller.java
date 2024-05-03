package org.example.Gui;

import org.example.DTO.ClientDTO;
import org.example.DTO.LogDTO;
import org.example.DTO.OrdersDTO;
import org.example.DTO.ProductDTO;
import org.example.Logic.ClientLogic;
import org.example.Logic.OrderLogic;
import org.example.Logic.ProductLogic;
import org.example.Model.Log;
import org.example.Logic.LogLogic;

import java.util.List;

/**
 * This class provides the Controller functionality.
 */
public class Controller {

    /**
     * Creates a new client.
     *
     * @param clientDTO the client to create
     */
    public static void createClient(ClientDTO clientDTO){
        ClientLogic.createClient(clientDTO);
    }

    /**
     * Creates a new product.
     *
     * @param productDTO the product to create
     */
    public static void createProduct(ProductDTO productDTO){
        ProductLogic.createProduct(productDTO);
    }

    /**
     * Creates a new order.
     *
     * @param orderDTO the order to create
     * @throws IllegalArgumentException if the order cannot be created
     */
    public static void createOrder(OrdersDTO orderDTO) throws IllegalArgumentException{
        OrderLogic.createOrder(orderDTO);
    }

    /**
     * Retrieves all clients.
     *
     * @return a list of all clients
     */
    public static List<ClientDTO> getAllClients(){
        return ClientLogic.getAllClients();
    }

    /**
     * Retrieves all products.
     *
     * @return a list of all products
     */
    public static List<ProductDTO> getAllProducts(){
        return ProductLogic.getAllProducts();
    }

    /**
     * Retrieves all orders.
     *
     * @return a list of all orders
     */
    public static List<OrdersDTO> getAllOrders(){
        return OrderLogic.getAllOrders();
    }

    /**
     * Retrieves all logs.
     *
     * @return a list of all logs
     */
    public static List<LogDTO> getAllLogs(){
        return LogLogic.getAllLogs();
    }

    /**
     * Retrieves all client names.
     *
     * @return a list of all client names
     */
    public static List<String> getAllClientsNames(){
        return ClientLogic.getAllClientsName();
    }

    /**
     * Retrieves all product names.
     *
     * @return a list of all product names
     */
    public static List<String> getAllProductsName(){
        return ProductLogic.getAllProductsName();
    }

    /**
     * Deletes a client.
     *
     * @param name the name of the client to delete
     */
    public static void deleteClient(String name) {
        ClientLogic.deleteClient(name);
    }

    /**
     * Edits a client.
     *
     * @param name the name of the client to edit
     * @param clientDTO the new client data
     */
    public static void editClient(String name, ClientDTO clientDTO) {
        ClientLogic.editClient(name, clientDTO);
    }

    /**
     * Deletes a product.
     *
     * @param name the name of the product to delete
     */
    public static void deleteProduct(String name) {
        ProductLogic.deleteProduct(name);
    }

    /**
     * Edits a product.
     *
     * @param name the name of the product to edit
     * @param productDTO the new product data
     */
    public static void editProduct(String name, ProductDTO productDTO) {
        ProductLogic.editProduct(name, productDTO);
    }
}
