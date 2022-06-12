package com.company.controller.servlet;

import com.company.model.dao.SelectionDAO;
import com.company.model.dao.TariffDAO;
import com.company.model.dao.WalletDAO;
import com.company.model.entity.tariff.Tariff;
import com.company.model.entity.user.User;
import com.company.model.entity.wallet.Wallet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

import static com.company.controller.servlet.Constants.*;

public class Render extends HttpServlet {

    private final WalletDAO walletDAO = new WalletDAO();
    private final TariffDAO tariffDAO = new TariffDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute(USER);
        Wallet wallet = user.getWallet();
        int tariffId = Integer.parseInt(session.getAttribute(TARIFF).toString());
        Tariff tariff = tariffDAO.selectTariff(tariffId);
        BigDecimal funds = wallet.getFunds();
        req.setAttribute(FUNDS, funds);
        req.setAttribute(TARIFF, tariff);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/internet_provider/render.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession(false) != null) {
            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute(USER);
            Wallet wallet = user.getWallet();
            int userId = ((User) req.getSession(false).getAttribute(USER)).getId();
            int tariffId = Integer.parseInt(req.getSession(false).getAttribute(TARIFF).toString());
            Tariff tariff = tariffDAO.selectTariff(tariffId);
            if ("Confirm".equals(req.getParameter("button"))) {
                BigDecimal funds = wallet.getFunds();
                BigDecimal price = tariff.getPrice();
                wallet.setFunds(funds.subtract(price));
                req.setAttribute(USER, user);
                req.setAttribute(TARIFF, tariff);
                walletDAO.addChange(wallet);
                req.setAttribute(FUNDS, wallet.getFunds());
                req.setAttribute(TARIFF, tariff.getPrice());
                resp.sendRedirect("/internet_provider/home");
                SelectionDAO dao = new SelectionDAO();
                dao.insertSelection(userId, tariffId);
            }
            if("Cancel".equals(req.getParameter("button"))){
                resp.sendRedirect("/internet_provider/home");
            }

        }
    }
}
