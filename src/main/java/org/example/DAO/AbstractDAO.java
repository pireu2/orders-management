package org.example.DAO;

import org.example.Connection.ConnectionFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/**
 * This class provides the base DAO functionality for all DAO classes.
 *
 * @param <T> the type parameter associated with the DAO.
 */
public class AbstractDAO <T>{
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    /**
     * Constructs a new AbstractDAO.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    /**
     * Retrieves all records from the table associated with the DAO's type parameter.
     *
     * @return a list of records from the table associated with the DAO's type parameter.
     */
    public List<T> findAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(createSelectAllQuery());
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
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

    /**
     * Retrieves a record by its name from the table associated with the DAO's type parameter.
     *
     * @param name the name of the record to retrieve.
     * @return the record with the specified name.
     */
    public T findByName(String name){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(createSelectQuery("name"));
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            List<T> list = createObjects(resultSet);
            if(list.isEmpty()){
                return null;
            }
            return list.get(0);
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

    /**
     * Retrieves the last record inserted into the table associated with the DAO's type parameter.
     *
     * @return the last record inserted into the table.
     */
    public T findLast(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement("SELECT * FROM " + type.getSimpleName() + " ORDER BY id DESC LIMIT 1");
            resultSet = statement.executeQuery();
            List<T> list = createObjects(resultSet);
            if(list.isEmpty()){
                return null;
            }
            return list.get(0);
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

    /**
     * Retrieves a record by its ID from the table associated with the DAO's type parameter.
     *
     * @param id the ID of the record to retrieve.
     * @return the record with the specified ID.
     */
    public T findById(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(createSelectQuery("id"));
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            List<T> list = createObjects(resultSet);
            if(list.isEmpty()){
                return null;
            }
            return list.get(0);
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

    /**
     * Inserts a new record into the table associated with the DAO's type parameter.
     *
     * @param object the object to insert into the table.
     */
    public void insert(T object){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(createInsertStatement(object));
            Field[] fields = object.getClass().getDeclaredFields();
            int index = 1;
            for (Field field : fields) {
                if(field.getName().equals("id"))
                    continue;
                field.setAccessible(true);
                Object value = field.get(object);
                if (value instanceof Integer) {
                    statement.setInt(index, (Integer) value);
                } else if (value instanceof String) {
                    statement.setString(index, (String) value);
                } else if (value instanceof Double) {
                    statement.setDouble(index, (Double) value);
                }
                index++;
            }
            statement.executeUpdate();
        }catch(SQLException | IllegalAccessException e){
            LOGGER.severe(e.toString());
        }
        finally{
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }


    /**
     * Updates a record in the table associated with the DAO's type parameter.
     *
     * @param object the object to update in the table.
     * @param id the ID of the record to update.
     */
    public void update(T object, int id){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(createUpdateStatement(object));
            Field[] fields = object.getClass().getDeclaredFields();
            int index = 1;
            for (Field field : fields) {
                if(field.getName().equals("id"))
                    continue;
                field.setAccessible(true);
                Object value = field.get(object);
                if (value instanceof Integer) {
                    statement.setInt(index, (Integer) value);
                } else if (value instanceof String) {
                    statement.setString(index, (String) value);
                } else if (value instanceof Double) {
                    statement.setDouble(index, (Double) value);
                }
                index++;
            }
            statement.setInt(index, id);
            statement.executeUpdate();
        }catch(SQLException | IllegalAccessException e){
            LOGGER.severe(e.toString());
        }
        finally{
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Deletes a record from the table associated with the DAO's type parameter.
     *
     * @param id the ID of the record to delete.
     */
    public void delete(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(createDeleteQuery());
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch(SQLException e){
            LOGGER.severe(e.toString());
        }
        finally{
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    private String createDeleteQuery(){
        return "DELETE FROM " + type.getSimpleName() + " WHERE id = ?";
    }

    private String createSelectQuery(String field){
        return "SELECT * FROM " + type.getSimpleName() + " WHERE " + field + " = ?";
    }

    private String createSelectAllQuery(){
        return "SELECT * FROM " + type.getSimpleName();
    }

    private String createInsertStatement(T object){
        StringBuilder fields = new StringBuilder();
        StringBuilder values = new StringBuilder();


        for(Field field : object.getClass().getDeclaredFields()){
            if (field.getName().equals("id")) {
                continue;
            }
            field.setAccessible(true);
            fields.append(field.getName()).append(",");
            values.append("?,");
        }
        if (!fields.isEmpty()) {
            fields.setLength(fields.length() - 1);
        }
        if (!values.isEmpty()) {
            values.setLength(values.length() - 1);
        }

        return "INSERT INTO " + object.getClass().getSimpleName().toLowerCase() + " (" + fields + ") VALUES (" + values + ")";
    }

    private String createUpdateStatement(T object) {
        StringBuilder fields = new StringBuilder();
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.getName().equals("id")) {
                continue;
            }
            field.setAccessible(true);
            fields.append(field.getName()).append(" = ?,");
        }
        if (!fields.isEmpty()) {
            fields.setLength(fields.length() - 1);
        }
        return "UPDATE " + object.getClass().getSimpleName().toLowerCase() + " SET " + fields + " WHERE id = ?";
    }


    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] constructors = type.getConstructors();
        Constructor ctor = null;
        for (Constructor constructor : constructors) {
            ctor = constructor;
            if (ctor.getGenericParameterTypes().length == 0) {
                break;
            }
        }
        try{
            while(resultSet.next()){
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for(Field field : type.getDeclaredFields()){
                    String fieldName = field.getName();
                    Object value;
                    if (field.getType() == int.class) {
                        value = resultSet.getInt(fieldName);
                    } else if (field.getType() == String.class) {
                        value = resultSet.getString(fieldName);
                    } else if (field.getType() == double.class) {
                        value = resultSet.getDouble(fieldName);
                    } else {
                        value = resultSet.getObject(fieldName);
                    }
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        }
        catch (Exception e) {
            LOGGER.severe(e.toString());
        }
        return list;
    }
}


