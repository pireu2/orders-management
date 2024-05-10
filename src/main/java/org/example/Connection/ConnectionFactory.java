package org.example.Connection;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.postgresql.Driver;

/**
 * This class is responsible for creating and closing database connections.
 */
public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://localhost:5432/orders_app";
    private static final String USER = "postgres";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String PASSWORD = "";
    private static final ConnectionFactory connectionFactory = new ConnectionFactory();

    /**
     * Returns a new database connection.
     *
     * @return a new Connection object
     */
    public static Connection getConnection(){
        return connectionFactory.createConnection();
    }

    /**
     * Closes the given database connection.
     *
     * @param connection the Connection object to close
     * @throws RuntimeException if an error occurs while closing the connection
     */
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

    /**
     * Closes the given Statement object.
     *
     * @param statement the Statement object to close
     * @throws RuntimeException if an error occurs while closing the statement
     */
    public static void close(Statement statement) throws  RuntimeException{
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException("Error closing statement", e);
            }
        }
    }

    /**
     * Closes the given ResultSet object.
     *
     * @param resultSet the ResultSet object to close
     * @throws RuntimeException if an error occurs while closing the result set
     */
    public static void close(ResultSet resultSet) throws  RuntimeException{
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException("Error closing result set", e);
            }
        }
    }



    /**
     * Private constructor to prevent instantiation.
     */
    private ConnectionFactory(){
        try{
            Class.forName(DRIVER);
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Creates a new database connection.
     *
     * @return a new Connection object
     */
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






