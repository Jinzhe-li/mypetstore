package org.mypetstore.web.servlets;

import org.mypetstore.domain.Account;
import org.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditAccountServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(request.getParameter("res").equalsIgnoreCase(session.getAttribute("res").toString())){
            AccountService accountService = new AccountService();
            Account account = new Account();
            account.setUsername(request.getParameter("username"));
            account.setPassword(request.getParameter("password"));
            account.setFirstName(request.getParameter("firstName"));
            account.setLastName(request.getParameter("lastName"));
            account.setEmail(request.getParameter("email"));
            account.setPhone(request.getParameter("phone"));
            account.setAddress1(request.getParameter("address1"));
            account.setAddress2(request.getParameter("address2"));
            account.setCity(request.getParameter("city"));
            account.setState(request.getParameter("state"));
            account.setZip(request.getParameter("zip"));
            account.setStatus("OK");
            account.setCountry(request.getParameter("country"));
            account.setLanguagePreference(request.getParameter("languagePreference"));
            account.setFavouriteCategoryId(request.getParameter("favouriteCategoryId"));
            account.setListOption((request.getParameter("listOption") != null));
            account.setBannerOption(request.getParameter("bannerOption") != null);
            accountService.update(account);
            session.setAttribute("account",account);
            request.getRequestDispatcher(MAIN).forward(request, response);
        }
        else{
            session.setAttribute("message", "验证码错误");
            request.getRequestDispatcher(ERROR).forward(request, response);
        }
    }
}
