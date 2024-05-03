package org.example.DAO;

import org.example.Connection.ConnectionFactory;
import org.example.Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * This class provides the DAO functionality for the Product class.
 */
public class ProductDAO extends AbstractDAO<Product> {

    /**
     * Constructs a new ProductDAO.
     */
    public ProductDAO() {
        super();
    }

    /**
     * Decrements the stock of a product by a given quantity.
     * @param product the product to decrement the stock of
     * @param quantity the quantity to decrement the stock by
     */
    public void decrementStock(Product product, int quantity) {
        int newStock = product.getStock() - quantity;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("UPDATE product SET stock = ? WHERE id = ?");
            statement.setInt(1, newStock);
            statement.setInt(2, product.getId());
            statement.executeUpdate();
        }
        catch(Exception e){
            LOGGER.severe(e.toString());
        }
        finally{
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
