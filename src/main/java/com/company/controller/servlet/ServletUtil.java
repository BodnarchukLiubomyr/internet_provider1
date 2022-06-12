package com.company.controller.servlet;

import com.company.model.dao.ServiceDAO;
import com.company.model.dao.TariffDAO;
import com.company.model.dao.TariffStatusDAO;
import com.company.model.dao.UserSuggestionDAO;
import com.company.model.entity.place.Place;
import com.company.model.entity.service.Service;
import com.company.model.entity.tariff.Tariff;
import com.company.model.entity.tariff.TariffStatus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ServletUtil {
    public static final ServiceDAO SERVICE_DAO = new ServiceDAO();
    public static final TariffDAO TARIFF_DAO = new TariffDAO();
    public static final UserSuggestionDAO USER_SUGGESTION_DAO = new UserSuggestionDAO();
    public static final TariffStatusDAO TARIFF_STATUS_DAO = new TariffStatusDAO();

    public static Tariff getTariff(int tariffId){
        return TARIFF_DAO.selectTariff(tariffId);
    }

    public static List<Service> selectServices(){
        return SERVICE_DAO.selectServices();
    }

    public static List<TariffStatus> selectStatuses(){return TARIFF_STATUS_DAO.selectStatuses(); }

//    public static List<String> getServices() {
//        // select service_en from service; --> ServiceDAO
//        return SERVICE_DAO.getServices();
//    }
}
