package com.company.model.dao;

import com.company.model.MySQLConnector;
import com.company.model.entity.Selection;
import com.company.model.entity.service.Service;
import com.company.model.entity.tariff.Tariff;
import com.company.model.entity.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static com.company.model.dao.SQL.*;

public class SelectionDAO {
    public void insertSelection(int userId,int tariffId){
        PreparedStatement pStatement = null;
        try (Connection connection = MySQLConnector.getConnection() ){
            pStatement = connection.prepareStatement(INSERT_SELECTION);
            pStatement.setInt(1,userId);
            pStatement.setInt(2,tariffId);
            pStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            close(pStatement);
        }
    }

    public List<Selection> showSelection(int userId){
        List<Selection> selectionList = new LinkedList<>();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = MySQLConnector.getConnection() ){
            pStatement = connection.prepareStatement(SHOW_SELECTION);
            pStatement.setInt(userId,1);
            resultSet = pStatement.executeQuery();////////////////////////////////////
            while (resultSet.next()){
                Selection selection = new Selection();
                mapSelection(selection,resultSet);
                selectionList.add(selection);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            close(pStatement);
            close(resultSet);
        }
        return selectionList;
    }



    public void mapSelection(Selection selection,ResultSet resultSet) throws SQLException {
        selection.setUserId((resultSet.getInt("account_id")));
        selection.setTariffId(resultSet.getInt("tariff_id"));
    }

    public void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
