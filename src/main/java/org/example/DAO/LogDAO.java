package org.example.DAO;

import org.example.Connection.ConnectionFactory;
import org.example.Model.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * This class provides the DAO functionality for the Log class.
 */
public class LogDAO extends AbstractDAO<Log>{

    /**
     * Constructs a new LogDAO.
     */
    public LogDAO() {
        super();
    }

    @Override
    public List<Log> findAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("SELECT * FROM log;");
            resultSet = statement.executeQuery();
            return createRecords(resultSet);
        }
        catch(SQLException e){
            LOGGER.severe(e.toString());
        }
        finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public List<Log> createRecords(ResultSet resultSet) {
        List<Log> records = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String bill = resultSet.getString("bill");
                Log record = new Log(id,bill);
                records.add(record);
            }
        } catch (SQLException e) {
            LOGGER.severe("Failed to create records from ResultSet: " + e.getMessage());
        }
        return records;
    }

}
