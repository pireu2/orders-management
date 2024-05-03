package org.example.Logic;

import org.example.DAO.ProductDAO;
import org.example.DTO.ProductDTO;
import org.example.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductLogic {
    public static void createProduct(ProductDTO productDTO){
        Product product = new Product(productDTO.getName(), productDTO.getPrice(), productDTO.getStock());
        ProductDAO productDAO = new ProductDAO();
        productDAO.insert(product);
    }

    public static void deleteProduct(String name){
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findByName(name);
        productDAO.delete(product.getId());
    }
    public static List<ProductDTO> getAllProducts(){
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(Product product : products){
            ProductDTO productDTO = new ProductDTO(product.getName(),product.getStock(), product.getPrice());
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    public static List<String> getAllProductsName() {
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.findAll();
        List<String> productNames = new ArrayList<>();
        for(Product product : products){
            productNames.add(product.getName());
        }
        return productNames;
    }
}
