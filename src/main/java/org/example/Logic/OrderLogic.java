package org.example.Logic;

import org.example.DAO.ClientDAO;
import org.example.DAO.LogDAO;
import org.example.DAO.OrdersDAO;
import org.example.DAO.ProductDAO;
import org.example.DTO.OrdersDTO;
import org.example.Model.Client;
import org.example.Model.Log;
import org.example.Model.Orders;
import org.example.Model.Product;

import java.util.List;

public class OrderLogic {
    public static void createOrder(OrdersDTO ordersDTO) throws IllegalArgumentException{
        ClientDAO clientDAO = new ClientDAO();
        Client client = clientDAO.findByName(ordersDTO.getClientName());
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findByName(ordersDTO.getProductName());
        if(client == null){
            throw new IllegalArgumentException("Client not found");
        }
        else if(product == null){
            throw new IllegalArgumentException("Product not found");
        }
        else if(product.getStock() < ordersDTO.getQuantity()){
            throw new IllegalArgumentException("Not enough stock");
        }
        else if(ordersDTO.getQuantity()  <= 0){
            throw new IllegalArgumentException("Invalid quantity");
        }
        productDAO.decrementStock(product, ordersDTO.getQuantity());
        Orders orders = new Orders(client.getId(), product.getId(), ordersDTO.getQuantity());
        OrdersDAO ordersDAO = new OrdersDAO();

        ordersDAO.insert(orders);

        Orders insertedOrder = ordersDAO.findLast();
        LogDAO logDAO = new LogDAO();
        Log log = new Log(insertedOrder.getId(),"Client: " + client.getName() + " Product: " + product.getName() + " Quantity: " + ordersDTO.getQuantity() + " Total: " + product.getPrice() * ordersDTO.getQuantity()  + " RON");
        logDAO.insert(log);
    }


    public static List<OrdersDTO> getAllOrders(){
        OrdersDAO ordersDAO = new OrdersDAO();
        List<Orders> orders = ordersDAO.findAll();
        List<OrdersDTO> ordersDTOS = new java.util.ArrayList<>();
        for(Orders order : orders){
            ClientDAO clientDAO = new ClientDAO();
            Client client = clientDAO.findById(order.getClient_id());
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.findById(order.getProduct_id());
            OrdersDTO ordersDTO = new OrdersDTO(client.getName(), product.getName(), order.getQuantity());
            ordersDTOS.add(ordersDTO);
        }
        return ordersDTOS;
    }
}
