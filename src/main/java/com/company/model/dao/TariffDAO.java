package com.company.model.dao;

import com.company.model.MySQLConnector;
import com.company.model.entity.service.Service;
import com.company.model.entity.tariff.Tariff;
import com.company.model.entity.tariff.TariffStatus;

import java.sql.*;
import java.util.*;

import static com.company.model.dao.SQL.*;

public class TariffDAO {
    public void insertTariff(Tariff tariff){
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
            connection = MySQLConnector.getConnection();
            connection.setAutoCommit(false);
            pStatement = connection.prepareStatement(INSERT_TARIFF, Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1,tariff.getName_en());
            pStatement.setInt(2,tariff.getTime());
            pStatement.setString(3,tariff.getDescription());
            pStatement.setBigDecimal(4,tariff.getPrice());
            pStatement.setInt(5,tariff.getService().getId());
            pStatement.setInt(6,tariff.getStatus().getId());
            pStatement.executeUpdate();
            resultSet = pStatement.getGeneratedKeys();
            if (resultSet != null && resultSet.next()){
                tariff.setId(resultSet.getInt(1));
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
    public Tariff selectTariff(int id) throws SQLException{
        Tariff tariff = new Tariff();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = MySQLConnector.getConnection() ){
            pStatement = connection.prepareStatement(SELECT_TARIFF_BY_ID);
            pStatement.setInt(1,id);
            resultSet = pStatement.executeQuery();
            if (resultSet.next()){
                mapTariff(tariff,resultSet);
            }
            return tariff;
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

    public void mapTariff(Tariff tariff, ResultSet resultSet) throws SQLException {
        tariff.setId(resultSet.getInt("id"));
        tariff.setName_en(resultSet.getString("name_en"));
        tariff.setTime(resultSet.getInt("time"));
        tariff.setDescription(resultSet.getString("description"));
        tariff.setPrice(resultSet.getBigDecimal("price"));
        int serviceId = resultSet.getInt("service_id");
        Service service = new ServiceDAO().selectService(serviceId);
        tariff.setService(service);
        int tariffStatusId = resultSet.getInt("tariff_status_id");
        TariffStatus status = new TariffStatusDAO().selectStatus(tariffStatusId);
        tariff.setStatus(status);
    }

    public List<Tariff> selectTariffs(){
        List<Tariff> tariffList = new LinkedList<>();

        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = MySQLConnector.getConnection() ){
            pStatement = connection.prepareStatement(SELECT_TARIFFS);
            resultSet = pStatement.executeQuery();
            while (resultSet.next()){
                Tariff tariff = new Tariff();
                mapTariff(tariff,resultSet);
                tariffList.add(tariff);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            close(pStatement);
            close(resultSet);
        }
        return tariffList;
    }

    //////////////////////////////////////////////////////////////////////////

    public List<Tariff> selectTariffs(String service) {
        List<Tariff> tariffList = new LinkedList<>();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = MySQLConnector.getConnection() ){
            pStatement = connection.prepareStatement(SELECT_TARIFF_BY_SERVICE);
            pStatement.setString(1, service);
            resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                Tariff tariff = new Tariff();
                mapTariff(tariff, resultSet);
                tariffList.add(tariff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(pStatement);
        }

        return tariffList;
    }
    ////////////////////////////////////////////////////////////////////////////////

//    public List<Tariff> selectTariffsBysStatus(String status) {
//        List<Tariff> tariffList = new ArrayList<>();
//        PreparedStatement pStatement = null;
//        ResultSet resultSet = null;
//        try (Connection connection = MySQLConnector.getConnection() ){
//            pStatement = connection.prepareStatement(SELECT_TARIFF_BY_STATUS);
//            pStatement.setString(1, status);
//            resultSet = pStatement.executeQuery();
//            while (resultSet.next()) {
//                Tariff tariff = new Tariff();
//                mapTariff(tariff, resultSet);
//                tariffList.add(tariff);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            close(resultSet);
//            close(pStatement);
//        }
//
//        return tariffList;
//    }

    ////////////////////////////////////////////////////////////////////////////////

    public void setStatus(Tariff tariff){
        PreparedStatement pStatement = null;
        try (Connection connection = MySQLConnector.getConnection() ){
            pStatement = connection.prepareStatement(SET_TARIFF_STATUS);
            pStatement.setInt(1,tariff.getStatus().getId());
            pStatement.setInt(2,tariff.getId());
            pStatement.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            close(pStatement);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////

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
