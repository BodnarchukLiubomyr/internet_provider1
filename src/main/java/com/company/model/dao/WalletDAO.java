package com.company.model.dao;

import com.company.model.MySQLConnector;
import com.company.model.entity.tariff.Tariff;
import com.company.model.entity.user.User;
import com.company.model.entity.wallet.Wallet;

import java.math.BigDecimal;
import java.sql.*;

import static com.company.model.dao.SQL.*;

public class WalletDAO {

    public void close(AutoCloseable closeable){
        if(closeable != null){
            try {
                closeable.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void addChange(Wallet wallet){
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
            connection = MySQLConnector.getConnection();
            connection.setAutoCommit(false);
            pStatement = connection.prepareStatement(ADD_CHANGE);
            pStatement.setBigDecimal(1, wallet.getFunds());
            pStatement.setInt(2, wallet.getId());
            pStatement.executeUpdate();
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
            close(connection);
            close(pStatement);
            close(resultSet);
        }
    }

//    public void Render(Wallet wallet, Tariff tariff){
//        Connection connection = null;
//        PreparedStatement pStatement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = MySQLConnector.getConnection();
//            connection.setAutoCommit(false);
//            pStatement = connection.prepareStatement(TARIFF_PAYMENT);
//            pStatement.setBigDecimal(1, wallet.getFunds());
//            pStatement.setInt(2, wallet.getId());
////            pStatement.setInt(3,tariff.getId());
////            pStatement.setBigDecimal(2,tariff.getPrice());
//            pStatement.executeUpdate();
//            connection.commit();
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            if (connection != null){
//                try {
//                    connection.rollback();
//                }catch (SQLException ee){
//                    ee.printStackTrace();
//                }
//            }
//        }
//        finally {
//            close(connection);
//            close(pStatement);
//            close(resultSet);
//        }
//    }
}
