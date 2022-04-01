package com.company.model.dao;

import com.company.model.MySQLConnector;
import com.company.model.entity.user.User;
import com.company.model.entity.wallet.Wallet;

import java.math.BigDecimal;
import java.sql.*;

import static com.company.model.dao.SQL.*;

public class WalletDAO {
//    public void insertWallet(Wallet wallet,int userId){
//        Connection connection = null;
//        PreparedStatement pStatement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = MySQLConnector.getConnection();
//            connection.setAutoCommit(false);
//            pStatement = connection.prepareStatement(INSERT_WALLET, Statement.RETURN_GENERATED_KEYS);
//            pStatement.setBigDecimal(1,wallet.getFunds());
//            pStatement.setInt(2, userId); // user.getId();
//            pStatement.setBigDecimal(3,wallet.getChange());
//            pStatement.executeUpdate();
//            resultSet = pStatement.getGeneratedKeys();
//            if (resultSet != null && resultSet.next()){
//                wallet.setId(resultSet.getInt(1));
//            }
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

//    public Wallet getWallet(int userId) {
//        System.out.println("getWallet with userId: " + userId); // user.getId();
//        Wallet wallet = new Wallet();
//        PreparedStatement pStatement = null;
//        ResultSet resultSet = null;
//        try (Connection connection = MySQLConnector.getConnection() ){
//            pStatement = connection.prepareStatement(SELECT_WALLET);
//            pStatement.setInt(1, userId); // user.getId();
//            resultSet = pStatement.executeQuery();
//            if (resultSet.next()) {
//                mapWallet(wallet, resultSet);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            close(resultSet);
//            close(pStatement);
//        }
//        return wallet;
//    }

//    private void mapWallet(Wallet wallet, ResultSet resultSet) throws SQLException {
//        System.out.println("Map wallet");
//        BigDecimal funds = resultSet.getBigDecimal("funds");
//        wallet.setId(resultSet.getInt("id"));
//        wallet.setFunds(funds == null ? new BigDecimal(0) : funds);
//        System.out.println("DAO funds " + wallet.getFunds());
//        wallet.setChange(resultSet.getBigDecimal("change"));
//        wallet.setUserId(resultSet.getInt("account_id"));
//    }

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
}
