package org.example.DAO;

import org.example.Connection.ConnectionFactory;
import org.example.Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProductDAO extends AbstractDAO<Product> {
    public ProductDAO() {
        super();
    }

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
