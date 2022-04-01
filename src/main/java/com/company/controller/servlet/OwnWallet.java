package com.company.controller.servlet;

import com.company.model.dao.WalletDAO;
import com.company.model.entity.user.User;
import com.company.model.entity.wallet.Wallet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

import static com.company.controller.servlet.Constants.*;

public class OwnWallet extends HttpServlet {

    private final WalletDAO walletDAO = new WalletDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Start GET process");
        BigDecimal funds = BigDecimal.valueOf(0);
        if (req.getSession(false) != null) {
            System.out.println("User session detected (get)");
            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute(USER);
            Wallet wallet = user.getWallet();
            Integer count = (Integer) session.getAttribute("count"); // Not sure if you need this
            funds = wallet.getFunds();
            System.out.println("Wallet funds (get): " + funds);
            if (count == null) {
                session.setAttribute("count",1);  // Not sure if you need this
            }
        }
        req.setAttribute(FUNDS, funds);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/internet_provider/ownWallet.jsp");
        System.out.println("End GET process");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("Start POST process");
        if (req.getSession(false) != null) {
            System.out.println("User session detected (post)");
            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute(USER);
            Wallet wallet = user.getWallet();
            Integer count = (Integer) session.getAttribute("count"); // Not sure if you need this
            if (count == null) {
                session.setAttribute("count",1);  // Not sure if you need this
            }
            BigDecimal funds = wallet.getFunds();
            System.out.println("Wallet funds (post before adding): " + funds);
            BigDecimal change = new BigDecimal(req.getParameter("change"));
            wallet.setFunds(funds.add(change));
            System.out.println("Wallet funds (post after adding): " + wallet.getFunds());
            req.setAttribute(USER, user);
            walletDAO.addChange(wallet);
            req.setAttribute(FUNDS, wallet.getFunds());
            System.out.println("End POST process");
            resp.sendRedirect("/internet_provider/ownWallet");
        }
    }
}
