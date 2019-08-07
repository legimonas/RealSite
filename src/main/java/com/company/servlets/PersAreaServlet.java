package com.company.servlets;

import com.company.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/persarea")
public class PersAreaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("UserStruct");
        req.setAttribute("User", user);
        req.getServletContext().getRequestDispatcher("/jsp/persarea.jsp").forward(req, resp);
    }
}
