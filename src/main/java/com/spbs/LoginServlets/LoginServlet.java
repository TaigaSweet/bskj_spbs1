package com.spbs.LoginServlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        String userName = request.getParameter("userName");
        String userPsw = request.getParameter("userPsw");//userNames
        String userNames = request.getParameter("userNames");//userNames
        System.out.println(userNames);
        System.out.println(userName + "  " + userPsw);
        if ("ws".equalsIgnoreCase(userName) && "123".equalsIgnoreCase(userPsw)) {
            request.setAttribute("userName", userName);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            request.setAttribute("userName", userName);
            response.sendRedirect("index.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}