package com.company.controller.servlet;

import com.company.model.dao.ServiceDAO;
import com.company.model.dao.TariffDAO;
import com.company.model.dao.UserDAO;
import com.company.model.entity.tariff.Tariff;
import com.company.model.entity.user.Role;
import com.company.model.entity.user.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.company.controller.servlet.Constants.*;

public class Home extends HttpServlet {

    private int currentPage = 1;
    private final TariffDAO tariffDAO = new TariffDAO();
    private final ServiceDAO serviceDAO = new ServiceDAO();
    private String sortOrder = "ascending";
    private String sortBy = "Name / Price";

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

        List<Tariff> tariffs;
        tariffs = tariffDAO.selectTariffs();

        String temp = req.getParameter("sortBy");
        if (temp != null) {
            if (temp.equals(sortBy)) {
                sortOrder = changeSortOrder(sortOrder);
            } else {
                sortOrder = ASC;
                sortBy = temp;
            }
        }
        sorting(tariffs);
        req.setAttribute("tariffs", tariffs);

        req.setAttribute("sortBy", sortBy);
        req.setAttribute("sortOrder", sortOrder);

        Tariff tariff = new Tariff();
        int tariffId = tariff.getId();
        tariffDAO.selectTariff(tariffId);

//        tariff = tariffDAO.selectTariffs();
        List<Tariff> tariffSuggests;
        String services = tariff.getService().getService_en();
        tariffSuggests = tariffDAO.selectTariffs(services);
        req.setAttribute("tariffSuggests",tariffSuggests);

//        req.setAttribute("services", ServletUtil.getServices());

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/internet_provider/home.jsp");
        requestDispatcher.forward(req,resp);
    }

//    public static void main(String[] args) {
//        System.out.println(ServletUtil.getServices());
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        session.setAttribute(TARIFF,req.getParameter("tariff_id"));

        if ("Delete".equals(req.getParameter("button"))) {
            Tariff tariff = new Tariff();
            int tariffId = tariff.getId();
            tariffDAO.deleteTariff(tariffId);
            resp.sendRedirect("/internet_provider/home");
        }

        resp.sendRedirect("/internet_provider/placeChoose");
    }

    private String changeSortOrder(String sortOrder) {
        if (sortOrder.equals(ASC)) {
            sortOrder = DESC;
        } else {
            sortOrder = ASC;
        }
        return sortOrder;
    }

    private void sorting(List<Tariff> tariffs) {
        List<Tariff> temp = new ArrayList<>(tariffs);
        switch (sortBy) {
            case "Name": {
                if (sortOrder.equals(ASC)) {
                    temp= tariffs.stream().sorted().collect(Collectors.toList());
                } else {
                    temp = tariffs.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
                }
                break;
            }

            case "Price": {
                if (sortOrder.equals(ASC)) {
                    temp = tariffs.stream().sorted(Comparator.comparing(Tariff::getPrice)).collect(Collectors.toList());
                } else {
                    temp = tariffs.stream().sorted(Comparator.comparing(Tariff::getPrice).reversed()).collect(Collectors.toList());
                }
                break;
            }
        }
        tariffs.clear();
        tariffs.addAll(temp);
    }

//    public static void main(String[] args) {
//        List<Tariff> tariffs = new ArrayList<>();
//        tariffs.add(new Tariff(1));
//        tariffs.add(new Tariff(3));
//        tariffs.add(new Tariff(2));
//        Home home = new Home();
//        home.sortBy = "Price";
//        home.sortOrder = "ascending";
//        home.sorting(tariffs);
//        tariffs.forEach(a -> System.out.println(a.getPrice()));
//        home.sortBy = "Price";
//        home.sortOrder = "descending";
//        home.sorting(tariffs);
//        tariffs.forEach(a -> System.out.println(a.getPrice()));
//    }
}
