package com.company.controller.servlet;

import com.company.model.dao.ServiceDAO;
import com.company.model.dao.UserSuggestionDAO;
import com.company.model.entity.service.Service;
import com.company.model.entity.suggestion.UserSuggestion;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

import static com.company.controller.servlet.Constants.SERVICES;

public class AddUserSuggestion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(SERVICES,ServletUtil.selectServices());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/internet_provider/userSuggestion.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceDAO serviceDAO = new ServiceDAO();
        UserSuggestion userSuggestion = new UserSuggestion();
        userSuggestion.setName_en(req.getParameter("name_en"));
        userSuggestion.setDescription(req.getParameter("description"));
        userSuggestion.setPrice(new BigDecimal(req.getParameter("price")));
        userSuggestion.setTime(Integer.parseInt(req.getParameter("time")));
        Service service = serviceDAO.selectService(Integer.parseInt(req.getParameter("service")));
        userSuggestion.setService(service);
        new UserSuggestionDAO().insertSuggestion(userSuggestion);
        resp.sendRedirect("/internet_provider/home");
    }
}
