package com.company.model.dao;

import com.company.model.MySQLConnector;
import com.company.model.entity.service.Service;
import com.company.model.entity.suggestion.UserSuggestion;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static com.company.model.dao.SQL.*;

public class UserSuggestionDAO {
    public void insertSuggestion(UserSuggestion userSuggestion){
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
            connection = MySQLConnector.getConnection();
            connection.setAutoCommit(false);
            pStatement = connection.prepareStatement(INSERT_USER_SUGGESTION, Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1,userSuggestion.getName_en());
            pStatement.setInt(2,userSuggestion.getTime());
            pStatement.setString(3,userSuggestion.getDescription());
            pStatement.setBigDecimal(4,userSuggestion.getPrice());
            pStatement.setInt(5,userSuggestion.getService().getId());
            pStatement.executeUpdate();
            resultSet = pStatement.getGeneratedKeys();
            if (resultSet != null && resultSet.next()){
                userSuggestion.setId(resultSet.getInt(1));
            }
            connection.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
            if (connection != null){
                try {
                    connection.rollback();
                }catch (SQLException ee){
                    ee.printStackTrace();
                }
            }
        }
        finally {
            close(pStatement);
            close(resultSet);
        }
    }

    public UserSuggestion selectSuggestion(int id){
        UserSuggestion userSuggestion = new UserSuggestion();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = MySQLConnector.getConnection() ){
            pStatement = connection.prepareStatement(SELECT_USER_SUGGESTION_BY_ID);
            pStatement.setInt(1,id);
            resultSet = pStatement.executeQuery();
            if (resultSet.next()){
                mapSuggestion(userSuggestion,resultSet);
            }
            return userSuggestion;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            close(pStatement);
            close(resultSet);
        }
        return null;
    }

    public void mapSuggestion(UserSuggestion userSuggestion, ResultSet resultSet) throws SQLException {
        userSuggestion.setId(resultSet.getInt("id"));
        userSuggestion.setName_en(resultSet.getString("name_en"));
        userSuggestion.setTime(resultSet.getInt("time"));
        userSuggestion.setDescription(resultSet.getString("description"));
        userSuggestion.setPrice(resultSet.getBigDecimal("price"));
        int serviceId = resultSet.getInt("service_id");
        Service service = new ServiceDAO().selectService(serviceId);
        userSuggestion.setService(service);
    }

    public List<UserSuggestion> selectSuggestions(){
        List<UserSuggestion> suggestionList = new LinkedList<>();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = MySQLConnector.getConnection() ){
            pStatement = connection.prepareStatement(SELECT_USER_SUGGESTIONS);
            resultSet = pStatement.executeQuery();
            while (resultSet.next()){
                UserSuggestion userSuggestion = new UserSuggestion();
                mapSuggestion(userSuggestion,resultSet);
                suggestionList.add(userSuggestion);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            close(pStatement);
            close(resultSet);
        }
        return suggestionList;
    }

    //////////////////////////////////////////////////////////////////////////

    public List<UserSuggestion> selectSuggestions(String service) {
        List<UserSuggestion> suggestionList = new LinkedList<>();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = MySQLConnector.getConnection() ){
            pStatement = connection.prepareStatement(SELECT_TARIFF_BY_SERVICE);
            pStatement.setString(1, service);
            resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                UserSuggestion userSuggestion = new UserSuggestion();
                mapSuggestion(userSuggestion, resultSet);
                suggestionList.add(userSuggestion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(pStatement);
        }

        return suggestionList;
    }

    public void close(AutoCloseable closeable){
        if(closeable != null){
            try {
                closeable.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
