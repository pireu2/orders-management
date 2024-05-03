package org.example.Connection;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.postgresql.Driver;

public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://localhost:5432/orders_db";
    private static final String USER = "orders_app";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String PASSWORD = "1234";
    private static final ConnectionFactory connectionFactory = new ConnectionFactory();

    public static Connection getConnection(){
        return connectionFactory.createConnection();
    }

    public static void close(Connection connection) throws  RuntimeException{
        if(connection != null){
            try{
                connection.close();
            }
            catch(SQLException e){
                throw new RuntimeException("Error closing connection", e);
            }
        }
    }
    public static void close(Statement statement) throws  RuntimeException{
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException("Error closing statement", e);
            }
        }
    }
    public static void close(ResultSet resultSet) throws  RuntimeException{
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException("Error closing result set", e);
            }
        }
    }
    private ConnectionFactory(){
        try{
            Class.forName(DRIVER);
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    private Connection createConnection(){
        Connection connection = null;
        try{
            Properties properties = new Properties();
            properties.setProperty("user", USER);
            properties.setProperty("password", PASSWORD);
            connection = new Driver().connect(URL, properties);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}






