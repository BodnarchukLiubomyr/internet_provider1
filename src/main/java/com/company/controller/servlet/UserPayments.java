package com.company.controller.servlet;

import com.company.model.dao.PaymentDAO;
import com.company.model.dao.SelectionDAO;
import com.company.model.dao.TariffDAO;
import com.company.model.dao.WalletDAO;
import com.company.model.entity.Selection;
import com.company.model.entity.payment.Payment;
import com.company.model.entity.tariff.Tariff;
import com.company.model.entity.user.User;
import com.company.model.entity.wallet.Wallet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.company.controller.servlet.Constants.*;

public class UserPayments extends HttpServlet {

    private int currentPage = 1;
    private final SelectionDAO selectionDAO = new SelectionDAO();
    private final TariffDAO tariffDAO = new TariffDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute(USER);
        int userId = ((User) req.getSession(false).getAttribute(USER)).getId();

        List<Selection> selections;
        selections = selectionDAO.showSelection(userId);/////////////////////////
        List<Tariff> tariffs = new ArrayList<>();
        for (Selection selection: selections) {
            int tariffId = selection.getTariffId();
            Tariff tariff = tariffDAO.selectTariff(tariffId);
            tariffs.add(tariff);
        }
        req.setAttribute("tariffs",tariffs);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/internet_provider/userPayments.jsp");
        dispatcher.forward(req, resp);

    }

//    private List<Payment> pagination(HttpServletRequest req, User user){
//        int paymentAmount = paymentDao.amountTariffs(user.getId());
//        int pageAmount = (int) Math.ceil((double) paymentAmount / PAYMENTS_LIMIT);
//
//        Integer[] pages = new Integer[pageAmount];
//        for (int i = 0; i < pageAmount; ++i) {
//            pages[i] = i + 1;
//        }
//
//        if (req.getParameter("page") != null) {
//            currentPage = Integer.parseInt(req.getParameter("page"));
//            if (currentPage > pageAmount || currentPage < 1) {
//                currentPage = 1;
//            }
//        }
//
//        int offset = (currentPage - 1) * PAYMENTS_LIMIT;
//        List<Payment> payments = paymentDao.getUserPayments(user.getId(), offset, PAYMENTS_LIMIT);
//
//        req.setAttribute(CURRENT_PAGE, currentPage);
//        req.setAttribute(PAGES, pages);
//        req.setAttribute(FIRST_PAGE, CONST_ONE);
//        req.setAttribute(LAST_PAGE, pageAmount);
//
//        return payments;



//        HttpSession session = req.getSession(false);
//        Map<Integer, BigDecimal> tariffs = (Map<Integer, BigDecimal>) session.getAttribute("chosenTariffs");
//        TariffDAO dao = new TariffDAO();
//        Tariff activeTariff = ((Tariff) session.getAttribute(ACTIVE_TARIFF));
//        int tariffId = activeTariff.getId();
//        if ("Cancel".equals(req.getParameter("button"))) {
//            for (int tariff : tariffs.keySet()) {
//                int shpId = shpDAO.selectSHPIdBySessionAndPlaceId(filmSessionId, place);
//                shpDAO.setAvailable(shpId, true);
//                shpDAO.setBookTimeNull(shpId);
//            }
//            if (FilmSessionStatus.NO_PLACES.equals(activeSession.getStatus())) {
//                activeSession.setStatus(FilmSessionStatus.AVAILABLE);
//                new FilmSessionDAO().setStatus(activeSession);
//            }
//            String path = "/placeSelect?name=" + activeSession.getFilm().getId() + "&id=" + filmSessionId;
//            resp.sendRedirect(path);
//        } else if ("Confirm".equals(req.getParameter("button"))) {
//            Ticket ticket;
//            for (int place : places.keySet()) {
//                int shpId = shpDAO.selectSHPIdBySessionAndPlaceId(filmSessionId, place);
//                boolean timeOut = shpDAO.isTimeOut(shpId);
//                if (timeOut) {
//                    session.setAttribute(TIME_OUT, true);
//                    resp.sendRedirect("/confirm");
//                    return;
//                }
//                ticket = new Ticket();
//                ticket.setUserId(((User) session.getAttribute(USER)).getId());
//                SessionHasPlace shp = shpDAO.getSessionHasPlace(shpId);
//                ticket.setSessionHasPlaceId(shpId);
//                BigDecimal price = activeSession.getFilm().getPrice().add(shp.getPlace().getType().getPrice());
//                ticket.setPrice(price);
//                new TicketDAO().insertTicket(ticket);
//                shpDAO.setBookTimeNull(shpId);
//            }
//            String path = "/internet_provider/payments.jsp";
//            resp.sendRedirect(path);
//        } else {
//            String path = "/errorPage.jsp";
//            resp.sendRedirect(path);
//        }
//    }
}
