package org.example.Logic;

import org.example.DAO.ProductDAO;
import org.example.DTO.ProductDTO;
import org.example.Model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the logic for the Product operations.
 */
public class ProductLogic {

    /**
     * Creates a new product.
     *
     * @param productDTO the product to create
     */
    public static void createProduct(ProductDTO productDTO){
        Product product = new Product(productDTO.getName(), productDTO.getPrice(), productDTO.getStock());
        ProductDAO productDAO = new ProductDAO();
        productDAO.insert(product);
    }

    /**
     * Deletes a product.
     *
     * @param name the name of the product to delete
     */
    public static void deleteProduct(String name){
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findByName(name);
        productDAO.delete(product.getId());
    }

    /**
     * Edits a product.
     *
     * @param name the name of the product to edit
     * @param productDTO the new product data
     */
    public static void editProduct(String name, ProductDTO productDTO){
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findByName(name);
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        productDAO.update(product, product.getId());
    }

    /**
     * Retrieves all products.
     *
     * @return a list of all products
     */
    public static List<ProductDTO> getAllProducts(){
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(Product product : products){
            ProductDTO productDTO = new ProductDTO(product.getName(), product.getPrice(),product.getStock());
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    /**
     * Retrieves all product names.
     *
     * @return a list of all product names
     */
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
