package com.company.controller.filter;

import com.company.model.entity.user.Role;
import com.company.model.entity.user.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) req).getSession(false);
        if(!"active".equals(req.getServletContext().getAttribute("active"))){
            ((HttpServletResponse) resp).sendRedirect("internet_provider/");
            return;
        }
        if(session != null){
            User user = (User) session.getAttribute("user");
            if(user != null) {
                if (!Role.ADMIN.equals(user.getRole())) {
                    ((HttpServletResponse) resp).sendRedirect("internet_provider/");
                    return;
                }
            }
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}
