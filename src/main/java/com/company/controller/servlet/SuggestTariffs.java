package com.company.controller.servlet;

import com.company.model.dao.TariffDAO;
import com.company.model.entity.tariff.Tariff;
import com.company.model.entity.user.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.company.controller.servlet.Constants.*;

public class SuggestTariffs extends HttpServlet {

    private int currentPage = 1;
    private final TariffDAO tariffDAO = new TariffDAO();
    private String showOnlyService = "All";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = null;
        if (req.getSession(false) != null) {
            user = (User) req.getSession(false).getAttribute(USER);
            req.setAttribute(USER, user);
        }

        String service = req.getParameter("service");
        List<Tariff> tariffSuggests = tariffDAO.selectTariffs(service);
        System.out.println("Service = " + service);

        req.setAttribute("tariffSuggests",tariffSuggests);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/internet_provider/suggestTariffs.jsp");
        requestDispatcher.forward(req,resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        session.setAttribute(TARIFF,req.getParameter("tariff_id"));
        resp.sendRedirect("/internet_provider/placeChoose");
    }
    private String check(String parameter){
        if(parameter.equals("")){
            return "0";
        }
        return parameter;
    }
}
