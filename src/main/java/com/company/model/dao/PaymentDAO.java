package com.company.model.dao;

import com.company.model.MySQLConnector;
import com.company.model.entity.payment.Payment;
import com.company.model.entity.service.Service;
import com.company.model.entity.tariff.Tariff;
import com.company.model.entity.tariff.TariffStatus;
import com.company.model.entity.user.User;
import com.company.model.entity.wallet.Wallet;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static com.company.model.dao.SQL.*;

public class PaymentDAO {
    public void insertPayment(Payment payment){
//        Connection connection = null;
//        PreparedStatement pStatement = null;
//        try {
//            connection = MySQLConnector.getConnection();
//            pStatement = connection.prepareStatement(INSERT_PAYMENT);
//            pStatement.setInt(1,payment.getUserId());
//            pStatement.setInt(2,payment.getTariffId());
//            pStatement.setBigDecimal(3,payment.getPrice());////////////////////////////////////////////////
//            pStatement.setInt(4,payment.getTime());////////////////////////////////////////////////////////
//            pStatement.executeUpdate();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        finally {
//            close(connection);
//            close(pStatement);
//        }
    }
    public List<Payment> getUserPayments(int userId, int offset, int limit) {
        List<Payment> paymentList = new LinkedList<>();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = MySQLConnector.getConnection() ){
            pStatement = connection.prepareStatement(SELECT_USER_PAYMENTS_LIMIT);
            pStatement.setInt(1, userId);///
            pStatement.setInt(2, offset);///
            pStatement.setInt(3, limit);///
            resultSet = pStatement.executeQuery();
            while (resultSet.next()){
                Payment payment = new Payment();
                payment.setUserId(resultSet.getInt("account_id"));
                payment.setTariffId(resultSet.getInt("tariff_id"));
                payment.setPrice(resultSet.getBigDecimal("price"));
                payment.setTime(resultSet.getInt("time"));///////////////////////////////////////////////////
                mapPayment(payment);
                paymentList.add(payment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(pStatement);
            close(resultSet);
        }
        return paymentList;
    }

    public int amountTariffs(int userId) {
        int amount = 0;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = MySQLConnector.getConnection() ){
            pStatement = connection.prepareStatement(SELECT_AMOUNT_PAYMENTS);//////////////////////////////
            pStatement.setInt(1, userId);
            resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(pStatement);
        }
        return amount;
    }

    private void mapPayment(Payment payment) throws SQLException {
        Tariff tariff = new TariffDAO().selectTariff(payment.getTariffId());
        payment.setTariffName(tariff.getName_en());
        payment.setDescription(tariff.getDescription());
        payment.setService(tariff.getService().getService_en());
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
