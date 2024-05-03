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

public class Controller {
    public static void createClient(ClientDTO clientDTO){
        ClientLogic.createClient(clientDTO);
    }
    public static void createProduct(ProductDTO productDTO){
        ProductLogic.createProduct(productDTO);
    }
    public static void createOrder(OrdersDTO orderDTO) throws IllegalArgumentException{
        OrderLogic.createOrder(orderDTO);
    }

    public static List<ClientDTO> getAllClients(){
        return ClientLogic.getAllClients();
    }

    public static List<ProductDTO> getAllProducts(){
        return ProductLogic.getAllProducts();
    }

    public static List<OrdersDTO> getAllOrders(){
        return OrderLogic.getAllOrders();
    }

    public static List<LogDTO> getAllLogs(){
        return LogLogic.getAllLogs();
    }

    public static List<String> getAllClientsNames(){
        return ClientLogic.getAllClientsName();
    }

    public static List<String> getAllProductsName(){
        return ProductLogic.getAllProductsName();
    }

    public static void deleteClient(String name) {
        ClientLogic.deleteClient(name);
    }

    public static void editClient(String name, ClientDTO clientDTO) {
        ClientLogic.editClient(name, clientDTO);
    }

    public static void deleteProduct(String name) {
        ProductLogic.deleteProduct(name);
    }

    public static void editProduct(String name, ProductDTO productDTO) {
        ProductLogic.editProduct(name, productDTO);
    }
}
