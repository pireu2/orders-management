package org.example.Data;

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

public class AbstractDAO <T>{
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


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

        for(Field field : object.getClass().getFields()){
            if (field.getName().equals("id")) {
                continue;
            }
            field.setAccessible(true);
            fields.append(field.getName()).append(",");
            values.append("?,");
        }
        fields.setLength(fields.length() - 1);
        values.setLength(values.length() - 1);

        return "INSERT INTO " + object.getClass().getSimpleName() + "(" + fields + ") VALUES (" + values + ")";
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
                    Object value = resultSet.getObject(fieldName);
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


