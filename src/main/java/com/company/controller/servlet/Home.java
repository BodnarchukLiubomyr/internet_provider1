package com.company.controller.servlet;

import com.company.model.dao.ServiceDAO;
import com.company.model.dao.TariffDAO;
import com.company.model.entity.tariff.Tariff;
import com.company.model.entity.user.Role;
import com.company.model.entity.user.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.company.controller.servlet.Constants.*;

public class Home extends HttpServlet {

    private int currentPage = 1;
    private final TariffDAO tariffDAO = new TariffDAO();
    private final ServiceDAO serviceDAO = new ServiceDAO();
    private String sortedTariff  = "alphabet";
    private String showOnlyService = "All";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if ("logout".equals(req.getParameter(NAME))) {
            String active = null;
            if (req.getSession(false) != null) {
                req.getSession(false).invalidate();
            }
            currentPage = 1;
            req.getServletContext().setAttribute("active", active);
        }

        User user = null;
        if (req.getSession(false) != null) {
            user = (User) req.getSession(false).getAttribute(USER);
            req.setAttribute(USER, user);
        }

        String link = "tariffSelect";
        if(req.getSession(false)!=null){
            user = (User) req.getSession(false).getAttribute(USER);
            req.setAttribute(USER,user);
            if(user != null){
                if(Role.ADMIN.equals(user.getRole())){
                    link = "editTariff";
                }
            }
        }

        List<Tariff> tariffs;

//        if (showOnlyService.equals("All")) {
//            tariffs = TariffDAO.selectTariffs();
//        } else {
//            tariffs = TariffDAO.selectTariffs(showOnlyService);
//        }
        List<Tariff> emptyFilms = new ArrayList<>();

//        tariffDAO.getTariff();

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/internet_provider/home.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
