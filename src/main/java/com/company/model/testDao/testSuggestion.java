package com.company.model.testDao;

import com.company.model.dao.ServiceDAO;
import com.company.model.dao.UserSuggestionDAO;
import com.company.model.entity.suggestion.UserSuggestion;

import java.math.BigDecimal;
import java.sql.SQLException;

public class testSuggestion {
    public static void main(String[] args) throws SQLException {
        UserSuggestion userSuggestion = new UserSuggestion();
        userSuggestion.setId(1);
        userSuggestion.setName_en("Enterprise");
        userSuggestion.setTime(30);
        userSuggestion.setDescription("Unchangeble stream of internet");
        userSuggestion.setPrice(new BigDecimal(30));
        ServiceDAO dao3 = new ServiceDAO();
        userSuggestion.setService(dao3.selectService(1));
        UserSuggestionDAO userSuggestionDAO = new UserSuggestionDAO();
        userSuggestionDAO.insertSuggestion(userSuggestion);
    }
}
