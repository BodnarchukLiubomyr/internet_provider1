package com.company.controller.servlet;

import com.company.model.dao.UserDAO;
import com.company.model.entity.place.Place;
import com.company.model.entity.user.User;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.company.controller.servlet.Constants.*;

public class PlaceChoose extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/internet_provider/placeChoose.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        Place place = new Place();
        place.setStreet(req.getParameter("street"));
        place.setDistrict(req.getParameter("district"));
        place.setCity(req.getParameter("city"));
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute(USER);
        userDAO.updatePlace(user.getPlace().getId(),place);
        resp.sendRedirect("/internet_provider/render");
    }

}
