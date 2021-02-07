package org.mypetstore.web.servlets;

import org.mypetstore.domain.Account;
import org.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UsernameExistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountService accountService = new AccountService();
        response.setContentType("text/plain");
        PrintWriter out=response.getWriter();
        if(accountService.isAccount(request.getParameter("username"))){
            out.print("0");
        }
        else out.print("1");
        out.flush();
        out.close();
    }
}
