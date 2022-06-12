package com.company.controller.servlet;

import com.company.model.dao.TariffDAO;
import com.company.model.dao.UserSuggestionDAO;
import com.company.model.entity.suggestion.UserSuggestion;
import com.company.model.entity.tariff.Tariff;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SuggestionExamples extends HttpServlet {
    private String showOnlyService = "All";
    private final UserSuggestionDAO userSuggestionDAO = new UserSuggestionDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserSuggestion> userSuggestions;

        if (showOnlyService.equals("All")) {
            userSuggestions = userSuggestionDAO.selectSuggestions();
        } else {
            userSuggestions = userSuggestionDAO.selectSuggestions(showOnlyService);
        }
        List<Tariff> emptyFilms = new ArrayList<>();
        req.setAttribute("userSuggestions", userSuggestions);
        req.getAttribute(userSuggestions.toString());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/internet_provider/suggestionExamples.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
